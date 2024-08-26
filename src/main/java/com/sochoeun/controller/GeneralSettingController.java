package com.sochoeun.controller;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.dto.PageResponse;
import com.sochoeun.mapper.GeneralSettingMapper;
import com.sochoeun.model.GeneralSetting;
import com.sochoeun.service.GeneralSettingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/general-setting")
@RequiredArgsConstructor
@Tag(name = "GENERAL-SETTING")
public class GeneralSettingController {
    private final GeneralSettingService generalSettingService;
    private final GeneralSettingMapper generalSettingMapper;
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GeneralSettingDto dto) {
        GeneralSetting generalSetting = generalSettingService.createGeneralSetting(generalSettingMapper.toGeneralSetting(dto));
        return ResponseEntity.ok(generalSettingMapper.toGeneralSettingDto(generalSetting));
    }
    
    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam Map<String, String> params) {
        Page<GeneralSettingDto> allGeneralSettings = generalSettingService.getAllGeneralSettings(params);
        PageResponse response = new PageResponse(allGeneralSettings);

        HttpStatus status = HttpStatus.OK;
        if (allGeneralSettings.isEmpty()){
            status = HttpStatus.NO_CONTENT;
        }
        return ResponseEntity.status(status).body(response);
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
