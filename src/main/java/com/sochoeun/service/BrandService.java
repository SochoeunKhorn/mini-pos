package com.sochoeun.service;

import com.sochoeun.model.Brand;
import org.springframework.data.domain.Page;


import java.util.List;

public interface BrandService {
    Brand create(Brand request);
    List<Brand> getAllBrands(String name);
    Brand getBrand(Long brandID);
    Brand updateBrand(Brand brand);
    void deleteBrand(Long brandID);

    /* Pagination */
    Page<Brand> getBrands(String name,Integer offset,Integer limit);
}
