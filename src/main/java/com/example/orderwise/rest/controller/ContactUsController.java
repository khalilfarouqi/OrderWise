package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.ContactUsDto;
import com.example.orderwise.rest.api.ContactUsApi;
import com.example.orderwise.service.ContactUsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ContactUsController implements ContactUsApi {
    private final ContactUsService contactUsService;

    public ContactUsDto save(ContactUsDto dto) {
        return contactUsService.save(dto);
    }

    @Override
    public List<ContactUsDto> getAllContactUs() {
        return contactUsService.findAll();
    }
}
