package com.sochoeun.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDto {

    private Long id;

    @NotNull(message = "Brand Name is required")
    @NotBlank(message = "Brand Name is required")
    private String name;
}
