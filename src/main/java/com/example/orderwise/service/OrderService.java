package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.entity.Order;
import com.example.orderwise.repository.OperationRepository;
import com.example.orderwise.repository.OrderRepository;
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
public class OrderService implements IBaseService<Order, OrderDto> {
    private final OrderRepository orderRepository;
    private ModelMapper modelMapper;
    @Override
    public OrderDto save(OrderDto dto) throws Exception {
        return null;
    }

    @Override
    public OrderDto update(OrderDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OrderDto findById(Long id) {
        return null;
    }

    @Override
    public List<OrderDto> findAll() {
        return null;
    }

    @Override
    public Page<OrderDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
