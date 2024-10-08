package com.example.orderwise.rest.controller;

import com.example.orderwise.bean.ChangePasswordRequest;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.rest.api.UserApi;
import com.example.orderwise.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public UserDto save(UserDto user) {
        return userService.save(user);
    }

    @Override
    public UserDto update(UserDto user) {
        return userService.update(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userService.findById(id);
    }

    @Override
    public UserDto findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userService.findAll();
    }

    @Override
    public Page<UserDto> search(String query, Integer page, Integer size, String order, String sort) {
        return userService.rsqlQuery(query, page, size, order, sort);
    }

    public ResponseEntity<Map<String, String>> uploadProfileImage(MultipartFile file) {
        return userService.uploadProfileImage(file);
    }

    public void changePassword(ChangePasswordRequest request) {
        String username = request.getUsername();
        userService.changePassword(username, request);
    }

    @Override
    public List<UserDto> getAllUsersToConfirm() {
        return userService.getAllUsersToConfirm();
    }

    @Override
    public ResponseEntity<UserDto> refuse(UserDto user) {
        return userService.refusee(user);
    }

    @Override
    public ResponseEntity<UserDto> valide(UserDto user) {
        return userService.validee(user);
    }

    @Override
    public ResponseEntity<String> treatNewProfiles(UserDto user, String response) {
        return userService.treatNewProfiles(user, response);
    }

    @Override
    public ResponseEntity<String> deleteAccount(String username, String password) {
        return userService.deleteAccount(username, password);
    }

}
