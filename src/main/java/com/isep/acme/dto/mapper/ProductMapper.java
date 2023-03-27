package com.isep.acme.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.Product;
import com.isep.acme.dto.ProductDTO;
import com.isep.acme.dto.request.ProductRequest;
import com.isep.acme.dto.response.ProductResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getDesignation());
    }

    public Product toEntity(ProductRequest productRequest){
        return modelMapper.map(productRequest, Product.class);
    }

    public ProductResponse toResponse(Product product){
        return modelMapper.map(product, ProductResponse.class);
    }

}
