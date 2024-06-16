package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.MyMoneyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "My Money", description = "REST API for My Money information")
@RequestMapping("/v1/mymoney")
public interface MyMoneyApi {
    @PostMapping
    MyMoneyDto save(@RequestBody MyMoneyDto myMoneyDto);
    @GetMapping(value = "/{username}")
    List<MyMoneyDto> findByUserUsername(@PathVariable("username") String username);
}
