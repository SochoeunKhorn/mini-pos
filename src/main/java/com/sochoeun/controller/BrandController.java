package com.sochoeun.controller;

import com.sochoeun.dto.BrandDto;
import com.sochoeun.dto.PageResponse;
import com.sochoeun.mapper.BrandMapper;
import com.sochoeun.model.Brand;
import com.sochoeun.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
@Tag(name="Brand")
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    /* == Create ==*/
    @PostMapping
    public ResponseEntity<?> addBrand(@RequestBody @Valid BrandDto brandDto) {
        Brand brand = brandMapper.toBrand(brandDto);
        var saved = brandService.create(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandMapper.toBrandDto(saved));
    }

    /* == List Brands :: Filter By Name ==*/
    @GetMapping
    public ResponseEntity<?> getAllBrands(
            @RequestParam(required = false) String name
    ) {
        List<Brand> allBrands = brandService.getAllBrands(name);

        List<BrandDto> brandDtoList = allBrands.stream()
                .map(
                        brand -> BrandDto.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .build())
                .toList();

        HttpStatus httpStatus = HttpStatus.OK;

        if (allBrands.isEmpty()){
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).body(brandDtoList);
    }

    /* == Brand :: By ID ==*/
    @GetMapping("/{brand-id}")
    public ResponseEntity<?> getBrand(@PathVariable("brand-id") Long brandId) {
        Brand brand = brandService.getBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).body(brandMapper.toBrandDto(brand));
    }

    /* == Update ==*/
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody BrandDto brandDto) {
        Brand dto = brandMapper.toBrand(brandDto);
        Brand updated = brandService.updateBrand(dto);
        return ResponseEntity.status(HttpStatus.OK).body(brandMapper.toBrandDto(updated));
    }

    /* == Delete ==*/
    @DeleteMapping("/{brand-id}")
    public ResponseEntity<?> delete(@PathVariable("brand-id") Long brandId) {
        brandService.deleteBrand(brandId);
        return ResponseEntity.status(HttpStatus.OK).body("Brand " + brandId + " deleted successfully");
    }

    /*  == Pagination ==  */
/*    @GetMapping("/pagination")
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
    }*/

    /* == Pagination with Mpa<> == */
    @GetMapping("/pagination")
    public ResponseEntity<?> getBrandPagination(@RequestParam Map<String,String> params){
        Page<Brand> brands = brandService.getBrands(params);
        PageResponse response = new PageResponse(brands);

        HttpStatus httpStatus = HttpStatus.OK;
        if (brands.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
