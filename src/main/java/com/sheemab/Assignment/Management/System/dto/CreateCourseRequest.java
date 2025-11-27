package com.sheemab.Assignment.Management.System.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCourseRequest {

    @NotBlank
    private String code;

    @NotBlank
    private String name;
}

