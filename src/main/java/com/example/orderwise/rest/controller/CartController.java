package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.CartDto;
import com.example.orderwise.rest.api.CartApi;
import com.example.orderwise.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CartController implements CartApi {
    private final CartService cartService;

    @Override
    public CartDto save(CartDto cart) {
        return cartService.save(cart);
    }

    @Override
    public CartDto update(CartDto cart) {
        return cartService.update(cart);
    }

    @Override
    public void delete(Long id) {
        cartService.delete(id);
    }

    @Override
    public CartDto getCartById(Long id) {
        return cartService.findById(id);
    }

    @Override
    public List<CartDto> getAllCart() {
        return cartService.findAll();
    }

    @Override
    public Page<CartDto> search(String query, Integer page, Integer size, String order, String sort) {
        return cartService.rsqlQuery(query, page, size, order, sort);
    }
}
