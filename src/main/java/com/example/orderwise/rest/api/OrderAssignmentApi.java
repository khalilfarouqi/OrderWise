package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.OrderAssignmentDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "OrderAssignment", description = "REST API for Order Assignment information")
@RequestMapping("/v1/orderAssignment")
public interface OrderAssignmentApi {
    @PostMapping
    OrderAssignmentDto save(@RequestBody OrderAssignmentDto orderAssignment);
    @PutMapping
    OrderAssignmentDto update(@RequestBody OrderAssignmentDto orderAssignment);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    OrderAssignmentDto getOrderAssignmentById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<OrderAssignmentDto> getAllOrderAssignment();
    @GetMapping(value = "/search")
    Page<OrderAssignmentDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
