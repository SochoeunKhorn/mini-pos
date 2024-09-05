package com.sochoeun.controller;

import com.sochoeun.dto.CategoryListResponse;
import com.sochoeun.dto.CategoryDto;
import com.sochoeun.dto.SubCategoryDto;
import com.sochoeun.mapper.CategoryMapper;
import com.sochoeun.model.Category;
import com.sochoeun.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@Tag(name = "CATEGORY")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto category) {
        Category savedCategory = categoryService.createCategory(categoryMapper.toCategory(category));
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toCategoryResponse(savedCategory));
    }

    @PostMapping("/sub-category")
    public ResponseEntity<?> addSubCategory(@Valid @RequestBody SubCategoryDto subCategory) {
        Category category = categoryService.createSubCategory(subCategory);
        return  ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toSubCategoryResponse(category));
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getCategory(@PathVariable("category-id")Long id){
        CategoryListResponse allCategories = categoryService.getAllCategories(id);
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping
    public ResponseEntity<?> getCategories(){
        List<CategoryDto> categoryDto = categoryService.getAllParentCategory();
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("category-id")Long id){
        categoryService.deleteCategory(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<?> updatedCategory(@PathVariable("category-id")Long id,@RequestBody SubCategoryDto category){
        categoryService.updateCategory(category,id);
        return  ResponseEntity.status(HttpStatus.OK).body("Updated  successfully");
    }

}
