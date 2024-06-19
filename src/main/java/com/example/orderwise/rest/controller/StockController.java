package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.StockDto;
import com.example.orderwise.rest.api.StockApi;
import com.example.orderwise.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class StockController implements StockApi {
    private final StockService stockService;

    @Override
    public StockDto save(StockDto stock) {
        return stockService.save(stock);
    }

    @Override
    public StockDto update(StockDto stock) {
        return stockService.update(stock);
    }

    @Override
    public void delete(Long id) {
        stockService.delete(id);
    }

    @Override
    public StockDto getStockById(Long id) {
        return stockService.findById(id);
    }

    @Override
    public List<StockDto> getAllStock() {
        return stockService.findAll();
    }

    public List<StockDto> getStocksByProductSellerUsername(String username) {
        return stockService.getStocksByProductSellerUsername(username);
    }

    @Override
    public Page<StockDto> search(String query, Integer page, Integer size, String order, String sort) {
        return stockService.rsqlQuery(query, page, size, order, sort);
    }
}
