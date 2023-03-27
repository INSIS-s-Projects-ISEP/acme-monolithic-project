package com.isep.acme.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.acme.domain.model.AggregatedRating;
import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.repository.AggregatedRatingRepository;
import com.isep.acme.domain.repository.ProductRepository;
import com.isep.acme.domain.services.AggregatedRatingService;
import com.isep.acme.domain.services.ProductService;
import com.isep.acme.domain.services.ReviewService;

import java.util.Optional;

@Service
public class AggregatedRatingServiceImpl implements AggregatedRatingService{

    @Autowired
    AggregatedRatingRepository arRepository;

    @Autowired
    private ProductRepository pRepository;

    @Autowired
    ReviewService rService;

    @Autowired
    ProductService productService;

    @Override
    public AggregatedRating save( String sku ) {

        Optional<Product> product = pRepository.findBySku( sku );

        if (product.isEmpty()){
            return null;
        }

        Double average = rService.getWeightedAverage(product.get());


        Optional<AggregatedRating> r = arRepository.findByProductId(product.get());
        AggregatedRating aggregateF;

        if(r.isPresent()) {
            r.get().setAverage( average );
            aggregateF = arRepository.save(r.get());
        }
        else {
            AggregatedRating f = new AggregatedRating(average, product.get());
            aggregateF = arRepository.save(f);
        }

        return aggregateF;
    }


}
