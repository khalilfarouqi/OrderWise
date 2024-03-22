package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.OperationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Operation", description = "REST API for Operation information")
@RequestMapping("/v1/operation")
public interface OperationApi {
    @PostMapping
    OperationDto save(@RequestBody OperationDto operation);
    @PutMapping
    OperationDto update(@RequestBody OperationDto operation);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    OperationDto getOperationById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<OperationDto> getAllOperation();
    @GetMapping(value = "/search")
    Page<OperationDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
