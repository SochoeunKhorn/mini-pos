package com.sochoeun.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageDto {
    private Long totalElements;
    private int totalPages;
    private int limit;
    private int numberOfElements;
}
