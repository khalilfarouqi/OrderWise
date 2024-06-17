package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.entity.Order;
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
    private final ModelMapper modelMapper;
    @Override
    public OrderDto save(OrderDto dto) {
        return modelMapper.map(orderRepository.save(modelMapper.map(dto, Order.class)), OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto dto) {
        return modelMapper.map(orderRepository.save(modelMapper.map(dto, Order.class)), OrderDto.class);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDto findById(Long id) {
        return modelMapper.map(orderRepository.findById(id), OrderDto.class);
    }

    public List<OrderDto> findBySellerUsername(String username) {
        return orderRepository.findBySellerUsername(username)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    @Override
    public Page<OrderDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
