package com.sochoeun.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private Long id;

    @NotNull(message = "CustomerLocalName is required")
    @NotBlank(message = "CustomerLocalName is required")
    private String customerLocalName;
    @NotNull(message = "CustomerEngName is required")
    @NotBlank(message = "CustomerEngName is required")
    private String customerEngName;
    @NotNull(message = "CustomerEmail is required")
    @Email(message = "Email not valid format")
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;

}
