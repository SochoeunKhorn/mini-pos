package com.sochoeun.repository;

import com.sochoeun.dto.GeneralSettingDto;
import com.sochoeun.model.GeneralSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting,Long> {
    Optional<GeneralSetting> findByIdAndDeletedFalse(Long id);
    List<GeneralSetting> findAllByDeletedFalse();
    Page<GeneralSettingDto> findAllByDeletedFalse(Pageable pageable);
}
