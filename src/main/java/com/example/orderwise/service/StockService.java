package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.ProductDto;
import com.example.orderwise.common.dto.StockDto;
import com.example.orderwise.entity.Stock;
import com.example.orderwise.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StockService implements IBaseService<Stock, StockDto> {
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    private final ProductService productService;

    @Override
    public StockDto save(StockDto dto) {
        return modelMapper.map(stockRepository.save(modelMapper.map(dto, Stock.class)), StockDto.class);
    }

    @Override
    @Transactional
    public StockDto update(StockDto dto) {
        dto.setLastModifiedDate(new Date());
        dto.setProduct(productService.update(dto.getProduct()));
        return modelMapper.map(stockRepository.save(modelMapper.map(dto, Stock.class)), StockDto.class);
    }

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public StockDto findById(Long id) {
        return modelMapper.map(stockRepository.findById(id), StockDto.class);
    }

    @Override
    public List<StockDto> findAll() {
        return stockRepository.findAll()
                .stream()
                .map(stock -> modelMapper.map(stock, StockDto.class))
                .toList();
    }

    public List<StockDto> getStocksByProductSellerUsername(String username) {
        return stockRepository.getStocksByProductSellerUsername(username)
                .stream()
                .map(stock -> modelMapper.map(stock, StockDto.class))
                .toList();
    }

    @Override
    public Page<StockDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
