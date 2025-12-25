package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.SubjectRequestDto;
import com.sheemab.Assignment.Management.System.dto.SubjectResponseDto;
import com.sheemab.Assignment.Management.System.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/subjects")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(
            @Valid @RequestBody SubjectRequestDto dto) {

        return ResponseEntity.ok(subjectService.createSubject(dto));
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<SubjectResponseDto>> getBySemester(
            @PathVariable Long semesterId) {

        return ResponseEntity.ok(
                subjectService.getSubjectsBySemester(semesterId)
        );
    }
}

