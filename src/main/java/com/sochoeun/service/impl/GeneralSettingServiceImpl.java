package com.sochoeun.service.impl;

import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.GeneralSetting;
import com.sochoeun.repository.GeneralSettingRepository;
import com.sochoeun.service.GeneralSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralSettingServiceImpl implements GeneralSettingService {
    private final GeneralSettingRepository generalSettingRepository;
    
    @Override
    public GeneralSetting createGeneralSetting(GeneralSetting request) {
        return generalSettingRepository.save(request);
    }

    @Override
    public List<GeneralSetting> getAllGeneralSettings() {
        return generalSettingRepository.findAll();
    }

    @Override
    public GeneralSetting getGeneralSetting(Long id) {
        return generalSettingRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new NotFoundException("General setting not found with id: " + id));
    }

    @Override
    public GeneralSetting updateGeneralSetting(GeneralSetting request) {
        GeneralSetting generalSetting = getGeneralSetting(request.getId());
        generalSetting.setSiteTitle(request.getSiteTitle());
        generalSetting.setSiteLogo(request.getSiteLogo());
        generalSetting.setSitePhone(request.getSitePhone());
        generalSetting.setSiteAddress(request.getSiteAddress());
        return generalSettingRepository.save(generalSetting);
    }

    @Override
    public void deleteGeneralSetting(Long id) {
        GeneralSetting generalSetting = getGeneralSetting(id);
        generalSetting.setDeleted(true);
        generalSettingRepository.save(generalSetting);
    }
}
