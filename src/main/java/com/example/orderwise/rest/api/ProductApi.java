package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.ProductDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Product", description = "REST API for Product information")
@RequestMapping("/v1/product")
public interface ProductApi {
    @PostMapping
    ProductDto save(@RequestBody ProductDto product);
    @PutMapping
    ProductDto update(@RequestBody ProductDto product);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    ProductDto getProductById(@PathVariable("id") Long id);
    @GetMapping(value = "/seller/{username}")
    List<ProductDto> getProductsBySellerUsername(@PathVariable("username") String username);
    @GetMapping(value = "/getAll")
    List<ProductDto> getAllProduct();
    @GetMapping(value = "/search")
    Page<ProductDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
