package com.isep.acme.api.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.isep.acme.domain.model.Product;
import com.isep.acme.domain.services.ProductService;
import com.isep.acme.dto.mapper.ProductMapper;
import com.isep.acme.dto.request.ProductRequest;
import com.isep.acme.dto.response.ProductResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@AllArgsConstructor
@RequestMapping("/products")
class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        try {
            productService.create(product);
            ProductResponse productResponse = productMapper.toResponse(product);
            return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
        } 
        catch(Exception exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Product must have a unique SKU.");
        }
    }

    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductResponse>> getCatalog() {

        Iterable<Product> catalog = productService.getCatalog();
        Iterable<ProductResponse> catalogResponse = productMapper.toResponseList(catalog);
        
        return ResponseEntity.ok().body(catalogResponse);
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "{sku}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable("sku") String sku){

        Product product = productService.findBySku(sku).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        });

        ProductResponse productResponse = productMapper.toResponse(product);
        return ResponseEntity.ok().body(productResponse);
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "designation/{designation}")
    public ResponseEntity<Iterable<ProductResponse>> findAllByDesignation(@PathVariable("designation") String designation){

        Iterable<Product> products = productService.findByDesignation(designation);
        Iterable<ProductResponse> productsResponse = productMapper.toResponseList(products);

        return ResponseEntity.ok().body(productsResponse);
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<ProductResponse> update(@PathVariable("sku") String sku, @RequestBody ProductRequest productRequest) {
        
        Product product = productMapper.toEntity(productRequest);
        Product productUpdated = productService.updateBySku(sku, product);
        ProductResponse productResponse = productMapper.toResponse(productUpdated);

        return ResponseEntity.ok().body(productResponse);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<?> delete(@PathVariable("sku") String sku){
        productService.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }

}