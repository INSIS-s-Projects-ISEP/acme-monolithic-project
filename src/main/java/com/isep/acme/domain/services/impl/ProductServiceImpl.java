package com.isep.acme.domain.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.repositories.ProductRepository;
import com.isep.acme.domain.services.ProductService;
import com.isep.acme.dto.ProductDTO;
import com.isep.acme.dto.ProductDetailDTO;
import com.isep.acme.dto.mapper.ProductMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductBySku( final String sku ) {

        return productRepository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = productRepository.findBySku(sku);

        if( product.isEmpty() )
            return Optional.empty();
        else
            return Optional.of(ProductMapper.toDto(product.get()));
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = productRepository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd:p) {
            pDto.add(ProductMapper.toDto(pd));
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = productRepository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd:p) {
            pDto.add(ProductMapper.toDto(pd));
        }

        return pDto;
    }

    public ProductDetailDTO getDetails(String sku) {

        Optional<Product> p = productRepository.findBySku(sku);

        if (p.isEmpty())
            return null;
        else
            return new ProductDetailDTO(p.get().getSku(), p.get().getDesignation(), p.get().getDescription());
    }


    @Override
    public Product create(Product product){
        return productRepository.save(product);
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {
        
        final Optional<Product> productToUpdate = productRepository.findBySku(sku);

        if( productToUpdate.isEmpty() ) return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = productRepository.save(productToUpdate.get());
        
        return ProductMapper.toDto(productUpdated);
    }

    @Override
    public void deleteBySku(String sku) {
        productRepository.deleteBySku(sku);
    }
}
