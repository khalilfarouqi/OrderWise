package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.DestinationsDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Destinations", description = "REST API for Destinations information")
@RequestMapping("/v1/destinations")
public interface DestinationsApi {
    @PostMapping
    DestinationsDto save(@RequestBody DestinationsDto destinations);
    @PutMapping
    DestinationsDto update(@RequestBody DestinationsDto destinations);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    DestinationsDto getDestinationsById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<DestinationsDto> getAllDestinations();
    @GetMapping(value = "/search")
    Page<DestinationsDto> search(@RequestParam(defaultValue = "id>0") String query,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "10") Integer size,
                        @RequestParam(defaultValue = "asc") String order,
                        @RequestParam(defaultValue = "id") String sort);
}
