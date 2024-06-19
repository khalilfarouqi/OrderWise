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
    private final ModelMapper modelMapper;
    @Override
    public ProductDto save(ProductDto dto) {
        return modelMapper.map(productRepository.save(modelMapper.map(dto, Product.class)), ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto dto) {
        return modelMapper.map(productRepository.save(modelMapper.map(dto, Product.class)), ProductDto.class);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto findById(Long id) {
        return modelMapper.map(productRepository.findById(id), ProductDto.class);
    }

    public List<ProductDto> getProductsBySellerUsername(String username) {
        return productRepository.getProductsBySellerUsername(username)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public Page<ProductDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
