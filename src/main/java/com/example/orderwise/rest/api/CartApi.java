package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.CartDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Cart", description = "REST API for Cart information")
@RequestMapping("/v1/cart")
public interface CartApi {
    @PostMapping
    CartDto save(@RequestBody CartDto cart);
    @PutMapping
    CartDto update(@RequestBody CartDto cart);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    CartDto getCartById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<CartDto> getAllCart();
    @GetMapping(value = "/search")
    Page<CartDto> search(@RequestParam(defaultValue = "id>0") String query,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           @RequestParam(defaultValue = "asc") String order,
                           @RequestParam(defaultValue = "id") String sort);
}
