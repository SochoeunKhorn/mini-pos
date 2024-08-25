package com.sochoeun.service.impl;

import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Supplier;
import com.sochoeun.repository.SupplierRepository;
import com.sochoeun.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(Supplier request) {
        return supplierRepository.save(request);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAllByDeletedFalse();
    }

    @Override
    public Supplier getSupplier(Long id) {
        return supplierRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()->new NotFoundException("Supplier not found with id: " + id));
    }

    @Override
    public Supplier updateSupplier(Supplier request) {
        Supplier supplier = getSupplier(request.getId());
        supplier.setSupplierLocalName(request.getSupplierLocalName());
        supplier.setSupplierEngName(request.getSupplierEngName());
        supplier.setSupplierEmail(request.getSupplierEmail());
        supplier.setSupplierPhone(request.getSupplierPhone());
        supplier.setSupplierAddress(request.getSupplierAddress());
        supplier.setVatNumber(request.getVatNumber());
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplier(id);
        supplier.setDeleted(true);
        supplierRepository.save(supplier);
    }
}
