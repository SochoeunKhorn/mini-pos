package com.sochoeun.controller;

import com.sochoeun.dto.SupplierDto;
import com.sochoeun.mapper.SupplierMapper;
import com.sochoeun.model.Supplier;
import com.sochoeun.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SupplierDto dto) {
        Supplier supplier = supplierService.createSupplier(supplierMapper.toSupplier(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierMapper.toSupplierDto(supplier));
    }
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<SupplierDto> SupplierDto = suppliers.stream()
                .map(supplierMapper::toSupplierDto)
                .toList();

        HttpStatus status = HttpStatus.OK;
        if (suppliers.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(status).body(SupplierDto);
    }
    
    @GetMapping("/{supplier-id}")
    public ResponseEntity<?> getById(@PathVariable("supplier-id") Long id) {
        Supplier supplier = supplierService.getSupplier(id);
        return ResponseEntity.ok(supplierMapper.toSupplierDto(supplier));
    }

    @DeleteMapping("/{supplier-id}")
    public ResponseEntity<?> delete(@PathVariable("supplier-id") Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier is deleted");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody SupplierDto dto) {
        Supplier supplier = supplierService.updateSupplier(supplierMapper.toSupplier(dto));
        return ResponseEntity.ok(supplierMapper.toSupplierDto(supplier));
    }
}
