package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.ProductDto;
import com.example.orderwise.entity.Product;
import com.example.orderwise.repository.ProductRepository;
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
public class ProductService implements IBaseService<Product, ProductDto> {
    private final ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductDto save(ProductDto dto) throws Exception {
        return null;
    }

    @Override
    public ProductDto update(ProductDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProductDto findById(Long id) {
        return null;
    }

    @Override
    public List<ProductDto> findAll() {
        return null;
    }

    @Override
    public Page<ProductDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
