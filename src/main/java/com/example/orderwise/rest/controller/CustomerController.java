package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.CustomerDto;
import com.example.orderwise.rest.api.CustomerApi;
import com.example.orderwise.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController implements CustomerApi {
    private final CustomerService customerService;

    @Override
    public CustomerDto save(CustomerDto customer) {
        return customerService.save(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        return customerService.update(customer);
    }

    @Override
    public void delete(Long id) {
        customerService.delete(id);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerService.findById(id);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return customerService.findAll();
    }

    @Override
    public Page<CustomerDto> search(String query, Integer page, Integer size, String order, String sort) {
        return customerService.rsqlQuery(query, page, size, order, sort);
    }
}
