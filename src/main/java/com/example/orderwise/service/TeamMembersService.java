package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.bean.NotificationRequestBean;
import com.example.orderwise.common.dto.OrderDto;
import com.example.orderwise.common.dto.TeamMembersDto;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.entity.TeamMembers;
import com.example.orderwise.entity.enums.Role;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.repository.TeamMembersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.orderwise.common.config.PasswordGenerator.generatePassword;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TeamMembersService implements IBaseService<TeamMembers, TeamMembersDto> {
    private final TeamMembersRepository teamMembersRepository;
    private final ModelMapper modelMapper;

    private final OrderService orderService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public TeamMembersDto save(TeamMembersDto dto) {
        return modelMapper.map(teamMembersRepository.save(modelMapper.map(dto, TeamMembers.class)), TeamMembersDto.class);
    }

    @Transactional
    public ResponseEntity<String> saveTeamMembers(UserDto userDto) {
        if (userDto.getImage() != null)
            userDto.setImage(userDto.getImage().equals("") ? null : userDto.getImage());
        if (userDto.getUsername() == null || userDto.getUsername().isEmpty())
            userDto.setUsername(Optional.ofNullable(userDto.getFirstname()).orElse("") + "." + Optional.ofNullable(userDto.getLastname()).orElse(""));
        userDto.setPassword(generatePassword(8));
        userDto.setRole(Role.ADMIN);
        userDto = userService.save(userDto);
        TeamMembersDto teamMembersDto = new TeamMembersDto();
        teamMembersDto.setAvailability(true);
        teamMembersDto.setCurrentLoad(0);
        teamMembersDto.setFullName(userDto.getFullName());
        teamMembersDto.setUserType(userDto.getUserType());
        teamMembersDto.setUser(userDto);
        save(teamMembersDto);
        return ResponseEntity.ok("Member is added successfully");
    }

    @Override
    @Transactional
    public TeamMembersDto update(TeamMembersDto dto) {
        return modelMapper.map(teamMembersRepository.save(modelMapper.map(dto, TeamMembers.class)), TeamMembersDto.class);
    }

    @Override
    public void delete(Long id) {
        teamMembersRepository.deleteById(id);
    }

    @Override
    public TeamMembersDto findById(Long id) {
        return modelMapper.map(teamMembersRepository.findById(id).get(), TeamMembersDto.class);
    }

    @Override
    public List<TeamMembersDto> findAll() {
        return teamMembersRepository.findAll()
                .stream()
                .map(teamMembers -> modelMapper.map(teamMembers, TeamMembersDto.class))
                .toList();
    }

    @Override
    public Page<TeamMembersDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }

    @Transactional
    public void assignOrderToTeamMember(Long orderId, UserType userType) {
        OrderDto orderDto = orderService.findById(orderId);

        List<TeamMembersDto> prioritizedMembers;

        if (userType == UserType.DELIVERY_BOY) {
            prioritizedMembers = teamMembersRepository.findAllByUserTypeAndAvailabilityIsTrueAndUserCity(userType, orderDto.getCart().getCustomer().getCity())
                    .stream()
                    .map(teamMembers -> modelMapper.map(teamMembers, TeamMembersDto.class))
                    .toList();
        } else {
            prioritizedMembers = teamMembersRepository.findAllByUserTypeAndAvailabilityIsTrue(userType)
                    .stream()
                    .map(teamMembers -> modelMapper.map(teamMembers, TeamMembersDto.class))
                    .toList();
        }

        try {
            TeamMembersDto chosenMember = prioritizedMembers
                    .stream()
                    .min(Comparator.comparing(TeamMembersDto::getCurrentLoad))
                    .orElseThrow(() -> new RuntimeException("No suitable team members for this priority"));

            orderDto.setHoldTo(chosenMember.getUser().getUsername());
            chosenMember.setCurrentLoad(chosenMember.getCurrentLoad() + 1);

            orderService.save(orderDto);
            save(chosenMember);
        } catch (RuntimeException e) {
            NotificationRequestBean notificationRequestBean = new NotificationRequestBean();
            notificationRequestBean.setMessage("No Suitable Team Members Available for Delivery");
            notificationRequestBean.setTel("0638153545");
            notificationRequestBean.setToEmail("khalil.farouqi.f@gmail.com");
            notificationRequestBean.setSubject("No Suitable Team Members Available for Delivery");
            Map<String, Object> model = new HashMap<>();
            model.put("subject", notificationRequestBean.getSubject());
            model.put("recipientName", "khalil farouqi");
            notificationRequestBean.setModel(model);
            notificationRequestBean.setTemplate("no_suitable_team_members.ftlh");
            notificationService.sendNotifications(notificationRequestBean);
        }
    }

    @Transactional
    public TeamMembersDto updateMember(TeamMembersDto dto) {
        UserDto userDto = userService.updateAndInKeycloak(dto.getUser());
        dto.setUserType(userDto.getUserType());
        if (userDto.getUserType().equals(UserType.HOLD))
            dto.setAvailability(false);
        dto.setFullName(userDto.getFirstname()+" "+userDto.getLastname());
        return modelMapper.map(teamMembersRepository.save(modelMapper.map(dto, TeamMembers.class)), TeamMembersDto.class);
    }
}
