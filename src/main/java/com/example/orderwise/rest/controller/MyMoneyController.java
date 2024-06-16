package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.MyMoneyDto;
import com.example.orderwise.rest.api.MyMoneyApi;
import com.example.orderwise.service.MyMoneyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MyMoneyController implements MyMoneyApi {
    private final MyMoneyService myMoneyService;

    @Override
    public MyMoneyDto save(MyMoneyDto myMoneyDto) {
        return myMoneyService.save(myMoneyDto);
    }

    public List<MyMoneyDto> findByUserUsername(String username) {
        return myMoneyService.findByUserUsername(username);
    }
}
