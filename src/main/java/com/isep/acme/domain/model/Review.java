package com.isep.acme.domain.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.isep.acme.domain.model.enumerate.ApprovalStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // @NotNull
    private String user;

    @NotNull
    private Double rate;

    @NotNull(message = "Review Text is a mandatory attribute of Review.")
    @Size(max = 2048, message = "Review Text must not be greater than 2048 characters.")
    private String reviewText;

    @OneToMany(mappedBy = "review")
    private Set<Vote> votes = new HashSet<>();

    @NotNull
    private LocalDate publishingDate = LocalDate.now();

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Size(max = 2048, message = "Report must not be greater than 2048 characters.")
    private String report;

    @Version
    private long version;

    public void addVote(Vote vote){
        votes.add(vote);
    }
}
