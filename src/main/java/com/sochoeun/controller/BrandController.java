package com.sochoeun.controller;

import com.sochoeun.dto.BrandDto;
import com.sochoeun.dto.PageResponse;
import com.sochoeun.mapper.BrandMapper;
import com.sochoeun.model.Brand;
import com.sochoeun.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    /* == Create ==*/
    @PostMapping
    public ResponseEntity<?> addBrand(@RequestBody @Valid BrandDto brandDto) {
        Brand brand = brandMapper.toBrand(brandDto);
        Brand saved = brandService.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /* == List Brands :: Filter By Name ==*/
    @GetMapping
    public ResponseEntity<?> getAllBrands(
            @RequestParam(required = false) String name
    ) {
        List<Brand> allBrands = brandService.getAllBrands(name);

        if (allBrands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allBrands);
    }

    /* == Brand :: By ID ==*/
    @GetMapping("/{brand-id}")
    public ResponseEntity<?> getBrand(@PathVariable("brand-id") Long brandId) {
        Brand brand = brandService.getBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).body(brand);
    }

    /* == Update ==*/
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody BrandDto brandDto) {
        Brand dto = brandMapper.toBrand(brandDto);
        Brand updated = brandService.updateBrand(dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    /* == Delete ==*/
    @DeleteMapping("/{brand-id}")
    public ResponseEntity<?> delete(@PathVariable("brand-id") Long brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).body("Brand " + brandId + " deleted successfully");
    }

    /*  == Pagination ==  */
    @GetMapping("/pagination")
    ResponseEntity<?> getBrands(
            @RequestParam(required = false) String name,
            @RequestParam(required = false,defaultValue = "1") Integer offSet,
            @RequestParam(required = false,defaultValue = "10") Integer limit
    ) {
        Page<Brand> allBrands = brandService.getBrands(name,offSet,limit);
        PageResponse response = new PageResponse(allBrands);

        if (allBrands.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
