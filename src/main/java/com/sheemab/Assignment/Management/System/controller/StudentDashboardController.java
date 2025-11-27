package com.sheemab.Assignment.Management.System.controller;


import com.sheemab.Assignment.Management.System.dto.StudentEnrollmentResponse;
import com.sheemab.Assignment.Management.System.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentDashboardController {

    private final EnrollmentService enrollmentService;

    //STUDENT ONLY
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/my-enrollments")
    public ResponseEntity<List<StudentEnrollmentResponse>> myEnrollments() {
        return ResponseEntity.ok(enrollmentService.getMyEnrollments());
    }
}

