package com.sochoeun.repository;

import com.sochoeun.dto.CategoryDto;
import com.sochoeun.dto.SubCategoryDto;
import com.sochoeun.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<SubCategoryDto> findAllByParentId(Long parentId);

    List<CategoryDto> findAllByParentIsNull();
}
