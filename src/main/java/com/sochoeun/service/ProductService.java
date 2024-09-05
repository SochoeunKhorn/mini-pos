package com.sochoeun.service;

import com.sochoeun.dto.ProductDto;
import com.sochoeun.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto product);
    List<Product> getAllProducts();
    Product getProduct(Long id);
    void deleteProduct(Long id);
    Product updateProduct(Long id , ProductDto product);
}
