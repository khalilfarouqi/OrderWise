package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.entity.User;
import com.example.orderwise.repository.UserRepository;
import com.example.orderwise.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements IBaseService<User, UserDto> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(a ->{
                    throw new BusinessException(String.format("User with the same username [%s] exist", dto.getUsername()));
                });
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(a ->{
                    throw new BusinessException(String.format("User with the same Email [%s] exist", dto.getEmail()));
                });
        dto.setPassword(encoder.encode(dto.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(dto, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto dto) {
        dto.setPassword(encoder.encode(dto.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(dto, User.class)), UserDto.class);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        return modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public Page<UserDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
