package com.isep.acme.dto.mapper;

import com.isep.acme.dto.ProductDTO;
import com.isep.acme.model.Product;

public class ProductMapper {

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getDesignation());
    }

}
