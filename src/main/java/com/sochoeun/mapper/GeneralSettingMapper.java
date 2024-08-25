package com.sochoeun.mapper;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.model.GeneralSetting;
import org.springframework.stereotype.Service;

@Service
public class GeneralSettingMapper {

    public GeneralSetting toGeneralSetting(GeneralSettingDto dto) {
        return GeneralSetting.builder()
                .id(dto.getId())
                .siteTitle(dto.getSiteTitle())
                .siteLogo(dto.getSiteLogo())
                .sitePhone(dto.getSitePhone())
                .siteAddress(dto.getSiteAddress())
                .build();
    }

    public GeneralSettingDto toGeneralSettingDto(GeneralSetting generalSetting) {
        return GeneralSettingDto.builder()
                .id(generalSetting.getId())
                .siteTitle(generalSetting.getSiteTitle())
                .siteLogo(generalSetting.getSiteLogo())
                .sitePhone(generalSetting.getSitePhone())
                .siteAddress(generalSetting.getSiteAddress())
                .build();
    }
}
