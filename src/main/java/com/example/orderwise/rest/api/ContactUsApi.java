package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.ContactUsDto;
import com.example.orderwise.common.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "ContactUs", description = "REST API for Destinations information")
@RequestMapping("/v1/contactus")
public interface ContactUsApi {
    @PostMapping
    ContactUsDto save(@RequestBody ContactUsDto contactUsDto);
    @GetMapping(value = "/getAll")
    List<ContactUsDto> getAllContactUs();
}
