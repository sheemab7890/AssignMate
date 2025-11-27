package com.sheemab.Assignment.Management.System.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCourseOfferingRequest {

    @NotNull
    private Long courseId;

    @NotNull
    private Long facultyId;

    @NotNull
    private Long labId;

    @NotBlank
    private String semester;

    @NotBlank
    private String academicYear;
}

