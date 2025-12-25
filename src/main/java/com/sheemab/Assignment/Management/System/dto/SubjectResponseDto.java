package com.sheemab.Assignment.Management.System.dto;

import com.sheemab.Assignment.Management.System.enums.SubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectResponseDto {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private SubjectType type;
}
