package com.sheemab.Assignment.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SectionResponseDto {
    private Long id;
    private String sectionName;
    private String mentorName;
}

