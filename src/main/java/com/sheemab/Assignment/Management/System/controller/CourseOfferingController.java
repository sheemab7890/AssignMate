package com.sheemab.Assignment.Management.System.controller;


import com.sheemab.Assignment.Management.System.dto.CourseOfferingResponse;
import com.sheemab.Assignment.Management.System.dto.CreateCourseOfferingRequest;
import com.sheemab.Assignment.Management.System.service.CourseOfferingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-offerings")
@RequiredArgsConstructor
public class CourseOfferingController {

    private final CourseOfferingService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseOfferingResponse> createOffering(
            @Valid @RequestBody CreateCourseOfferingRequest request
    ) {
        CourseOfferingResponse response = service.createOffering(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasRole('FACULTY')")
    @GetMapping("/faculty")
    public ResponseEntity<List<CourseOfferingResponse>> getForFaculty() {
        return ResponseEntity.ok(service.getOfferingsForFaculty());
    }

    @PreAuthorize("hasRole('LAB_INCHARGE')")
    @GetMapping("/lab-incharge")
    public ResponseEntity<List<CourseOfferingResponse>> getForLabIncharge() {
        return ResponseEntity.ok(service.getOfferingsForLabIncharge());
    }
}

