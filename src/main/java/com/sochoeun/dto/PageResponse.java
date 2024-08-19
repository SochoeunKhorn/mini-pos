package com.sochoeun.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageResponse {
    private List<?> content;
    private PageDto pageDto;

    public PageResponse(Page<?> page) {
        this.content = page.getContent();
        this.pageDto = PageDto.builder()
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .size(page.getSize())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}
