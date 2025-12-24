package com.sheemab.Assignment.Management.System.dto;

import com.sheemab.Assignment.Management.System.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCsvRow {

    private String fullName;
    private String email;
    private String password;
    private RoleName role;
}

