package com.sochoeun.service.impl;

import com.sochoeun.dto.CompanyDto;
import com.sochoeun.model.Company;
import com.sochoeun.repository.CompanyRepository;
import com.sochoeun.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Page<CompanyDto> getCompanies(Map<String, String> params) {

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
            return companyRepository.findAllByIsDeletedIsFalseAndCompanyLocalNameContainingIgnoreCase(local_name,pageable);
        }else
            return companyRepository.findAllByIsDeletedIsFalse(pageable);
    }

    @Override
    public Company getCompany(Long companyId) {
        return companyRepository.findByIdAndIsDeletedFalse(companyId)
                .orElseThrow(()-> new RuntimeException("Company not found with id: " + companyId));
    }

    @Override
    public Company updateCompany(Company request) {
        Company company = getCompany(request.getId());
        company.setCompanyLocalName(request.getCompanyLocalName());
        company.setCompanyEngName(request.getCompanyEngName());
        company.setCompanyEmail(request.getCompanyEmail());
        company.setCompanyPhone(request.getCompanyPhone());
        company.setCompanyAddress(request.getCompanyAddress());
        company.setVatNumber(request.getVatNumber());
        company.setImagePath(request.getImagePath());
        company.setImage(request.getImagePath());
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = getCompany(companyId);
        if (!company.getIsDeleted()){
            company.setDeleted(true);
            companyRepository.save(company);
        }
    }
}
