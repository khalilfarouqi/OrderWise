package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.OrderAssignmentDto;
import com.example.orderwise.entity.OrderAssignment;
import com.example.orderwise.repository.OrderAssignmentRepository;
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
public class OrderAssignmentService implements IBaseService<OrderAssignment, OrderAssignmentDto> {
    private final OrderAssignmentRepository orderAssignmentRepository;
    private ModelMapper modelMapper;
    @Override
    public OrderAssignmentDto save(OrderAssignmentDto dto) throws Exception {
        return null;
    }

    @Override
    public OrderAssignmentDto update(OrderAssignmentDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderAssignmentDto findById(Long id) {
        return modelMapper.map(orderAssignmentRepository.findById(id), OrderAssignmentDto.class);
    }

    @Override
    public List<OrderAssignmentDto> findAll() {
        return null;
    }

    @Override
    public Page<OrderAssignmentDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
