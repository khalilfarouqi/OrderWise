package com.example.orderwise.rest.api;

import com.example.orderwise.bean.ConfirmationDashboardStatsBean;
import com.example.orderwise.bean.ConfirmedTreatedBean;
import com.example.orderwise.bean.DashboardBean;
import com.example.orderwise.bean.DeliveryBoyDashStatsBean;
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
    @GetMapping(value = "/confirmation-dashboard-state/{username}")
    ConfirmationDashboardStatsBean confirmationDashState(@PathVariable("username") String username);
    @GetMapping(value = "/dashboard-state")
    DashboardBean dashStateAllOrder();
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
    @GetMapping(value = "/confirm")
    List<OrderDto> getAllOrdersConfirm();
    @GetMapping(value = "/deliver")
    List<OrderDto> getAllOrdersDeliver();
    @GetMapping(value = "/return")
    List<OrderDto> getAllOrdersReturn();
    @GetMapping(value = "/by-stage/{stage}")
    List<OrderDto> getAllByStage(@PathVariable("stage") Stage stage);
    @GetMapping(value = "/by-stage-and-userType/{stage}/{userType}")
    List<OrderDto> getAllByStageAndUserType(@PathVariable("stage") Stage stage, @PathVariable("userType") UserType userType);
    @GetMapping(value = "/order-to-deliver/{username}")
    List<OrderDto> findOrderToDeliver(@PathVariable("username") String username);
    @GetMapping(value = "/confirmed-treated/{username}")
    List<ConfirmedTreatedBean> getConfirmedTreated(@PathVariable("username") String username);
    @GetMapping(value = "/by-confirmed/{username}")
    List<OrderDto> getOrdersConfirmByConfirmed(@PathVariable("username") String username);
    @GetMapping(value = "/delivery-boy-dashboard-state/{username}")
    DeliveryBoyDashStatsBean deliveryBoyDashState(@PathVariable("username") String username);
    @GetMapping(value = "/delivered-by-delivery-boy/{username}")
    List<OrderDto> getAllOrdersDeliveredByDeliveryBoy(@PathVariable("username") String username);
    @GetMapping(value = "/returned-by-delivery-boy/{username}")
    List<OrderDto> getAllOrdersReturnedByDeliveryBoy(@PathVariable("username") String username);
}
