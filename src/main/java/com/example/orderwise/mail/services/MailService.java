package com.example.orderwise.mail.services;

import com.example.orderwise.common.dto.MyMoneyDto;
import com.example.orderwise.common.dto.UserDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private Configuration freemarkerConfig;

    public MailService(JavaMailSender mailSender, @Value("${app.mail.from}") String fromEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
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

    public void sendEmail(String to, String subject, Map<String, Object> model, String template) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            freemarkerConfig.setDefaultEncoding("UTF-8");

            String text = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(template), model);

            helper.setTo(to);
            helper.setText(text, true);
            helper.setSubject(subject);
            helper.setFrom(fromEmail);

            mailSender.send(message);

        } catch (Exception e) {
            throw new MessagingException("Failed to send email", e);
        }
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
