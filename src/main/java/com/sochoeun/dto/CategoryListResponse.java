package com.sochoeun.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryListResponse {
    private Long id;
    private String categoryName;
    private String categoryCode;
    private String imagePath;
    private List<SubCategoryDto> subCategoryResponses;
}
