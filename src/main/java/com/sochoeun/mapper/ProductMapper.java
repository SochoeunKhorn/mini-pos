package com.sochoeun.mapper;

import com.sochoeun.dto.ProductDto;
import com.sochoeun.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    /* ==  toProduct == */
    public ProductDto toProductRequest(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .productType(product.getProductType())
                .brandId(product.getBrand().getId())
                .build();
    }
}
