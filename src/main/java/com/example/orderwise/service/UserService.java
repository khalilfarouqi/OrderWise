package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.entity.User;
import com.example.orderwise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements IBaseService<User, UserDto> {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public UserDto save(UserDto dto) {
        return modelMapper.map(userRepository.save(modelMapper.map(dto, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto dto) {
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
