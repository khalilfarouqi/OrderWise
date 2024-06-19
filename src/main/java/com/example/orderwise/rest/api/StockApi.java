package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.StockDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Stock", description = "REST API for Stock information")
@RequestMapping("/v1/stock")
public interface StockApi {
    @PostMapping
    StockDto save(@RequestBody StockDto stock);
    @PutMapping
    StockDto update(@RequestBody StockDto stock);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    StockDto getStockById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<StockDto> getAllStock();
    @GetMapping(value = "/seller/{username}")
    List<StockDto> getStocksByProductSellerUsername(@PathVariable("username") String username);
    @GetMapping(value = "/search")
    Page<StockDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
