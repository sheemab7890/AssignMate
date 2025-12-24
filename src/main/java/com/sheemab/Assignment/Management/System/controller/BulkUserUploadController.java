package com.sheemab.Assignment.Management.System.controller;

import com.sheemab.Assignment.Management.System.service.BulkUserUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class BulkUserUploadController {

    private final BulkUserUploadService bulkUserUploadService;

    @PostMapping(value = "/bulk-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadUsers(
            @RequestParam("file") MultipartFile file) {

        bulkUserUploadService.uploadUsers(file);
        return ResponseEntity.ok("Bulk user upload completed successfully");
    }
}

