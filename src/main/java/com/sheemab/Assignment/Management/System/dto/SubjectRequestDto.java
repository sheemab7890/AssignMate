package com.sheemab.Assignment.Management.System.dto;

import com.sheemab.Assignment.Management.System.enums.SubjectType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequestDto {

    @NotBlank
    private String subjectCode;

    @NotBlank
    private String subjectName;

    @NotNull
    private SubjectType type; // THEORY / LAB

    @NotNull
    private Long semesterId;
}

