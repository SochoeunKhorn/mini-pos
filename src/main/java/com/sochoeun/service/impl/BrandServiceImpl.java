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
        return brandRepository.findAllByNameContainingAndIsDeletedFalse(name);
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

        if (!brand.getIsDeleted()){
            brand.setIsDeleted(true);
            brandRepository.save(brand);
        }else {
            throw new NotFoundException(format("Brand with ID %d not found", brandID));
        }

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
        return brandRepository.findAllByNameContaining(name,pageable);
    }
}
