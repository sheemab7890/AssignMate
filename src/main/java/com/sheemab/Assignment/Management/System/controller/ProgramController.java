package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.ProgramRequestDto;
import com.sheemab.Assignment.Management.System.dto.ProgramResponseDto;
import com.sheemab.Assignment.Management.System.service.ProgramService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/programs")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class ProgramController {

    private final ProgramService programService;

    @PostMapping
    public ResponseEntity<ProgramResponseDto> createProgram(
            @Valid @RequestBody ProgramRequestDto dto) {

        return ResponseEntity.ok(programService.createProgram(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponseDto>> getAllPrograms() {
        return ResponseEntity.ok(programService.getAllPrograms());
    }
}

