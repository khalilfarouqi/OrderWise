package com.example.orderwise.rest.controller;

import com.example.orderwise.common.dto.TeamMembersDto;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.rest.api.TeamMembersApi;
import com.example.orderwise.service.TeamMembersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TeamMembersController implements TeamMembersApi {
    private final TeamMembersService teamMembersService;

    @Override
    public TeamMembersDto save(TeamMembersDto teamMembersDto) {
        return teamMembersService.save(teamMembersDto);
    }

    @Override
    public TeamMembersDto update(TeamMembersDto teamMembersDto) {
        return teamMembersService.update(teamMembersDto);
    }

    @Override
    public void delete(Long id) {
        teamMembersService.delete(id);
    }

    @Override
    public TeamMembersDto getTeamMembersById(Long id) {
        return teamMembersService.findById(id);
    }

    @Override
    public List<TeamMembersDto> getAllTeamMembers() {
        return teamMembersService.findAll();
    }

    @Override
    public Page<TeamMembersDto> search(String query, Integer page, Integer size, String order, String sort) {
        return teamMembersService.rsqlQuery(query, page, size, order, sort);
    }

    @Override
    public ResponseEntity<String> addMember(TeamMembersDto teamMembersDto) {
        return teamMembersService.saveTeamMembers(teamMembersDto.getUser());
    }

    @Override
    public TeamMembersDto updateMember(TeamMembersDto teamMembersDto) {
        return teamMembersService.updateMember(teamMembersDto);
    }
}
