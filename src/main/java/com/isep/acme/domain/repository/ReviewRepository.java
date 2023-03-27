package com.isep.acme.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.model.Review;
import com.isep.acme.domain.model.User;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    List<Review> findByProductId(Product product);

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='pending'")
    List<Review> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='active'")
    List<Review> findActiveReviews();

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    List<Review> findByProductIdStatus(Product product, String status);

    @Query("SELECT r FROM Review r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    List<Review> findByUserId(User user);

}
