package com.example.orderwise.rest.api;

import com.example.orderwise.bean.DashboardBean;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.UserType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Order", description = "REST API for Order information")
@RequestMapping("/v1/order")
public interface OrderApi {
    @PostMapping
    OrderDto save(@RequestBody OrderDto order);
    @PutMapping
    OrderDto update(@RequestBody OrderDto order);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    OrderDto getOrderById(@PathVariable("id") Long id);
    @GetMapping(value = "/seller/{username}")
    List<OrderDto> getOrderBySellerUsername(@PathVariable("username") String username);
    @GetMapping(value = "/dashboard-state/{username}")
    DashboardBean dashState(@PathVariable("username") String username);
    @GetMapping(value = "/getAll")
    List<OrderDto> getAllOrder();
    @GetMapping(value = "/search")
    Page<OrderDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
    @GetMapping(value = "/confirm/{username}")
    List<OrderDto> getOrdersConfirm(@PathVariable("username") String username);
    @GetMapping(value = "/deliver/{username}")
    List<OrderDto> getOrdersDeliver(@PathVariable("username") String username);
    @GetMapping(value = "/return/{username}")
    List<OrderDto> getOrdersReturn(@PathVariable("username") String username);
    @GetMapping(value = "/by-stage/{stage}")
    List<OrderDto> getAllByStage(@PathVariable("stage") Stage stage);
    @GetMapping(value = "/getAllByStageAndUserType/{stage}/{userType}")
    List<OrderDto> getAllByStageAndUserType(@PathVariable("stage") Stage stage, @PathVariable("userType") UserType userType);
}
