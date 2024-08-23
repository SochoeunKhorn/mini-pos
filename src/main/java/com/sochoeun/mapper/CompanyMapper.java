package com.sochoeun.mapper;

import com.sochoeun.dto.CompanyDto;
import com.sochoeun.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {

    /* toCompany */
    public Company toCompany(CompanyDto dto){
        return Company.builder()
                .id(dto.getId())
                .companyLocalName(dto.getCompanyLocalName())
                .companyEngName(dto.getCompanyEngName())
                .companyEmail(dto.getCompanyEmail())
                .companyPhone(dto.getCompanyPhone())
                .companyAddress(dto.getCompanyAddress())
                .vatNumber(dto.getVatNumber())
                .imagePath(dto.getImagePath())
                .image(dto.getImage())
                .build();
    }

    public CompanyDto toCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .companyLocalName(company.getCompanyLocalName())
                .companyEngName(company.getCompanyEngName())
                .companyEmail(company.getCompanyEmail())
                .companyPhone(company.getCompanyPhone())
                .companyAddress(company.getCompanyAddress())
                .vatNumber(company.getVatNumber())
                .imagePath(company.getImagePath())
                .image(company.getImage())
                .build();
    }
}
