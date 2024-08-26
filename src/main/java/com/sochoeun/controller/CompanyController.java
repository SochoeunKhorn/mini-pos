package com.sochoeun.controller;

import com.sochoeun.dto.CompanyDto;
import com.sochoeun.dto.PageResponse;
import com.sochoeun.mapper.CompanyMapper;
import com.sochoeun.model.Company;
import com.sochoeun.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@Tag(name = "COMPANY")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @PostMapping
    public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDto dto){
        Company company = companyMapper.toCompany(dto);
        var saved = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toCompanyDto(saved));
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies(@RequestParam Map<String, String> params){
        /* == Params-> offset:1,limit:10,name ==*/
        Page<CompanyDto> companies = companyService.getCompanies(params);
        PageResponse response = new PageResponse(companies);

        HttpStatus httpStatus = HttpStatus.OK;
        if (companies.isEmpty()) {
            httpStatus = HttpStatus.NO_CONTENT;
        }

        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/{company-id}")
    public ResponseEntity<?> getCompany(@PathVariable("company-id") Long companyId){
        Company company = companyService.getCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK).body(companyMapper.toCompanyDto(company));
    }

    @PutMapping()
    public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyDto dto){
        Company company = companyMapper.toCompany(dto);
        var updated = companyService.updateCompany(company);
        return ResponseEntity.status(HttpStatus.OK).body(companyMapper.toCompanyDto(updated));
    }

    @DeleteMapping("/{company-id}")
    public ResponseEntity<?> delete(@PathVariable("company-id") Long companyId){
        companyService.deleteCompany(companyId);
        return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully");
    }
}
