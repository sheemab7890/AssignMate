package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.dto.BatchRequestDto;
import com.sheemab.Assignment.Management.System.dto.BatchResponseDto;
import com.sheemab.Assignment.Management.System.entity.Batch;
import com.sheemab.Assignment.Management.System.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/batches")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<BatchResponseDto> createBatch(
            @Valid @RequestBody BatchRequestDto dto) {

        return ResponseEntity.ok(batchService.createBatch(dto));
    }

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<BatchResponseDto>> getBySection(
            @PathVariable Long sectionId) {

        return ResponseEntity.ok(
                batchService.getBatchesBySection(sectionId)
        );
    }
}

