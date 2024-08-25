package com.sochoeun.service;

import com.sochoeun.model.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier createSupplier(Supplier request);
    List<Supplier> getAllSuppliers();
    Supplier getSupplier(Long id);
    Supplier updateSupplier(Supplier request);
    void deleteSupplier(Long id);
}
