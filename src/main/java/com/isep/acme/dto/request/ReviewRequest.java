package com.isep.acme.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String reviewText;

    @NotNull
    @Size(min = 0, max = 5)
    private Double rate;
}
