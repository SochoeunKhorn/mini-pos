package com.sochoeun.mapper;

import com.sochoeun.dto.BrandDto;
import com.sochoeun.model.Brand;
import org.springframework.stereotype.Service;

@Service
public class BrandMapper {

    public  Brand toBrand(BrandDto brandDto){
        if(brandDto == null){
            return null;
        }
        return Brand.builder()
                .id(brandDto.getId())
                .name(brandDto.getName())
                .build();
    }

    public BrandDto toBrandDto(Brand brand){
        if(brand == null){
            return null;
        }
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
