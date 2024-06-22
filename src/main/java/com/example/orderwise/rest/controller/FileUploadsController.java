package com.example.orderwise.rest.controller;

import com.example.orderwise.rest.api.FileUploadsApi;
import com.example.orderwise.service.FileUploadsService;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
public class FileUploadsController implements FileUploadsApi {

    private final FileUploadsService fileUploadsService;

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file) {
        return fileUploadsService.uploadFile(file);
    }

    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        return fileUploadsService.getFile(filename);
    }

    @Override
    public String getFileUploadResponse() {
        return "aaaaa";
    }
}
