package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.MyMoneyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Tag(name = "My Money", description = "REST API for My Money information")
@RequestMapping("/v1/mymoney")
public interface MyMoneyApi {
    @GetMapping(value = "/{username}")
    MyMoneyDto findByUserUsername(@PathVariable("username") String username);
}
