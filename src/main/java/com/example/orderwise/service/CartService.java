package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.CartDto;
import com.example.orderwise.entity.Cart;
import com.example.orderwise.repository.CartRepository;
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
public class CartService implements IBaseService<Cart, CartDto> {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    @Override
    public CartDto save(CartDto dto) {
        return modelMapper.map(cartRepository.save(modelMapper.map(dto, Cart.class)), CartDto.class);
    }

    @Override
    public CartDto update(CartDto dto) {
        return modelMapper.map(cartRepository.save(modelMapper.map(dto, Cart.class)), CartDto.class);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartDto findById(Long id) {
        return modelMapper.map(cartRepository.findById(id), CartDto.class);
    }

    @Override
    public List<CartDto> findAll() {
        return cartRepository.findAll().stream()
                .map(cart -> modelMapper.map(cart, CartDto.class))
                .toList();
    }

    @Override
    public Page<CartDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
