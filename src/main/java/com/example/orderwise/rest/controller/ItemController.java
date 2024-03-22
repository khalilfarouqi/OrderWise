package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.ItemDto;
import com.example.orderwise.rest.api.ItemApi;
import com.example.orderwise.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController implements ItemApi {
    private final ItemService itemService;

    public ItemDto save(ItemDto item) {
        return itemService.save(item);
    }

    @Override
    public ItemDto update(ItemDto item) {
        return itemService.update(item);
    }

    @Override
    public void delete(Long id) {
        itemService.delete(id);
    }

    @Override
    public ItemDto getItemById(Long id) {
        return itemService.findById(id);
    }

    @Override
    public List<ItemDto> getAllItem() {
        return itemService.findAll();
    }

    @Override
    public Page<ItemDto> search(String query, Integer page, Integer size, String order, String sort) {
        return itemService.rsqlQuery(query, page, size, order, sort);
    }
}
