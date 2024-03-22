package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.ItemDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Item", description = "REST API for Item information")
@RequestMapping("/v1/item")
public interface ItemApi {
    @PostMapping
    ItemDto save(@RequestBody ItemDto item);
    @PutMapping
    ItemDto update(@RequestBody ItemDto item);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    ItemDto getItemById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<ItemDto> getAllItem();
    @GetMapping(value = "/search")
    Page<ItemDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
