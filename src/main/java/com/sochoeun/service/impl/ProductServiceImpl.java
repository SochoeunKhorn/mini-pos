package com.sochoeun.service.impl;

import com.sochoeun.dto.ProductDto;
import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Brand;
import com.sochoeun.model.Product;
import com.sochoeun.repository.ProductRepository;
import com.sochoeun.service.BrandService;
import com.sochoeun.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;

    @Override
    public Product createProduct(ProductDto product) {
        Brand brand = brandService.getBrand(product.getBrandId());
        return productRepository.save(
                Product.builder()
                        .productName(product.getProductName())
                        .productCode(product.getProductCode())
                        .productType(product.getProductType())
                        .brand(brand)
                        .build());
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(()->new NotFoundException("product not found with id: " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        getProduct(id);
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, ProductDto product) {

        Brand brand = brandService.getBrand(product.getBrandId());

        Product updated = getProduct(id);
        updated.setProductName(product.getProductName());
        updated.setProductCode(product.getProductCode());
        updated.setProductType(product.getProductType());
        updated.setBrand(brand);
        return productRepository.save(updated);
    }
}
