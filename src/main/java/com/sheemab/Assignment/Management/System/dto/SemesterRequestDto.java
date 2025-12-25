package com.sheemab.Assignment.Management.System.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SemesterRequestDto {

    @Min(1) @Max(12)
    private int semesterNumber;

    @NotNull
    private Long programId;
}
