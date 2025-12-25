package com.sheemab.Assignment.Management.System.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramRequestDto {

    @NotBlank
    private String programName;

    @NotNull
    private Long coordinatorId; // faculty user id
}

