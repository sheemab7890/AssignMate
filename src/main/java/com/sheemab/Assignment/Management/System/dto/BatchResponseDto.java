package com.sheemab.Assignment.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatchResponseDto {
    private Long id;
    private String batchName;
    private String sectionName;
    private String labName;
}

