package com.sheemab.Assignment.Management.System.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchRequestDto {

    @NotBlank
    private String batchName;

    @NotNull
    private Long sectionId;

    @NotNull
    private Long labId;
}

