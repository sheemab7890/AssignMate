package com.sheemab.Assignment.Management.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev")
public class DevController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/bcrypt")
    public String generate() {
        return passwordEncoder.encode("123456");
    }
}

