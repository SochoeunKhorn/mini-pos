package com.sochoeun.service;

import com.sochoeun.model.GeneralSetting;

import java.util.List;

public interface GeneralSettingService {

    GeneralSetting createGeneralSetting(GeneralSetting request);
    List<GeneralSetting> getAllGeneralSettings();
    GeneralSetting getGeneralSetting(Long id);
    GeneralSetting updateGeneralSetting(GeneralSetting request);
    void deleteGeneralSetting(Long id);
}
