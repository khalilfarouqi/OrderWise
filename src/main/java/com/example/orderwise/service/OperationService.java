package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.OperationDto;
import com.example.orderwise.entity.Operation;
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
public class OperationService implements IBaseService<Operation, OperationDto> {
    private final OperationRepository operationRepository;
    private ModelMapper modelMapper;
    @Override
    public OperationDto save(OperationDto dto) throws Exception {
        return modelMapper.map(operationRepository.save(modelMapper.map(dto, Operation.class)), OperationDto.class);
    }

    @Override
    public OperationDto update(OperationDto dto) {
        return modelMapper.map(operationRepository.save(modelMapper.map(dto, Operation.class)), OperationDto.class);
    }

    @Override
    public void delete(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public OperationDto findById(Long id) {
        return modelMapper.map(operationRepository.findById(id), OperationDto.class);
    }

    @Override
    public List<OperationDto> findAll() {
        return operationRepository.findAll()
                .stream()
                .map(operation -> modelMapper.map(operation, OperationDto.class))
                .toList();
    }

    @Override
    public Page<OperationDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
