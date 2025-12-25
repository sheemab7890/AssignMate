package com.sheemab.Assignment.Management.System.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionRequestDto {

    @NotBlank
    private String sectionName;

    @NotNull
    private Long semesterId;

    @NotNull
    private Long mentorId; // faculty user id
}

