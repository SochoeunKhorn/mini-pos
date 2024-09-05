package com.sochoeun.dto;

import com.sochoeun.model.ProductType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Long id;

    @NotNull(message = "Product name is required")
    @NotBlank(message = "Product name is required")
    private String productName;

    private String productCode;

    @NotNull(message = "Product type is required")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotNull(message = "Brand-ID is required")
    private Long brandId;
}
