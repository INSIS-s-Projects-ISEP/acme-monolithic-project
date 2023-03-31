package com.isep.acme.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.isep.acme.domain.model.enumerate.VoteType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @NotNull
    private String user;

    public Boolean isUpVote(){
        return voteType.equals(VoteType.UP_VOTE);
    }

    public Boolean isDownVote(){
        return voteType.equals(VoteType.DOWN_VOTE);
    }

}
