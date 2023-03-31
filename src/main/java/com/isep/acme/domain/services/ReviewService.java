package com.isep.acme.domain.services;

import java.util.List;

import com.isep.acme.domain.model.*;
import com.isep.acme.domain.model.enumerate.ApprovalStatus;
import com.isep.acme.dto.VoteReviewDTO;
import com.isep.acme.dto.request.ReviewRequest;
import com.isep.acme.dto.response.ReviewResponse;

public interface ReviewService {

    Iterable<Review> getAll();

    List<ReviewResponse> getReviewsOfProduct(String sku, ApprovalStatus approvalStatus);

    Review create(Review review, String sku);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Double getWeightedAverage(Product product);

    Boolean DeleteReview(Long reviewId);

    List<ReviewResponse> findPendingReview();

    ReviewResponse moderateReview(Long reviewID, ApprovalStatus approvalStatus);

    List<ReviewResponse> findReviewsByUser(Long userID);
}
