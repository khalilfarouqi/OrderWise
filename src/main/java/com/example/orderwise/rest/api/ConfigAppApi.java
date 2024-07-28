package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Tag(name = "ConfigApp", description = "REST API for Config App information")
@RequestMapping("/v1/config-app")
public interface ConfigAppApi {
    @GetMapping
    ConfigAppDto getConfigOfApp();
    @GetMapping(value = "/{id}")
    ConfigAppDto getConfigAppById(@PathVariable("id") Long id);
    @PutMapping
    ConfigAppDto update(@RequestBody ConfigAppDto configApp);
}
