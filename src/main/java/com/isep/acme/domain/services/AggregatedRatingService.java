package com.isep.acme.domain.services;

import com.isep.acme.domain.model.AggregatedRating;

public interface AggregatedRatingService {

    AggregatedRating save(String sku);
}
