package com.isep.acme.domain.services;

import java.util.Optional;

import com.isep.acme.domain.model.Product;

public interface ProductService {

    Product create(Product product);

    Iterable<Product> getCatalog();
    Optional<Product> findBySku(String sku);
    Iterable<Product> findByDesignation(String designation);
    
    Product updateBySku(String sku, Product product);
    void deleteBySku(final String sku);

}
