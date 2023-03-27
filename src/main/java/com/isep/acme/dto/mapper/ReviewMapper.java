package com.isep.acme.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.User;
import com.isep.acme.domain.services.UserService;
import com.isep.acme.dto.request.ReviewRequest;
import com.isep.acme.dto.response.ReviewResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReviewMapper {

    private final UserService userService;

    public ReviewResponse toResponse(Review review){
        return new ReviewResponse(
            review.getIdReview(),
            review.getReviewText(),
            review.getPublishingDate(),
            review.getApprovalStatus(),
            review.getFunFact(),
            review.getRate(),
            review.getUpVotes().size()
        );
    }

    public List<ReviewResponse> toDtoList(List<Review> review) {
        List<ReviewResponse> dtoList = new ArrayList<>();

        for (Review rev: review) {
            dtoList.add(toResponse(rev));
        }
        return dtoList;
    }

    public Review toEntity(ReviewRequest reviewRequest){
        User user = userService.getUserId(reviewRequest.getUserId()).orElseThrow();

        Review review = new Review();
        review.setUser(user);
        review.setReviewText(reviewRequest.getReviewText());
        review.setRate(reviewRequest.getRate());

        return review;
    }
}
