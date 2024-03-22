package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.OperationDto;
import com.example.orderwise.entity.Operation;
import com.example.orderwise.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class OperationService implements IBaseService<Operation, OperationDto> {
    private final OperationRepository carRepository;
    private ModelMapper modelMapper;
    @Override
    public OperationDto save(OperationDto dto) throws Exception {
        return null;
    }

    @Override
    public OperationDto update(OperationDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OperationDto findById(Long id) {
        return null;
    }

    @Override
    public List<OperationDto> findAll() {
        return null;
    }

    @Override
    public Page<OperationDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
