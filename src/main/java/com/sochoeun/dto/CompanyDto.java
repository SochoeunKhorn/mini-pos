package com.sochoeun.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyDto {
    private Long id;

    @NotNull(message = "CompanyLocalName is required")
    @NotBlank(message = "CompanyLocalName is required")
    private String companyLocalName;

    @NotNull(message = "CompanyEngName is required")
    @NotBlank(message = "CompanyEngName is required")
    private String companyEngName;

    @NotNull(message = "CompanyEmail is required")
    @NotBlank(message = "CompanyEmail is required")
    private String companyEmail;

    @NotNull(message = "CompanyPhone is required")
    @NotBlank(message = "CompanyPhone is required")
    private String companyPhone;
    private String companyAddress;
    private String vatNumber;
    private String imagePath;
    private String image;
}
