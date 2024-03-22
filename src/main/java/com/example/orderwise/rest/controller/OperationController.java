package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.OperationDto;
import com.example.orderwise.rest.api.OperationApi;
import com.example.orderwise.service.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OperationController implements OperationApi {
    private final OperationService operationService;

    @Override
    public OperationDto save(OperationDto operation) {
        return operationService.save(operation);
    }

    @Override
    public OperationDto update(OperationDto operation) {
        return operationService.update(operation);
    }

    @Override
    public void delete(Long id) {
        operationService.delete(id);
    }

    @Override
    public OperationDto getOperationById(Long id) {
        return operationService.findById(id);
    }

    @Override
    public List<OperationDto> getAllOperation() {
        return operationService.findAll();
    }

    @Override
    public Page<OperationDto> search(String query, Integer page, Integer size, String order, String sort) {
        return operationService.rsqlQuery(query, page, size, order, sort);
    }
}
