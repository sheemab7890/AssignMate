package com.sheemab.Assignment.Management.System.controller;


import com.sheemab.Assignment.Management.System.dto.CreateLabRequest;
import com.sheemab.Assignment.Management.System.dto.LabResponse;
import com.sheemab.Assignment.Management.System.service.LabService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LabResponse> createLab(
            @Valid @RequestBody CreateLabRequest request
    ) {
        LabResponse response = labService.createLab(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'FACULTY', 'LAB_INCHARGE')")
    @GetMapping
    public ResponseEntity<List<LabResponse>> getAllLabs() {
        return ResponseEntity.ok(labService.getAllLabs());
    }
}

