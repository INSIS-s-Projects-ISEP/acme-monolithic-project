package com.isep.acme.domain.services;

import java.util.List;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.Vote;
import com.isep.acme.domain.model.enumerate.ApprovalStatus;

public interface ReviewService {

    Iterable<Review> getAll();

    List<Review> getReviewsOfProduct(String sku, ApprovalStatus approvalStatus);

    Review create(Review review);

    void addVoteToReview(Review review, Vote vote);

    Double getWeightedAverage(Product product);

    void deleteReview(Review review);

    List<Review> findPendingReview();

    Review moderateReview(Review review, ApprovalStatus approvalStatus);

    List<Review> findReviewsByUser(String user);

}
