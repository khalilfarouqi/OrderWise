package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.ConfigAppDto;
import com.example.orderwise.rest.api.ConfigAppApi;
import com.example.orderwise.service.ConfigAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ConfigAppController implements ConfigAppApi {
    private final ConfigAppService configAppService;

    @Override
    public ConfigAppDto getConfigOfApp() {
        return configAppService.getConfigOfApp();
    }

    @Override
    public ConfigAppDto getConfigAppById(Long id) {
        return configAppService.findById(id);
    }

    @Override
    public ConfigAppDto update(ConfigAppDto configApp) {
        return configAppService.update(configApp);
    }
}
