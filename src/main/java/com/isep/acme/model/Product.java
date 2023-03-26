package com.isep.acme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(unique = true)
    @NotBlank(message = "SKU is a mandatory attribute of Product.")
    @Size(min = 12, max = 12, message = "SKU must be 12 characters long.")
    public String sku;

    @NotBlank(message = "Designation is a mandatory attribute of Product.")
    @Size(max = 50, message = "Designation must not be greater than 50 characters.")
    private String designation;

    @NotBlank(message = "Description is a mandatory attribute of Product.")
    @Size(max = 1200, message = "Description must not be greater than 1200 characters.")
    private String description;

    public Product(String sku, String designation, String description) {
        this.sku = sku;
        this.description = description;
        this.designation = designation;
    }

    public void updateProduct(Product product) {
        BeanUtils.copyProperties(this, product, "productId");
    }

    @Deprecated
    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }

}
