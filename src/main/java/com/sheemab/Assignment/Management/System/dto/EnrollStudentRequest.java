package com.sheemab.Assignment.Management.System.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollStudentRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseOfferingId;
}

