package com.sochoeun.mapper;
import com.sochoeun.dto.CategoryDto;
import com.sochoeun.dto.SubCategoryDto;
import com.sochoeun.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryMapper {

    /* == category == */
    public Category toCategory(CategoryDto request) {
        return Category.builder()
                .categoryName(request.getCategoryName())
                .categoryCode(request.getCategoryCode())
                .imagePath(request.getImagePath())
                .build();
    }

    /* == categoryResponse == */
    public CategoryDto toCategoryResponse(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categoryCode(category.getCategoryCode())
                .imagePath(category.getImagePath())
                .build();
    }

    /*== sub-category-response == */
    public SubCategoryDto toSubCategoryResponse(Category category) {
        return SubCategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categoryCode(category.getCategoryCode())
                .imagePath(category.getImagePath())
                .parentId(category.getParent().getId())
                .build();
    }
}
