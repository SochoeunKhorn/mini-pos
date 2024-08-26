package com.sochoeun.service.impl;

import com.sochoeun.dto.SupplierDto;
import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.Supplier;
import com.sochoeun.repository.SupplierRepository;
import com.sochoeun.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(Supplier request) {
        return supplierRepository.save(request);
    }

    @Override
    public Page<SupplierDto> getAllSuppliers(Map<String, String> params) {

        int offset = 0;
        int limit = 10;
        String local_name = "";
        if (params.containsKey("offset")){
            offset = Integer.parseInt(params.get("offset"));
        }
        if (params.containsKey("limit")){
            limit = Integer.parseInt(params.get("limit"));
        }
        if (params.containsKey("local-name")){
            local_name = params.get("local-name");
        }

        if (offset <= 0){
            offset = 1;
        }

        Pageable pageable = PageRequest.of(offset-1,limit);
        if (!local_name.isEmpty()){
            return supplierRepository.findAllByDeletedFalseAndSupplierLocalNameContainingIgnoreCase(local_name,pageable);
        }else
            return supplierRepository.findAllByDeletedFalse(pageable);

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
