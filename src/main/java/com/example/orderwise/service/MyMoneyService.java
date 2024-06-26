package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.common.config.JsonProperties;
import com.example.orderwise.common.dto.*;
import com.example.orderwise.entity.MyMoney;
import com.example.orderwise.entity.User;
import com.example.orderwise.entity.enums.EtatDemande;
import com.example.orderwise.entity.enums.NotificationType;
import com.example.orderwise.exception.BusinessException;
import com.example.orderwise.mail.services.MailService;
import com.example.orderwise.mail.services.SmsService;
import com.example.orderwise.repository.MyMoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MyMoneyService implements IBaseService<MyMoney, MyMoneyDto> {
    private final MyMoneyRepository myMoneyRepository;

    private final ModelMapper modelMapper;

    private final JsonProperties jsonProperties;

    private final UserService userService;
    private final WalletService walletService;
    private final MailService mailService;
    private final SmsService smsService;
    private final NotificationService notificationService;
    private final NotificationGroupService notificationGroupService;

    @Override
    @Transactional
    public MyMoneyDto save(MyMoneyDto dto) {
        dto.setUser(userService.findByUsername(dto.getUser().getUsername()));
        String tel = formatPhoneNumber(dto.getUser().getTel());
        WalletDto walletDto = walletService.getWalletBySeller(dto.getUser().getUsername());
        double totalPendingAmount = calculateTotalPendingAmount(dto.getUser().getUsername());

        validateAmount(dto.getMontant(), walletDto.getAmountCredited(), totalPendingAmount);

        MyMoney myMoney = createMyMoneyEntity(dto);
        sendNotifications(myMoney, tel);

        NotificationGroupDto notificationGroupDto = createNotificationGroup();
        notificationGroupDto = notificationGroupService.save(notificationGroupDto);

        NotificationDto notificationDto = createNotification(notificationGroupDto, dto.getUser());
        notificationService.save(notificationDto);

        return modelMapper.map(myMoneyRepository.save(myMoney), MyMoneyDto.class);
    }

    private String formatPhoneNumber(String tel) {
        if (tel.startsWith("0")) {
            return tel.replaceFirst("0", "+212");
        }
        return tel;
    }

    private double calculateTotalPendingAmount(String username) {
        return findByUserUsername(username).stream()
                .filter(myMoneyDto -> myMoneyDto.getEtatDemande() == EtatDemande.ENCOURS)
                .mapToDouble(MyMoneyDto::getMontant)
                .sum();
    }

    private void validateAmount(double requestedAmount, double walletAmount, double totalPendingAmount) {
        if (walletAmount < requestedAmount) {
            throw new BusinessException("Vous ne pouvez pas demander ce montant.");
        } else if (totalPendingAmount + requestedAmount > walletAmount) {
            throw new BusinessException("Vous ne pouvez pas demander ce montant car vous avez déjà des demandes non traitées.");
        }
    }

    private MyMoney createMyMoneyEntity(MyMoneyDto dto) {
        MyMoney myMoney = modelMapper.map(dto, MyMoney.class);
        myMoney.setUser(modelMapper.map(userService.findByUsername(dto.getUser().getUsername()), User.class));
        myMoney.setDateDeDemande(new Date());
        myMoney.setEtatDemande(EtatDemande.ENCOURS);
        return myMoney;
    }

    private void sendNotifications(MyMoney myMoney, String tel) {
        try {
            mailService.afterSendDemandMoney(
                    jsonProperties.getNewCustomerSubject().replaceAll("[\",]", ""),
                    modelMapper.map(myMoney, MyMoneyDto.class)
            );
            smsService.sendSms(tel, jsonProperties.getDemandOfMoney().replaceAll("[\",]", ""));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private NotificationGroupDto createNotificationGroup() {
        NotificationGroupDto notificationGroupDto = new NotificationGroupDto();
        notificationGroupDto.setObject("Ajouter un nouveau customer.");
        notificationGroupDto.setBody("Vous avez créé un compte chez nous.");
        notificationGroupDto.setNotificationType(NotificationType.NOTIFICATION_SMS_MAIL);
        notificationGroupDto.setNotificationWeb(true);
        notificationGroupDto.setDateEnvoy(new Date());
        return notificationGroupDto;
    }

    private NotificationDto createNotification(NotificationGroupDto notificationGroupDto, UserDto dto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setObject(notificationGroupDto.getObject());
        notificationDto.setBody(notificationGroupDto.getBody());
        notificationDto.setIsRead(false);
        notificationDto.setNotificationWeb(true);
        notificationDto.setUserDto(dto);
        notificationDto.setNotificationGroup(notificationGroupDto);
        return notificationDto;
    }

    @Override
    public MyMoneyDto update(MyMoneyDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public MyMoneyDto findById(Long id) {
        return null;
    }

    public List<MyMoneyDto> findByUserUsername(String username) {
        return myMoneyRepository.findByUserUsername(username).stream().map(myMoney -> modelMapper.map(myMoney, MyMoneyDto.class)).toList();
    }

    @Override
    public List<MyMoneyDto> findAll() {
        return myMoneyRepository.findAll()
                .stream()
                .map(money -> modelMapper.map(money, MyMoneyDto.class))
                .toList();
    }

    @Override
    public Page<MyMoneyDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }
}
