package com.sheemab.Assignment.Management.System.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabRequestDto {

    @NotBlank
    private String labName;

    @NotNull
    private Long labInchargeId;
}

