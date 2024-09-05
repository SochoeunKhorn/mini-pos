package com.sochoeun.controller;

import com.sochoeun.dto.ProductDto;
import com.sochoeun.mapper.ProductMapper;
import com.sochoeun.model.Product;
import com.sochoeun.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "PRODUCT")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductRequest(saved));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        HttpStatus httpStatus = HttpStatus.OK;
        if (products.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).body(products);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProduct(@PathVariable("product-id") Long productId) {
        Product product = productService.getProduct(productId);
        return ResponseEntity.ok(productMapper.toProductRequest(product));
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("product-id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PostMapping("/{product-id}")
    public ResponseEntity<?> updateProduct(@PathVariable("product-id") Long productId, @Valid @RequestBody ProductDto product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseEntity.ok(productMapper.toProductRequest(updatedProduct));
    }

}
