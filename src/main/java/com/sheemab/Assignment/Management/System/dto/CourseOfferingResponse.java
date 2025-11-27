package com.sheemab.Assignment.Management.System.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseOfferingResponse {

    private Long id;

    private String courseCode;
    private String courseName;

    private Long facultyId;
    private String facultyName;

    private String labNumber;
    private String labName;

    private String semester;
    private String academicYear;

    private Long labInchargeId;
    private String labInchargeName;
}
