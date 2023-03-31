package com.isep.acme.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.enumerate.ApprovalStatus;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    List<Review> findByProductId(Product product);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='PENDING'")
    List<Review> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='ACTIVE'")
    List<Review> findActiveReviews();

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    List<Review> findByProductIdStatus(Product product, ApprovalStatus approvalStatus);

    @Query("SELECT r FROM Review r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    List<Review> findByUserId(String user);

}
