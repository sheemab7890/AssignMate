package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.EnrollStudentRequest;
import com.sheemab.Assignment.Management.System.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // ONLY ADMIN CAN ENROLL
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> enrollStudent(
            @Valid @RequestBody EnrollStudentRequest request
    ) {
        enrollmentService.adminEnrollStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }// Admin -> Enroll Student
}
