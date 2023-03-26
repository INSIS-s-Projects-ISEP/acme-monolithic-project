package com.isep.acme.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Version
    private long version;

    @NotNull
    private String approvalStatus;

    @NotNull(message = "Review Text is a mandatory attribute of Review.")
    @Size(max = 2048, message = "Review Text must not be greater than 2048 characters.")
    private String reviewText;

    @ElementCollection
    @Column(nullable = true)
    private Set<Vote> upVotes = new HashSet<>();

    @ElementCollection
    @Column(nullable = true)
    private Set<Vote> downVotes = new HashSet<>();

    @NotNull
    @Size(max = 2048, message = "Report must not be greater than 2048 characters.")
    private String report;

    @NotNull
    private LocalDate publishingDate;

    @NotNull
    private String funFact;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Rating rating = new Rating(0.0);

    public Review(String reviewText, LocalDate publishingDate, Product product, String funFact, Rating rating, User user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
    }

    public Boolean setApprovalStatus(String approvalStatus) {
        
        if(Arrays.asList("pending", "approved", "rejected").contains(approvalStatus.toLowerCase())){
            this.approvalStatus = approvalStatus;
            return true;
        }

        return false;
    }

    public boolean addUpVote(Vote upVote) {

        if(!approvalStatus.equals("approved")){
            return false;
        }
        upVotes.add(upVote);
        return true;
    }

    public boolean addDownVote(Vote downVote) {

        if(!approvalStatus.equals("approved")){
            return false;
        }

        downVotes.add(downVote);
        return true;
    }
}
