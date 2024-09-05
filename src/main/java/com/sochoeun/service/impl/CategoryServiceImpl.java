package com.sochoeun.service.impl;
import com.sochoeun.dto.CategoryDto;
import com.sochoeun.dto.CategoryListResponse;
import com.sochoeun.dto.SubCategoryDto;
import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Category;
import com.sochoeun.repository.CategoryRepository;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category createSubCategory(SubCategoryDto request) {
        Category category = getCategory(request.getParentId());
        Category saved = categoryRepository.save(Category.builder()
                .categoryName(request.getCategoryName())
                .categoryCode(request.getCategoryCode())
                .imagePath(request.getImagePath())
                .build());
        saved.setParent(category);
        return categoryRepository.save(saved);
    }

    @Override
    public CategoryListResponse getAllCategories(Long categoryId) {
        /* list category and list sub-category*/
        Category category = getCategory(categoryId);

        return CategoryListResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categoryCode(category.getCategoryCode())
                .imagePath(category.getImagePath())
                .subCategoryResponses(categoryRepository.findAllByParentId(categoryId))
                .build();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new NotFoundException("Category not found with id: " + id));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(SubCategoryDto request, Long categoryId) {
        /* update ? category : sub-category*/

        Category category = getCategory(categoryId);

        category.setCategoryName(request.getCategoryName());
        category.setCategoryCode(request.getCategoryCode());
        category.setImagePath(request.getImagePath());

        if (category.getParent() != null){
            category.setParent(getCategory(request.getParentId()));
        }

        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllParentCategory() {
        return categoryRepository.findAllByParentIsNull();
    }

}
