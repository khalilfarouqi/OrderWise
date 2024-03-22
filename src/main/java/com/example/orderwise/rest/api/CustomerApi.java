package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.CustomerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Customer", description = "REST API for Customer information")
@RequestMapping("/v1/customer")
public interface CustomerApi {
    @PostMapping
    CustomerDto save(@RequestBody CustomerDto customer);
    @PutMapping
    CustomerDto update(@RequestBody CustomerDto customer);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    CustomerDto getCustomerById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<CustomerDto> getAllCustomer();
    @GetMapping(value = "/search")
    Page<CustomerDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
