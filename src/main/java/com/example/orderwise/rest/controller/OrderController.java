package com.example.orderwise.rest.controller;

import com.example.orderwise.bean.ConfirmationDashboardStatsBean;
import com.example.orderwise.bean.ConfirmedTreatedBean;
import com.example.orderwise.bean.DashboardBean;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.UserType;
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

    public DashboardBean dashState(String username) {
        return orderService.dashState(username);
    }

    public ConfirmationDashboardStatsBean confirmationDashState(String username) {
        return orderService.confirmationDashState(username);
    }

    public DashboardBean dashStateAllOrder() {
        return orderService.dashStateAllOrder();
    }

    @Override
    public Page<OrderDto> search(String query, Integer page, Integer size, String order, String sort) {
        return orderService.rsqlQuery(query, page, size, order, sort);
    }

    public List<OrderDto> getOrdersConfirm(String sellerUsername) {
        return orderService.getOrdersConfirm(sellerUsername);
    }

    public List<OrderDto> getOrdersDeliver(String sellerUsername) {
        return orderService.getOrdersDeliver(sellerUsername);
    }

    public List<OrderDto> getOrdersReturn(String sellerUsername) {
        return orderService.getOrdersReturn(sellerUsername);
    }

    @Override
    public List<OrderDto> getAllByStage(Stage stage) {
        return orderService.getAllByStage(stage);
    }

    public List<OrderDto> getAllByStageAndUserType(Stage stage, UserType userType) {
        return orderService.getAllByStageAndUserType(stage, userType);
    }

    public List<OrderDto> findOrderToDeliver(String username) {
        return orderService.findOrderToDeliver(username);
    }

    @Override
    public List<ConfirmedTreatedBean> getConfirmedTreated(String username) {
        return orderService.getConfirmedTreated(username);
    }

    @Override
    public List<OrderDto> getOrdersConfirmByConfirmed(String username) {
        return orderService.getOrdersConfirmByConfirmed(username);
    }

    @Override
    public List<OrderDto> getAllOrdersConfirm() {
        return orderService.getAllOrdersConfirm();
    }

    @Override
    public List<OrderDto> getAllOrdersDeliver() {
        return orderService.getAllOrdersDeliver();
    }

    @Override
    public List<OrderDto> getAllOrdersReturn() {
        return orderService.getAllOrdersReturn();
    }
}
