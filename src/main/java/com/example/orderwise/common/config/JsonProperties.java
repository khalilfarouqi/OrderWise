package com.example.orderwise.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.json", encoding = "UTF-8")
@ConfigurationProperties
@Getter
@Setter
public class JsonProperties {
    private String newCustomerSubject;
    private String newDemandMoneySubject;
    private String sendPasswordSms;
    private String demandOfMoney;
    private String demandOfMoneyAccepted;
    private String demandOfMoneyRefuser;
    private String notificationOfConfirmation;
    private String notificationOfReject;
    private String bodyMailOfConfirmation;
    private String bodyMailOfReject;
    private String smsDeleteAccount;
    private String emailSubjectDeleteAccount;
    private String confirmOrderEmailSubject;
    private String confirmOrderSms;
    private String cancelOrderEmailSubject;
    private String cancelOrderSms;
    private String notAnswerOrderSms;
    private String emailSubjectConfirmation;
    private String emailSubjectRefusal;
    private String smsConfirmation;
    private String smsRefusal;
}
