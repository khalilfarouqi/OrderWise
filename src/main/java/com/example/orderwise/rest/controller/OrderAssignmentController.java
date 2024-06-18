package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.OrderAssignmentDto;
import com.example.orderwise.rest.api.OrderAssignmentApi;
import com.example.orderwise.service.OrderAssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderAssignmentController implements OrderAssignmentApi {
    private final OrderAssignmentService orderAssignmentService;

    @Override
    public OrderAssignmentDto save(OrderAssignmentDto orderAssignment) {
        return orderAssignmentService.save(orderAssignment);
    }

    @Override
    public OrderAssignmentDto update(OrderAssignmentDto orderAssignment) {
        return orderAssignmentService.update(orderAssignment);
    }

    @Override
    public void delete(Long id) {
        orderAssignmentService.delete(id);
    }

    @Override
    public OrderAssignmentDto getOrderAssignmentById(Long id) {
        return orderAssignmentService.findById(id);
    }

    @Override
    public List<OrderAssignmentDto> getAllOrderAssignment() {
        return orderAssignmentService.findAll();
    }

    public List<OrderAssignmentDto> getOrderAssignmentsBySellerUsername(String username) {
        return orderAssignmentService.getOrderAssignmentsBySellerUsername(username);
    }

    @Override
    public Page<OrderAssignmentDto> search(String query, Integer page, Integer size, String order, String sort) {
        return orderAssignmentService.rsqlQuery(query, page, size, order, sort);
    }
}
