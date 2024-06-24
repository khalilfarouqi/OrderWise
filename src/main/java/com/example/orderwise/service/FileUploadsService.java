package com.example.orderwise.service;

import com.example.orderwise.bean.FileUploadResponse;

import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FileUploadsService {
    @Value("${upload.dir}")
    private String uploadDir;

    private static final Logger LOGGER = Logger.getLogger(FileUploadsService.class.getName());

    public ResponseEntity<?> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file name and sanitize it
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            // Ensure the upload directory exists
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Check if the file already exists
            Path filePath = uploadPath.resolve(fileName);
            if (Files.exists(filePath)) {
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }

            // Save the file locally
            Files.copy(file.getInputStream(), filePath);

            // Return the file download URL
            String fileDownloadUri = "assets/" + fileName;
            return ResponseEntity.ok().body(new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize()));

        } catch (IOException e) {
            LOGGER.severe("File upload failed: " + e.getMessage());
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Resource> getFile(String filename) {
        try {
            Path file = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
