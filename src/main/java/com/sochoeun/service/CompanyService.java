package com.sochoeun.service;

import com.sochoeun.dto.CompanyDto;
import com.sochoeun.model.Company;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CompanyService {
    /*
    *
    * CRUD :
    *   - Create
    *   - Get List with Pagination
    *   - Get
    *   - Update
    *   - Delete (soft delete)
    * */

    Company createCompany(Company company);
    Page<CompanyDto> getCompanies(Map<String,String> params);
    Company getCompany(Long companyId);
    Company updateCompany(Company company);
    void deleteCompany(Long companyId);
}
