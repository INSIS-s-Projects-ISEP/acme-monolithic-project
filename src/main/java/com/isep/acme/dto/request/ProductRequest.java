package com.isep.acme.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequest {

    @NotBlank(message = "SKU is a mandatory attribute of Product.")
    @Size(min = 12, max = 12, message = "SKU must be 12 characters long.")
    private String sku;

    @NotBlank(message = "Designation is a mandatory attribute of Product.")
    @Size(max = 50, message = "Designation must not be greater than 50 characters.")
    private String designation;

    @NotBlank(message = "Description is a mandatory attribute of Product.")
    @Size(max = 1200, message = "Description must not be greater than 1200 characters.")
    private String description;
}
