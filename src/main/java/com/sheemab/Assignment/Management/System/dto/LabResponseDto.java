package com.sheemab.Assignment.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LabResponseDto {
    private Long id;
    private String labName;
    private String labInchargeName;
}

