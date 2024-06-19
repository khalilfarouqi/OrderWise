package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.ProductDto;
import com.example.orderwise.rest.api.ProductApi;
import com.example.orderwise.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductController implements ProductApi {
    private final ProductService productService;

    @Override
    public ProductDto save(ProductDto product) {
        return productService.save(product);
    }

    @Override
    public ProductDto update(ProductDto product) {
        return productService.update(product);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productService.findById(id);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productService.findAll();
    }

    public List<ProductDto> getProductsBySellerUsername(String username) {
        return productService.getProductsBySellerUsername(username);
    }

    @Override
    public Page<ProductDto> search(String query, Integer page, Integer size, String order, String sort) {
        return productService.rsqlQuery(query, page, size, order, sort);
    }
}
