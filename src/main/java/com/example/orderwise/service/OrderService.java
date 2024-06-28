package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.bean.DashboardBean;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.common.dto.WalletDto;
import com.example.orderwise.entity.Order;
import com.example.orderwise.entity.enums.Stage;
import com.example.orderwise.entity.enums.Status;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OrderService implements IBaseService<Order, OrderDto> {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    private final WalletService walletService;

    private final DashboardBean dashboardBean = new DashboardBean();

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

    public DashboardBean dashState(String username) {
        WalletDto walletDto = walletService.getWalletBySeller(username);
        List<OrderDto> orders = findBySellerUsername(username);

        Date dateOfLastConfirmation = orderRepository.getDateOfLastConfirmation(Stage.CONFIRMATION, username);
        Date dateOfLastDelivered = orderRepository.getDateOfLastDelivered(Stage.SHIPPING, username);
        Date dateOfLastReturn = orderRepository.getDateOfLastReturn(Stage.RETURN, username);

        dashboardBean.setTotalSales(walletDto.getSold());
        dashboardBean.setCurrentMonthOrderCount((int) orders.stream()
                .filter(orderDto -> {
                    Calendar orderCalendar = Calendar.getInstance();
                    orderCalendar.setTime(orderDto.getOrderDate());
                    Calendar currentCalendar = Calendar.getInstance();
                    return orderCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)
                            && orderCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR);
                })
                .count());
        dashboardBean.setCurrentDayOrderCount((int) orders.stream()
                .filter(orderDto -> Objects.equals(orderDto.getOrderDate(), Calendar.getInstance().getTime()))
                .count());
        dashboardBean.setOrdersRejectedInConfirmation((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.CANCEL) && orderDto.getStage().equals(Stage.CONFIRMATION))
                .count());
        dashboardBean.setOrdersRejectedInDelivery((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.CANCEL) && orderDto.getStage().equals(Stage.SHIPPING))
                .count());
        dashboardBean.setOrdersInProgress((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.INPROGRESS))
                .count());
        dashboardBean.setOrdersNotTreated((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.PENDING))
                .count());
        dashboardBean.setOrdersReturned((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.RETURN))
                .count());
        dashboardBean.setOrdersValidated((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.CONFIRMED))
                .count());
        dashboardBean.setOrdersToConfirm((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.WAITING) && orderDto.getStage().equals(Stage.CONFIRMATION))
                .count());
        if (dateOfLastConfirmation != null)
            dashboardBean.setDateOrdersToConfirm((int) (new Date().getTime() - dateOfLastConfirmation.getTime()));
        dashboardBean.setOrdersToDeliver((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.WAITING) && orderDto.getStage().equals(Stage.SHIPPING))
                .count());
        if (dateOfLastDelivered != null)
            dashboardBean.setDateOrdersToDeliver((int) (new Date().getTime() - dateOfLastDelivered.getTime()));
        dashboardBean.setOrdersToReturn((int) orders.stream()
                .filter(orderDto -> orderDto.getStatus().equals(Status.WAITING) && orderDto.getStage().equals(Stage.RETURN))
                .count());
        if (dateOfLastReturn != null)
            dashboardBean.setDateOrdersToReturn((int) (new Date().getTime() - dateOfLastReturn.getTime()));
        return dashboardBean;
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

    public List<OrderDto> getOrdersConfirm(String sellerUsername) {
        return orderRepository.getAllByStageAndSeller(Stage.CONFIRMATION, sellerUsername)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public List<OrderDto> getOrdersDeliver(String sellerUsername) {
        return orderRepository.getAllByStageAndSeller(Stage.SHIPPING, sellerUsername)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public List<OrderDto> getOrdersReturn(String sellerUsername) {
        return orderRepository.getAllByStageAndSeller(Stage.RETURN, sellerUsername)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public List<OrderDto> getAllByStage(Stage stage) {
        return orderRepository.getAllByStage(stage)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }

    public List<OrderDto> getAllByStageAndUserType(Stage stage, UserType userType) {
        return orderRepository.getAllByStageAndUserType(stage, userType)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .toList();
    }
}
