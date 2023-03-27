package com.isep.acme.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Product;
import com.isep.acme.dto.request.ProductRequest;
import com.isep.acme.dto.response.ProductResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;

    public Product toEntity(ProductRequest productRequest){
        return modelMapper.map(productRequest, Product.class);
    }

    public ProductResponse toResponse(Product product){
        return modelMapper.map(product, ProductResponse.class);
    }

    public Iterable<ProductResponse> toResponseList(Iterable<Product> products){
        List<ProductResponse> productsResponse = new ArrayList<>();
        for(Product product : products){
            ProductResponse productResponse = toResponse(product);
            productsResponse.add(productResponse);
        }
        return productsResponse;
    }

}
