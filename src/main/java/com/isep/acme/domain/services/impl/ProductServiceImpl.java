package com.isep.acme.domain.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.repositories.ProductRepository;
import com.isep.acme.domain.services.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> getCatalog() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    @Override
    public Iterable<Product> findByDesignation(String designation) {
        return productRepository.findByDesignation(designation);
    }

    @Override
    public Product create(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateBySku(String sku, Product productUpdated){
        Product product = productRepository.findBySku(sku).orElseThrow();
        product.updateProduct(productUpdated);
        return productRepository.save(product);
    }

    @Override
    public void deleteBySku(String sku) {
        productRepository.deleteBySku(sku);
    }
}
