package com.sochoeun.service.impl;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.GeneralSetting;
import com.sochoeun.repository.GeneralSettingRepository;
import com.sochoeun.service.GeneralSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeneralSettingServiceImpl implements GeneralSettingService {
    private final GeneralSettingRepository generalSettingRepository;
    
    @Override
    public GeneralSetting createGeneralSetting(GeneralSetting request) {
        return generalSettingRepository.save(request);
    }

    @Override
    public Page<GeneralSettingDto> getAllGeneralSettings(Map<String,String> params) {

        int offset = 0;
        int limit = 10;
        String local_name = "";
        if (params.containsKey("offset")){
            offset = Integer.parseInt(params.get("offset"));
        }
        if (params.containsKey("limit")){
            limit = Integer.parseInt(params.get("limit"));
        }

        if (offset <= 0){
            offset = 1;
        }

        Pageable pageable = PageRequest.of(offset-1,limit);
        return generalSettingRepository.findAllByDeletedFalse(pageable);
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
