package com.sochoeun.service.impl;

import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Brand;
import com.sochoeun.repository.BrandRepository;
import com.sochoeun.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private static final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final BrandRepository brandRepository;

    @Override
    public Brand create(Brand request) {
        request.setIsDeleted(false);
        return brandRepository.save(request);
    }

    @Override
    public List<Brand> getAllBrands(String name) {
        log.info("Sorting name : {}", name);

        if (name == null || name.isEmpty()) {
            return brandRepository.findAllByIsDeletedIsFalse();
        }
        return brandRepository.findAllByNameContainingIgnoreCaseAndIsDeletedFalse(name);
    }

    @Override
    public Brand getBrand(Long brandID) {
        return brandRepository.findByIdAndIsDeletedFalse(brandID).
                orElseThrow(()-> new NotFoundException(format("Brand with ID %d not found", brandID)));
    }

    @Override
    public Brand updateBrand(Brand brand) {
        Brand updated = getBrand(brand.getId());
        updated.setName(brand.getName());
        return brandRepository.save(updated);
    }

    @Override
    public void deleteBrand(Long brandID) {

        /* == soft delete :: updated isDelete = true == */
        Brand brand = getBrand(brandID);
        brand.setIsDeleted(true);
        brandRepository.save(brand);
    }

    /*
    *   Pagination
    *   - offSet    : page number
    *   - pageSize  : size of per page
    *
    *   - content   : hold list of data
    *   - totalElement
    *   - totalPage
    *   - currentPage
    *   - totalPage
    * */
    @Override
    public Page<Brand> getBrands(String name,Integer offset, Integer limit) {

        if (offset <= 0){
            offset = 1;
        }

        Pageable pageable = PageRequest.of(offset-1,limit);

        if (name == null || name.isEmpty()) {
            return brandRepository.findAll(pageable);
        }
        return brandRepository.findAllByNameContainingIgnoreCase(name,pageable);
    }

    /* Pagination with Map */
    @Override
    public Page<Brand> getBrands(Map<String, String> params) {

        /*
        *   Map<key,value> :
        *       + Map<offset,1>
        *       + Map<limit,10>
        * */
        int offset = 0;
        int limit = 10;
        String name = "";
        if (params.containsKey("offset")){
             offset = Integer.parseInt(params.get("offset"));
        }
        if (params.containsKey("limit")){
             limit = Integer.parseInt(params.get("limit"));
        }
        if (params.containsKey("name")){
            name = params.get("name");
        }

        if (offset <= 0){
            offset = 1;
        }

        Pageable pageable = PageRequest.of(offset-1,limit);

        if (name == null || name.isEmpty()) {
            return brandRepository.findAll(pageable);
        }
        return brandRepository.findAllByNameContainingIgnoreCase(name,pageable);

    }
}
