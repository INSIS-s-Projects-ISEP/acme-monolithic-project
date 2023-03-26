package com.isep.acme.dto.mapper;

import com.isep.acme.domain.model.Product;
import com.isep.acme.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getDesignation());
    }

}
