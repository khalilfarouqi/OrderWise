package com.example.orderwise.mail.services;

import com.example.orderwise.common.dto.*;
import com.example.orderwise.service.ConfigAppService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String fromEmail;

    private final ConfigAppService configAppService;

    @Autowired
    private Configuration freemarkerConfig;

    public MailService(JavaMailSender mailSender, @Value("${app.mail.from}") String fromEmail, ConfigAppService configAppService) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
        this.configAppService = configAppService;
    }

    public void sendConfirmedOrderEmail(String subject, OrderDto orderDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientName", orderDto.getCustomer().getCompleteNom());
        model.put("orderNumber", orderDto.getTrackingCode());
        model.put("orderDate", orderDto.getOrderDate());
        model.put("orderTotal", orderDto.getTotalPrice());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromEmail);
        helper.setTo(orderDto.getCustomer().getEmail());
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("confirmed_order.ftlh"), model), true);

        mailSender.send(message);
    }

    public void sendCanceledOrderEmail(String subject, OrderDto orderDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientName", orderDto.getCustomer().getCompleteNom());
        model.put("orderNumber", orderDto.getTrackingCode());
        model.put("orderDate", orderDto.getOrderDate());

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromEmail);
        helper.setTo(orderDto.getCustomer().getEmail());
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("canceled_order.ftlh"), model), true);

        mailSender.send(message);
    }

    public void sendDeleteEmail(String subject, UserDto userDto) throws Exception {
        ConfigAppDto configAppDto = configAppService.getConfigOfApp();
        Map<String, Object> model = new HashMap<>();
        model.put("clientName", userDto.getFirstname() + " " + userDto.getLastname());
        model.put("supportEmailAddress", configAppDto.getEmail());

        String toAddress = userDto.getEmail();
        String fromAddress = fromEmail;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("delete_account.ftlh"), model), true);

        mailSender.send(message);
    }

    public void sendLoginPasswordMail(String subject, UserDto userDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientName", userDto.getFirstname() + " " + userDto.getLastname());
        model.put("username", userDto.getUsername());
        model.put("tempPassword", userDto.getPassword());

        String toAddress = userDto.getEmail();
        String fromAddress = fromEmail;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("Login.ftlh"), model), true);

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, Map<String, Object> model, String template) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(template), model), true);

        mailSender.send(message);
    }

    public ResponseEntity<String> sendSimpleEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("your-email@example.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
        }
        return ResponseEntity.ok("Email sent successfully.");
    }

    public void afterSendDemandMoney(String subject, MyMoneyDto myMoneyDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("fullName", myMoneyDto.getUser().getFirstname() + " " + myMoneyDto.getUser().getLastname());
        model.put("montant", myMoneyDto.getMontant());

        String toAddress = myMoneyDto.getUser().getEmail();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromEmail);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("demande.ftlh"), model), true);

        mailSender.send(message);
    }
}
