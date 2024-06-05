package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.ContactUsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@Tag(name = "ContactUs", description = "REST API for Destinations information")
@RequestMapping("/v1/contactus")
public interface ContactUsApi {
    @PostMapping
    ContactUsDto save(@RequestBody ContactUsDto contactUsDto);
}
