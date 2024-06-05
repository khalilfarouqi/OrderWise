package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.*;
import com.example.orderwise.entity.*;
import com.example.orderwise.repository.ContactUsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactUsService implements IBaseService<ContactUs, ContactUsDto> {
    private final ContactUsRepository contactUsRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public ContactUsDto save(ContactUsDto dto) {
        dto.setDateEnvoy(new Date());
        dto.setIsRead(false);
        return modelMapper.map(contactUsRepository.save(modelMapper.map(dto, ContactUs.class)), ContactUsDto.class);
    }

    @Override
    public ContactUsDto update(ContactUsDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ContactUsDto findById(Long id) {
        return null;
    }

    @Override
    public List<ContactUsDto> findAll() {
        return List.of();
    }

    @Override
    public Page<ContactUsDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
