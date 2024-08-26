package com.sochoeun.service;

import com.sochoeun.dto.SupplierDto;
import com.sochoeun.model.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SupplierService {

    Supplier createSupplier(Supplier request);
    Page<SupplierDto> getAllSuppliers(Map<String,String> params);
    Supplier getSupplier(Long id);
    Supplier updateSupplier(Supplier request);
    void deleteSupplier(Long id);
}
