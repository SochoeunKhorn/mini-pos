package com.sochoeun.controller;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.mapper.GeneralSettingMapper;
import com.sochoeun.model.GeneralSetting;
import com.sochoeun.service.GeneralSettingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/general-setting")
@RequiredArgsConstructor
public class GeneralSettingController {
    private final GeneralSettingService generalSettingService;
    private final GeneralSettingMapper generalSettingMapper;
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GeneralSettingDto dto) {
        GeneralSetting generalSetting = generalSettingService.createGeneralSetting(generalSettingMapper.toGeneralSetting(dto));
        return ResponseEntity.ok(generalSettingMapper.toGeneralSettingDto(generalSetting));
    }
    
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GeneralSetting> generalSettings = generalSettingService.getAllGeneralSettings();
        List<GeneralSettingDto> generalSettingDtos = generalSettings.stream()
                .map(generalSettingMapper::toGeneralSettingDto)
                .toList();
        HttpStatus status = HttpStatus.OK;
        if (generalSettings.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(status).body(generalSettingDtos);
    }
    
    @GetMapping("/{setting-id}")
    public ResponseEntity<?> getById(@PathVariable("setting-id") Long id) {
        GeneralSetting generalSetting = generalSettingService.getGeneralSetting(id);
        return ResponseEntity.ok(generalSettingMapper.toGeneralSettingDto(generalSetting));
    }

    @DeleteMapping("/{setting-id}")
    public ResponseEntity<?> delete(@PathVariable("setting-id") Long id) {
        generalSettingService.deleteGeneralSetting(id);
        return ResponseEntity.ok("GeneralSetting deleted");
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody GeneralSettingDto dto) {
        GeneralSetting generalSetting = generalSettingService.updateGeneralSetting(generalSettingMapper.toGeneralSetting(dto));
        return ResponseEntity.ok(generalSettingMapper.toGeneralSettingDto(generalSetting));
    }
}
