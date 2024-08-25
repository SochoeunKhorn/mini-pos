package com.sochoeun.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SupplierDto {

    private Long id;

    @NotNull(message = "SupplierLocalName is required")
    @NotBlank(message = "SupplierLocalName is required")
    private String supplierLocalName;
    @NotNull(message = "SupplierEngName is required")
    @NotBlank(message = "SupplierEngName is required")
    private String supplierEngName;
    @NotNull(message = "CustomerEmail is required")
    @Email(message = "Email not valid format")
    private String supplierEmail;
    private String supplierPhone;
    private String supplierAddress;
    private String vatNumber;
}
