package com.sheemab.Assignment.Management.System.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentEnrollmentResponse {

    private String courseCode;
    private String courseName;

    private String facultyName;

    private String labNumber;
    private String labName;

    private String semester;
    private String academicYear;
}

