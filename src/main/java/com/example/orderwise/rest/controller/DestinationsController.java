package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.DestinationsDto;
import com.example.orderwise.rest.api.DestinationsApi;
import com.example.orderwise.service.DestinationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DestinationsController implements DestinationsApi {
    private final DestinationsService destinationsService;

    @Override
    public DestinationsDto save(DestinationsDto destinations) {
        return destinationsService.save(destinations);
    }

    @Override
    public DestinationsDto update(DestinationsDto destinations) {
        return destinationsService.update(destinations);
    }

    @Override
    public void delete(Long id) {
        destinationsService.delete(id);
    }

    @Override
    public DestinationsDto getDestinationsById(Long id) {
        return destinationsService.findById(id);
    }

    @Override
    public List<DestinationsDto> getAllDestinations() {
        return destinationsService.findAll();
    }

    @Override
    public Page<DestinationsDto> search(String query, Integer page, Integer size, String order, String sort) {
        return destinationsService.rsqlQuery(query, page, size, order, sort);
    }
}
