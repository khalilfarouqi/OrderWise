package com.example.orderwise.rest.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@Tag(name = "FileUploads", description = "REST API for File Uploads information")
@RequestMapping("/v1/fileUploads")
public interface FileUploadsApi {
    @PostMapping
    ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file);
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    ResponseEntity<Resource> getFile(@PathVariable String filename);

    @GetMapping(value = "/getAll")
    String getFileUploadResponse();
}
