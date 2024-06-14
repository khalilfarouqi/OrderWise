package com.example.orderwise.rest.api;

import com.example.orderwise.bean.ChangePasswordRequest;
import com.example.orderwise.common.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Tag(name = "User", description = "REST API for User information")
@RequestMapping("/v1/user")
public interface UserApi {
    @PostMapping
    UserDto save(@RequestBody UserDto user);
    @PutMapping
    UserDto update(@RequestBody UserDto user);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
    @GetMapping(value = "/profile/{username}")
    UserDto findByUsername(@PathVariable("username") String username);
    @GetMapping(value = "/getAll")
    List<UserDto> getAllUser();
    @GetMapping(value = "/search")
    Page<UserDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
    @PostMapping("/profile-image")
    ResponseEntity<Map<String, String>> uploadProfileImage(@RequestParam("file") MultipartFile file);
    @PostMapping("/change-password")
    void changePassword(@RequestBody ChangePasswordRequest request);
}
