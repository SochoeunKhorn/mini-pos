package com.sochoeun.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralSettingDto {
    private Long id;
    private String siteTitle;
    private String siteLogo;
    private String sitePhone;
    private String siteAddress;
}
