package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.bean.ChangePasswordRequest;
import com.example.orderwise.common.config.JsonProperties;
import com.example.orderwise.common.dto.NotificationDto;
import com.example.orderwise.common.dto.NotificationGroupDto;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.entity.User;
import com.example.orderwise.entity.enums.Motif;
import com.example.orderwise.entity.enums.NotificationType;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.mail.services.MailService;
import com.example.orderwise.mail.services.SmsService;
import com.example.orderwise.repository.UserRepository;
import com.example.orderwise.exception.BusinessException;
import com.example.orderwise.security.service.KeycloakAdminService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements IBaseService<User, UserDto> {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JsonProperties jsonProperties;

    private final MailService mailService;
    private final SmsService smsService;
    private final NotificationService notificationService;
    private final NotificationGroupService notificationGroupService;
    private final KeycloakAdminService keycloakAdminService;

    public static int counter = 1;

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        validateUser(dto);
        sendNotifications(dto);

        dto.setUserId(keycloakAdminService.createUser(dto));

        NotificationGroupDto notificationGroupDto = createNotificationGroup();

        notificationGroupDto = notificationGroupService.save(notificationGroupDto);

        UserDto userDto = modelMapper.map(userRepository.save(modelMapper.map(dto, User.class)), UserDto.class);

        NotificationDto notificationDto = createNotification(notificationGroupDto, userDto);
        notificationService.save(notificationDto);
        return userDto;
    }

    @Transactional
    public void saveNotifications(UserDto dto, String object, String body, NotificationType notificationType, Boolean notificationWeb) {

        NotificationGroupDto notificationGroupDto = new NotificationGroupDto();
        notificationGroupDto.setObject(object);
        notificationGroupDto.setBody(body);
        notificationGroupDto.setNotificationType(notificationType);
        notificationGroupDto.setNotificationWeb(notificationWeb);
        notificationGroupDto.setDateEnvoy(new Date());

        notificationGroupDto = notificationGroupService.save(notificationGroupDto);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setObject(notificationGroupDto.getObject());
        notificationDto.setBody(notificationGroupDto.getBody());
        notificationDto.setIsRead(false);
        notificationDto.setNotificationWeb(notificationWeb);
        //notificationDto.setUser(dto);
        notificationDto.setNotificationGroup(notificationGroupDto);

        notificationService.save(notificationDto);
    }

    private void validateUser(UserDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(a -> {
                    throw new BusinessException(String.format("User with the same username [%s] exists", dto.getUsername()));
                });

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(a -> {
                    throw new BusinessException(String.format("User with the same Email [%s] exists", dto.getEmail()));
                });
    }

    private String formatPhoneNumber(String tel) {
        if (tel.startsWith("0")) {
            return tel.replaceFirst("0", "+212");
        }
        return tel;
    }

    private void sendNotifications(UserDto dto) {
        try {
            if (dto.getEmail() != null)
                mailService.sendLoginPasswordMail(jsonProperties.getNewCustomerSubject().replaceAll("[\",]", ""), dto);
            if (dto.getTel() != null)
                smsService.sendSms(formatPhoneNumber(dto.getTel()), jsonProperties.getSendPasswordSms().replaceAll("[\",]", ""));
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
        notificationDto.setObject("Creation of you account");
        notificationDto.setBody(notificationGroupDto.getBody());
        notificationDto.setIsRead(false);
        notificationDto.setNotificationWeb(true);
        notificationDto.setUser(dto);
        notificationDto.setNotificationGroup(notificationGroupDto);
        return notificationDto;
    }

    @Override
    @Transactional
    public UserDto update(UserDto dto) {
        Optional<User> user = userRepository.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            if (dto.getImage() != null) {
                int lastIndex = Math.max(dto.getImage().lastIndexOf('\\'), dto.getImage().lastIndexOf('/'));
                String fileName = dto.getImage().substring(lastIndex + 1);
                user.get().setImage(fileName);
            } else {
                user.get().setGender(dto.getGender());
                user.get().setFirstname(dto.getFirstname());
                user.get().setLastname(dto.getLastname());
                user.get().setCin(dto.getCin());
                user.get().setCity(dto.getCity());
                user.get().setEmail(dto.getEmail());
                user.get().setTel(dto.getTel());
            }
            keycloakAdminService.updateUser(user.get().getUserId(), modelMapper.map(user.get(), UserDto.class));
            return modelMapper.map(userRepository.save(modelMapper.map(user.get(), User.class)), UserDto.class);
        } else {
            throw new BusinessException(String.format("User not found [%s]", dto.getUsername()));
        }
    }

    @Transactional
    public UserDto updateAndInKeycloak(UserDto dto) {
        Optional<User> user = userRepository.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            keycloakAdminService.changeRole(user.get().getUserId(), user.get().getUserType().name(), dto.getUserType().name());
            user.get().setImage(dto.getImage());
            user.get().setFirstname(dto.getFirstname());
            user.get().setLastname(dto.getLastname());
            user.get().setEmail(dto.getEmail());
            user.get().setCin(dto.getCin());
            user.get().setTel(dto.getTel());
            user.get().setCity(dto.getCity());
            user.get().setGender(dto.getGender());
            user.get().setUserType(dto.getUserType());
            keycloakAdminService.updateUser(user.get().getUserId(), modelMapper.map(user.get(), UserDto.class));
            return modelMapper.map(userRepository.save(modelMapper.map(user.get(), User.class)), UserDto.class);
        } else
            return null;
    }

    @Transactional
    public UserDto updateConfirmation(UserDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new BusinessException(String.format("User not found [%s]", dto.getUsername())));

        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setCin(dto.getCin());
        user.setTel(dto.getTel());
        user.setCity(dto.getCity());
        user.setGender(dto.getGender());

        keycloakAdminService.removeUserRole(user.getUserId(), user.getRole().name());
        user.setUserType(dto.getUserType());
        keycloakAdminService.assignUserRole(user.getUserId(), user.getRole().name());
        keycloakAdminService.updateUser(user.getUserId(), modelMapper.map(user, UserDto.class));

        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Transactional
    public ResponseEntity<UserDto> refusee(UserDto dto) {
        UserDto userDto = updateConfirmation(dto);
        ResponseEntity<String> notificationResponse;
        try {
            notificationResponse = sendNotificationsByMotif(userDto, Motif.REFUSEE);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (notificationResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<UserDto> validee(UserDto dto) {
        UserDto userDto = updateConfirmation(dto);
        ResponseEntity<String> notificationResponse;
        try {
            notificationResponse = sendNotificationsByMotif(userDto, Motif.VALIDEE);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (notificationResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    public ResponseEntity<String> sendNotificationsByMotif(UserDto dto, Motif motif) {
        String subjectByMotif;
        ResponseEntity<String> emailResponse;
        try {
            if (motif.equals(Motif.VALIDEE)) {
                subjectByMotif = jsonProperties.getNotificationOfConfirmation().replaceAll("[\",]", "");
                emailResponse = sendConfirmationMail(subjectByMotif, dto);
            } else if (motif.equals(Motif.REFUSEE)) {
                subjectByMotif = jsonProperties.getNotificationOfReject().replaceAll("[\",]", "");
                emailResponse = sendRefuseeMail(subjectByMotif, dto);
            } else {
                throw new IllegalArgumentException("Unsupported motif: " + motif);
            }
            saveNotifications(dto, subjectByMotif, motif.name(), NotificationType.NOTIFICATION_SMS_MAIL, true);
            if (emailResponse.getStatusCode() != HttpStatus.OK) {
                return emailResponse;
            }
            smsService.sendSms(formatPhoneNumber(dto.getTel()), subjectByMotif);
            return ResponseEntity.ok("Notifications sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notifications.");
        }
    }

    public ResponseEntity<String> sendConfirmationMail(String subject, UserDto userDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientFirstName", userDto.getFirstname());
        model.put("clientUsername", userDto.getUsername());
        model.put("clientEmail", userDto.getEmail());

        try {
            mailService.sendEmail(userDto.getEmail(), subject, model, "confirmation_email.ftlh");
            return ResponseEntity.ok("Confirmation email sent successfully.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send confirmation email.");
        }
    }

    public ResponseEntity<String> sendRefuseeMail(String subject, UserDto userDto) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientFirstName", userDto.getFirstname());

        try {
            mailService.sendEmail(userDto.getEmail(), subject, model, "refused_account_email.ftlh");
            return ResponseEntity.ok("Confirmation email sent successfully.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send confirmation email.");
        }
    }

    public ResponseEntity<Map<String, String>> uploadProfileImage(MultipartFile file) {
        try {
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }

            String filePath = uploadDir + "image" + counter + ".jpg";
            File uploadedFile = new File(filePath);
            file.transferTo(uploadedFile);

            // Assuming you store the image URL in the database associated with the user
            String imageUrl = "external-images/" + "image" + counter + ".jpg";

            // Update the user's profile image URL in the database (this is just a placeholder)
            // userService.updateUserProfileImage(userId, imageUrl);

            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            counter++;
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new BusinessException(String.format("User with id [%s] does not exist", id));
        }
    }

    public UserDto findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        } else {
            throw new BusinessException(String.format("User with the same username [%s] does not exist", username));
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public Page<UserDto> rsqlQuery(String query, Integer page, Integer size, String order, String sort) {
        return null;
    }

    public void updateUserProfileImage(Long userId, String imageUrl) {
        // Fetch the user by ID and update the profile image URL
        //User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        User user = userRepository.findById(userId).get();
        user.setImage(imageUrl);
        userRepository.save(user);
    }

    @Transactional
    public void changePassword(String username, ChangePasswordRequest request) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));

            keycloakAdminService.changeUserPassword(user.getUserId(), request.getNewPassword());
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public int countByUserType(UserType userType) {
        return userRepository.countByUserType(userType);
    }

    public int countByConfirmedBy(String confirmedBy) {
        return userRepository.countByConfirmedBy(confirmedBy);
    }

    public List<UserDto> getAllUsersToConfirm() {
        return userRepository.getAllByUserType(UserType.NEW_USER)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }

    @Transactional
    public ResponseEntity<String> deleteAccount(String username, String password) {
        UserDto userDto = findByUsername(username);
        if (!keycloakAdminService.checkPassword(username, password))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");

        keycloakAdminService.removeUserRole(userDto.getUserId(), userDto.getUserType().name());
        userDto.setUserType(UserType.REFUSER);
        keycloakAdminService.assignUserRole(userDto.getUserId(), userDto.getUserType().name());

        userRepository.save(modelMapper.map(userDto, User.class));

        try {
            if (userDto.getEmail() != null)
                mailService.sendDeleteEmail(jsonProperties.getEmailSubjectDeleteAccount().replaceAll("[\",]", ""), userDto);
            if (userDto.getTel() != null)
                smsService.sendSms(formatPhoneNumber(userDto.getTel()), jsonProperties.getSmsDeleteAccount().replaceAll("[\",]", ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send confirmation email.");
        }
        return ResponseEntity.ok("Your account has been deleted.");
    }

    @Transactional
    public ResponseEntity<String> treatNewProfiles(UserDto user, String response) {
        try {
            switch (response) {
                case "save":
                    userRepository.save(modelMapper.map(user, User.class));
                    break;
                case "validee":
                    userRepository.save(modelMapper.map(user, User.class));
                    keycloakAdminService.changeRole(user.getUserId(), UserType.NEW_USER.name(), user.getUserType().name());
                    saveNotifications(user, "Your Profile is valide", "Your Profile is valide", NotificationType.NOTIFICATION_SMS_MAIL, true);
                    sendNotificationsAfterTreat(user, jsonProperties.getEmailSubjectConfirmation(), jsonProperties.getSmsConfirmation(), "profileConfirmation.ftlh");
                    break;
                case "refusee":
                    userRepository.save(modelMapper.map(user, User.class));
                    keycloakAdminService.changeRole(user.getUserId(), UserType.NEW_USER.name(), user.getUserType().name());
                    saveNotifications(user, "Your Profile is refuse", "Your Profile is refuse", NotificationType.NOTIFICATION_SMS_MAIL, true);
                    sendNotificationsAfterTreat(user, jsonProperties.getEmailSubjectRefusal(), jsonProperties.getSmsRefusal(), "profileRefusal.ftlh");
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid response");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send confirmation email.");
        }
        return ResponseEntity.ok("Your account is " + response + " successfully");
    }

    private void sendNotificationsAfterTreat(UserDto userDto, String emailSubject, String smsMessage, String template) {
        Map<String, Object> model = new HashMap<>();
        model.put("userName", userDto.getFullName());

        try {
            if (userDto.getEmail() != null)
                mailService.sendEmail(userDto.getEmail(), emailSubject.replaceAll("[\",]", ""), model, template);
            if (userDto.getTel() != null)
                smsService.sendSms(formatPhoneNumber(userDto.getTel()), smsMessage.replaceAll("[\",]", ""));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
