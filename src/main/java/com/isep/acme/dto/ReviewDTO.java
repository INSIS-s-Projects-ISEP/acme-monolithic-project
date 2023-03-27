package com.isep.acme.dto;

import java.time.LocalDate;

import com.isep.acme.domain.model.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ReviewDTO {

    private Long idReview;
    private String reviewText;
    private LocalDate publishingDate;
    private ApprovalStatus approvalStatus;
    private String funFact;
    private Double rating;
    private Integer vote;

}
