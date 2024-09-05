package com.sochoeun.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
public class SubCategoryDto {
    private Long id;

    @NotNull(message = "category-name is required.")
    @NotBlank(message = "category-name is required.")
    private String categoryName;

    @Length(max = 10)
    private String categoryCode;
    private String imagePath;

    @NotNull(message = "parent id is required.")
    private Long parentId;
}
