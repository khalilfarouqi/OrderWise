package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.CustomerDto;
import com.example.orderwise.common.dto.NotificationDto;
import com.example.orderwise.entity.Notification;
import com.example.orderwise.repository.NotificationRepository;
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
public class NotificationService implements IBaseService<Notification, NotificationDto> {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    @Override
    public NotificationDto save(NotificationDto dto) {
        return null;
    }

    @Override
    public NotificationDto update(NotificationDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public NotificationDto findById(Long id) {
        return null;
    }

    @Override
    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .toList();
    }

    @Override
    public Page<NotificationDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
