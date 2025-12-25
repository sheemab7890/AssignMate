package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.SectionRequestDto;
import com.sheemab.Assignment.Management.System.dto.SectionResponseDto;
import com.sheemab.Assignment.Management.System.entity.Section;
import com.sheemab.Assignment.Management.System.service.SectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sections")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<SectionResponseDto> createSection(
            @Valid @RequestBody SectionRequestDto dto) {

        return ResponseEntity.ok(sectionService.createSection(dto));
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<SectionResponseDto>> getBySemester(
            @PathVariable Long semesterId) {

        return ResponseEntity.ok(
                sectionService.getSectionsBySemester(semesterId)
        );
    }
}

