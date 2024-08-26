package com.sochoeun.service;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.model.GeneralSetting;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface GeneralSettingService {

    GeneralSetting createGeneralSetting(GeneralSetting request);
    Page<GeneralSettingDto> getAllGeneralSettings(Map<String,String> params);
    GeneralSetting getGeneralSetting(Long id);
    GeneralSetting updateGeneralSetting(GeneralSetting request);
    void deleteGeneralSetting(Long id);
}
