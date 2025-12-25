package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.SemesterRequestDto;
import com.sheemab.Assignment.Management.System.dto.SemesterResponseDto;
import com.sheemab.Assignment.Management.System.entity.Semester;
import com.sheemab.Assignment.Management.System.service.SemesterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/semesters")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SemesterController {

    private final SemesterService semesterService;

    @PostMapping
    public ResponseEntity<SemesterResponseDto> createSemester(
            @Valid @RequestBody SemesterRequestDto dto) {

        return ResponseEntity.ok(semesterService.createSemester(dto));
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<List<SemesterResponseDto>> getByProgram(
            @PathVariable Long programId) {

        return ResponseEntity.ok(
                semesterService.getSemestersByProgram(programId)
        );
    }
}

