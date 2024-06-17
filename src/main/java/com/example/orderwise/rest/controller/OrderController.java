package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.rest.api.OrderApi;
import com.example.orderwise.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    public OrderDto save(OrderDto order) {
        return orderService.save(order);
    }

    @Override
    public OrderDto update(OrderDto order) {
        return orderService.update(order);
    }

    @Override
    public void delete(Long id) {
        orderService.delete(id);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return orderService.findById(id);
    }

    @Override
    public List<OrderDto> getAllOrder() {
        return orderService.findAll();
    }

    public List<OrderDto> getOrderBySellerUsername(String username) {
        return orderService.findBySellerUsername(username);
    }

    @Override
    public Page<OrderDto> search(String query, Integer page, Integer size, String order, String sort) {
        return orderService.rsqlQuery(query, page, size, order, sort);
    }
}
