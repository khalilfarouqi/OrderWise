package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.CartDto;
import com.example.orderwise.entity.Cart;
import com.example.orderwise.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService implements IBaseService<Cart, CartDto> {
    private final CartRepository cartRepository;
    @Override
    public CartDto save(CartDto dto) throws Exception {
        return null;
    }

    @Override
    public CartDto update(CartDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public CartDto findById(Long id) {
        return null;
    }

    @Override
    public List<CartDto> findAll() {
        return null;
    }

    @Override
    public Page<CartDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
