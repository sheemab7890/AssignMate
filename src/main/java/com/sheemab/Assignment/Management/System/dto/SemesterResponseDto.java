package com.sheemab.Assignment.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SemesterResponseDto {
    private Long id;
    private Integer semesterNumber;
    private Long programId;
    private String programName;
}


