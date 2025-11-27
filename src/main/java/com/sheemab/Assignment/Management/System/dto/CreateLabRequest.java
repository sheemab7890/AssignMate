package com.sheemab.Assignment.Management.System.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateLabRequest {

    @NotBlank
    private String labNumber;

    @NotBlank
    private String labName;

    @NotNull
    private Long labInchargeId;
}
