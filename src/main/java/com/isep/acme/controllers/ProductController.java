package com.isep.acme.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.isep.acme.dto.ProductDTO;
import com.isep.acme.model.Product;
import com.isep.acme.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    private ProductService service;


    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
       final var products = service.getCatalog();

       return ResponseEntity.ok().body( products );
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {

        final Optional<ProductDTO> product = service.findBySku(sku);

        if( product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation){

        final Iterable<ProductDTO> products = service.findByDesignation( designation );
        
        return ResponseEntity.ok().body( products );
    }

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody Product manager) {
        try {
            final ProductDTO product = service.create(manager);
            return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> Update(@PathVariable("sku") final String sku, @RequestBody final Product product) {

        final ProductDTO productDTO = service.updateBySku(sku, product);

        if( productDTO == null )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<Product> delete(@PathVariable("sku") final String sku ){

        service.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }
}