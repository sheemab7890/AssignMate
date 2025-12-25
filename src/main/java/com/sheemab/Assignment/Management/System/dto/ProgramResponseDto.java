package com.sheemab.Assignment.Management.System.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramResponseDto {

    private Long id;
    private String programName;
    private String coordinatorName;
}

