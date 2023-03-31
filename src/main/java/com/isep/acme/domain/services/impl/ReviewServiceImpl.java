package com.isep.acme.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.api.controllers.ResourceNotFoundException;
import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.User;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.model.enumerate.ApprovalStatus;
import com.isep.acme.domain.repository.ProductRepository;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.domain.repository.UserRepository;
import com.isep.acme.domain.services.RestService;
import com.isep.acme.domain.services.ReviewService;
import com.isep.acme.domain.services.UserService;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.dto.mapper.ReviewMapper;
import com.isep.acme.dto.response.ReviewResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserService userService;

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private final RestService restService;

    @Override
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review create(Review review, String sku){
        
        generateFunFact(review);
        findProduct(review, sku);
        reviewRepository.save(review);

        return review;
    }

    private void generateFunFact(Review review) {
        String funfact = restService.generateFunFact(review.getPublishingDate());
        review.setFunFact(funfact);
    }

    private void findProduct(Review review, String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow();
        review.setProduct(product);
    }

    @Override
    public List<ReviewResponse> getReviewsOfProduct(String sku, ApprovalStatus approvalStatus) {

        Optional<Product> product = productRepository.findBySku(sku);
        if( product.isEmpty() ) return null;

        List<Review> r = reviewRepository.findByProductIdStatus(product.get(), approvalStatus);

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<Review> review = this.reviewRepository.findById(reviewID);

        if (review.isEmpty()) return false;

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if (voteReviewDTO.getVote().equalsIgnoreCase("upVote")) {
            boolean added = review.get().addUpVote(vote);
            if (added) {
                Review reviewUpdated = this.reviewRepository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                Review reviewUpdated = this.reviewRepository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
    }

    @Override
    public Double getWeightedAverage(Product product){

        List<Review> r = reviewRepository.findByProductId(product);

        if (r.isEmpty()) return 0.0;

        double sum = 0;

        // for (Review rev: r) {
        //     Rating rate = rev.getRating();

        //     if (rate != null){
        //         sum += rate.getRate();
        //     }
        // }

        return sum/r.size();
    }

    @Override
    public Boolean DeleteReview(Long reviewId)  {

        Optional<Review> rev = reviewRepository.findById(reviewId);

        if (rev.isEmpty()){
            return null;
        }
        Review r = rev.get();

        if (r.getUpVotes().isEmpty() && r.getDownVotes().isEmpty()) {
            reviewRepository.delete(r);
            return true;
        }
        return false;
    }

    @Override
    public List<ReviewResponse> findPendingReview(){

        List<Review> r = reviewRepository.findPendingReviews();

        if(r.isEmpty()){
            return null;
        }

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public ReviewResponse moderateReview(Long reviewID, ApprovalStatus approvalStatus) throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = reviewRepository.findById(reviewID);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("Review not found");
        }

        r.get().setApprovalStatus(approvalStatus);

        Review review = reviewRepository.save(r.get());

        return ReviewMapper.toResponse(review);
    }


    @Override
    public List<ReviewResponse> findReviewsByUser(Long userID) {

        final Optional<User> user = userRepository.findById(userID);

        if(user.isEmpty()) return null;

        List<Review> r = reviewRepository.findByUserId(user.get());

        if (r.isEmpty()) return null;

        return ReviewMapper.toDtoList(r);
    }
}