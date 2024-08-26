package com.example.orderwise.rest.api;

import com.example.orderwise.common.dto.TeamMembersDto;
import com.example.orderwise.common.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "TeamMembers", description = "REST API for Team Members information")
@RequestMapping("/v1/team-members")
public interface TeamMembersApi {
    @PostMapping
    TeamMembersDto save(@RequestBody TeamMembersDto teamMembersDto);
    @PutMapping
    TeamMembersDto update(@RequestBody TeamMembersDto teamMembersDto);
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
    @GetMapping(value = "/{id}")
    TeamMembersDto getTeamMembersById(@PathVariable("id") Long id);
    @GetMapping(value = "/getAll")
    List<TeamMembersDto> getAllTeamMembers();
    @GetMapping(value = "/search")
    Page<TeamMembersDto> search(@RequestParam(defaultValue = "id>0") String query,
                           @RequestParam(defaultValue = "0") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           @RequestParam(defaultValue = "asc") String order,
                           @RequestParam(defaultValue = "id") String sort);
    @PostMapping("/add-member")
    ResponseEntity<String> addMember(@RequestBody TeamMembersDto teamMembersDto);
    @PutMapping("/update-member")
    TeamMembersDto updateMember(@RequestBody TeamMembersDto teamMembersDto);
}
