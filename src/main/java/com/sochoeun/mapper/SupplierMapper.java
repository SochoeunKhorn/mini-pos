package com.sochoeun.mapper;

import com.sochoeun.dto.SupplierDto;
import com.sochoeun.model.Supplier;
import org.springframework.stereotype.Service;

@Service
public class SupplierMapper {

    public Supplier toSupplier(SupplierDto dto){
        return Supplier.builder()
                .id(dto.getId())
                .supplierLocalName(dto.getSupplierLocalName())
                .supplierEngName(dto.getSupplierEngName())
                .supplierEmail(dto.getSupplierEmail())
                .supplierPhone(dto.getSupplierPhone())
                .supplierAddress(dto.getSupplierAddress())
                .vatNumber(dto.getVatNumber())
                .build();
    }

    public SupplierDto toSupplierDto(Supplier supplier){
        return SupplierDto.builder()
                .id(supplier.getId())
                .supplierLocalName(supplier.getSupplierLocalName())
                .supplierEngName(supplier.getSupplierEngName())
                .supplierEmail(supplier.getSupplierEmail())
                .supplierPhone(supplier.getSupplierPhone())
                .supplierAddress(supplier.getSupplierAddress())
                .vatNumber(supplier.getVatNumber())
                .build();
    }
}
