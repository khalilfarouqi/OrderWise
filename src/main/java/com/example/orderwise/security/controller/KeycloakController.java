package com.example.orderwise.security.controller;

import com.example.orderwise.security.service.KeycloakAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/keycloak")
public class KeycloakController {
    private final KeycloakAdminService keycloakAdminService;
    @GetMapping("/removeUserRole/{userId}/{roleName}")
    ResponseEntity<String> removeUserRole(@PathVariable("userId") String userId, @PathVariable("roleName") String roleName) {
        return keycloakAdminService.removeUserRole(userId, roleName);
    }
    @GetMapping("/assignUserRole/{userId}/{roleName}")
    ResponseEntity<String> assignUserRole(@PathVariable("userId") String userId, @PathVariable("roleName") String roleName) {
        return keycloakAdminService.assignUserRole(userId, roleName);
    }

}
