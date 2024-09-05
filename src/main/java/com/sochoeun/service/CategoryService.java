package com.sochoeun.service;

import com.sochoeun.dto.CategoryDto;
import com.sochoeun.dto.CategoryListResponse;
import com.sochoeun.dto.SubCategoryDto;
import com.sochoeun.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryListResponse getAllCategories(Long categoryId);

    Category createCategory(Category category);

    Category createSubCategory(SubCategoryDto category);

    Category getCategory(Long id);

    void deleteCategory(Long id);

    void updateCategory(SubCategoryDto category, Long categoryId);

    /* == get only parent category == */
    //
    List<CategoryDto> getAllParentCategory();

}
