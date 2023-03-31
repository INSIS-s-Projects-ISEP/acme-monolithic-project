package com.isep.acme.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.domain.exception.ProductNotFoundException;
import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.model.enumerate.ApprovalStatus;
import com.isep.acme.domain.repository.ProductRepository;
import com.isep.acme.domain.repository.ReviewRepository;
import com.isep.acme.domain.services.ReviewService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsOfProduct(String sku, ApprovalStatus approvalStatus) {

        Optional<Product> product = productRepository.findBySku(sku);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }

        List<Review> reviews = reviewRepository.findByProductIdStatus(product.get(), approvalStatus);
        return reviews;
    }

    @Override
    public Review create(Review review) {
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void addVoteToReview(Review review, Vote vote) {
        review.addVote(vote);
        reviewRepository.save(review);
    }

    @Override
    public Double getWeightedAverage(Product product) {

        List<Review> reviews = reviewRepository.findByProductId(product);
        if(reviews.isEmpty()) {
            return 0.0;
        }

        Double sum = 0.0;
        for(Review review : reviews){
            sum += review.getRate();
        }

        return sum/reviews.size();
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> findPendingReview() {
        return reviewRepository.findPendingReviews();
    }

    @Override
    public Review moderateReview(Review review, ApprovalStatus approvalStatus) {
        review.setApprovalStatus(approvalStatus);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findReviewsByUser(String user) {
        List<Review> reviews = reviewRepository.findByUserId(user);
        return reviews;
    }

}
