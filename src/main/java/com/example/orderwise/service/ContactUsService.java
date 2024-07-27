package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.dto.*;
import com.example.orderwise.entity.*;
import com.example.orderwise.mail.services.MailService;
import com.example.orderwise.repository.ContactUsRepository;
import com.example.orderwise.slack.service.SlackMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContactUsService implements IBaseService<ContactUs, ContactUsDto> {
    private final ContactUsRepository contactUsRepository;
    private final ModelMapper modelMapper;

    private final MailService mailService;
    private final SlackMessageService slackMessageService;

    @Transactional
    @Override
    public ContactUsDto save(ContactUsDto dto) {
        Map<String, Object> model = new HashMap<>();
        model.put("clientFullName", dto.getFullName());
        model.put("clientMessage", dto.getMessage());
        model.put("clientEmail", dto.getEmail());

        dto.setDateEnvoy(new Date());
        dto.setIsRead(false);
        try {
            mailService.sendEmail(dto.getEmail(), "Confirmation de Réception de Message", model, "contact-confirmation.ftlh");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        sendSlackMessage(dto);

        return modelMapper.map(contactUsRepository.save(modelMapper.map(dto, ContactUs.class)), ContactUsDto.class);
    }

    @Override
    public ContactUsDto update(ContactUsDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ContactUsDto findById(Long id) {
        return null;
    }

    @Override
    public List<ContactUsDto> findAll() {
        return contactUsRepository.findAll()
                .stream()
                .map(contactUs -> modelMapper.map(contactUs, ContactUsDto.class))
                .toList();
    }

    @Override
    public Page<ContactUsDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }

    public void sendSlackMessage(ContactUsDto message) {
        try {
            // Send Slack message
            String slackMessage = "New contact message received:\n" +
                    "Name: " + message.getFullName() + "\n" +
                    "Email: " + message.getEmail() + "\n" +
                    "Message: " + message.getMessage();
            slackMessageService.sendSlackMessage(slackMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
