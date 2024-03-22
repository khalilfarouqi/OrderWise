package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.StockDto;
import com.example.orderwise.entity.Stock;
import com.example.orderwise.repository.StockRepository;
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
public class StockService implements IBaseService<Stock, StockDto> {
    private final StockRepository stockRepository;
    private ModelMapper modelMapper;
    @Override
    public StockDto save(StockDto dto) throws Exception {
        return null;
    }

    @Override
    public StockDto update(StockDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public StockDto findById(Long id) {
        return null;
    }

    @Override
    public List<StockDto> findAll() {
        return null;
    }

    @Override
    public Page<StockDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
