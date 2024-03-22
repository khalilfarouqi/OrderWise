package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.CustomerDto;
import com.example.orderwise.common.dto.DestinationsDto;
import com.example.orderwise.entity.Customer;
import com.example.orderwise.entity.Destinations;
import com.example.orderwise.repository.DestinationsRepository;
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
public class DestinationsService implements IBaseService<Destinations, DestinationsDto> {
    private final DestinationsRepository destinationsRepository;
    private ModelMapper modelMapper;
    @Override
    public DestinationsDto save(DestinationsDto dto) throws Exception {
        return null;
    }

    @Override
    public DestinationsDto update(DestinationsDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DestinationsDto findById(Long id) {
        return null;
    }

    @Override
    public List<DestinationsDto> findAll() {
        return null;
    }

    @Override
    public Page<DestinationsDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
