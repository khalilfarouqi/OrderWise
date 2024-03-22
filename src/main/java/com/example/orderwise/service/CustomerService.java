package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.CustomerDto;
import com.example.orderwise.entity.Customer;
import com.example.orderwise.repository.CustomerRepository;
import com.example.orderwise.repository.OperationRepository;
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
public class CustomerService implements IBaseService<Customer, CustomerDto> {
    private final CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    @Override
    public CustomerDto save(CustomerDto dto) throws Exception {
        return null;
    }

    @Override
    public CustomerDto update(CustomerDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CustomerDto findById(Long id) {
        return modelMapper.map(customerRepository.findById(id), CustomerDto.class);
    }

    @Override
    public List<CustomerDto> findAll() {
        return null;
    }

    @Override
    public Page<CustomerDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
