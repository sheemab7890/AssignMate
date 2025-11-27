package com.sheemab.Assignment.Management.System.dto;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabResponse {

    private Long id;
    private String labNumber;
    private String labName;

    private Long labInchargeId;
    private String labInchargeName;
}

