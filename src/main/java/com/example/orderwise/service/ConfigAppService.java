package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.*;
import com.example.orderwise.entity.*;
import com.example.orderwise.repository.ConfigAppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ConfigAppService implements IBaseService<ConfigApp, ConfigAppDto> {
    private final ConfigAppRepository configAppRepository;
    private final ModelMapper modelMapper;
    @Override
    public ConfigAppDto findById(Long id) {
        return modelMapper.map(configAppRepository.findById(id), ConfigAppDto.class);
    }

    public ConfigAppDto getConfigOfApp() {
        return configAppRepository.findAll().stream()
                .map(configApp -> modelMapper.map(configApp, ConfigAppDto.class))
                .collect(Collectors.toList()).stream().limit(1).findFirst().orElse(null);
    }

    @Override
    public List<ConfigAppDto> findAll() {
        return configAppRepository.findAll().stream()
                .map(configApp -> modelMapper.map(configApp, ConfigAppDto.class))
                .toList();
    }

    @Override
    public ConfigAppDto save(ConfigAppDto dto) {
        return null;
    }

    @Transactional
    public ConfigAppDto update(ConfigAppDto dto) {
        return modelMapper.map(configAppRepository.save(modelMapper.map(dto, ConfigApp.class)), ConfigAppDto.class);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<ConfigAppDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
