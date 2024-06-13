package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.NotificationGroupDto;
import com.example.orderwise.entity.NotificationGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationGroupService implements IBaseService<NotificationGroup, NotificationGroupDto> {
    @Override
    public NotificationGroupDto save(NotificationGroupDto dto) {
        return null;
    }

    @Override
    public NotificationGroupDto update(NotificationGroupDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public NotificationGroupDto findById(Long id) {
        return null;
    }

    @Override
    public List<NotificationGroupDto> findAll() {
        return List.of();
    }

    @Override
    public Page<NotificationGroupDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}