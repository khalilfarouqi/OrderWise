package com.example.orderwise.security.controller;

import com.example.orderwise.security.service.KeycloakAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.orderwise.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/keycloak")
public class KeycloakController {

}
