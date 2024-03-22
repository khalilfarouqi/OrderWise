package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.ItemDto;
import com.example.orderwise.entity.Item;
import com.example.orderwise.repository.CustomerRepository;
import com.example.orderwise.repository.ItemRepository;
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
public class ItemService implements IBaseService<Item, ItemDto> {
    private final ItemRepository itemRepository;
    private ModelMapper modelMapper;
    @Override
    public ItemDto save(ItemDto dto) throws Exception {
        return null;
    }

    @Override
    public ItemDto update(ItemDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ItemDto findById(Long id) {
        return null;
    }

    @Override
    public List<ItemDto> findAll() {
        return null;
    }

    @Override
    public Page<ItemDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
