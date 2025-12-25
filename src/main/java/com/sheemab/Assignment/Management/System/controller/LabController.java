package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.LabRequestDto;
import com.sheemab.Assignment.Management.System.dto.LabResponseDto;
import com.sheemab.Assignment.Management.System.entity.Lab;
import com.sheemab.Assignment.Management.System.service.LabService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/labs")
@RequiredArgsConstructor
public class LabController {

    private final LabService labService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LabResponseDto> createLab(
            @Valid @RequestBody LabRequestDto dto) {

        return ResponseEntity.ok(labService.createLab(dto));
    }

//    @PreAuthorize("hasAnyRole('ADMIN','LAB_INCHARGE')")
//    @GetMapping
//    public ResponseEntity<List<LabResponseDto>> getAllLabs() {
//
//        return ResponseEntity.ok(labService.getAllLabs());
//    }
}

