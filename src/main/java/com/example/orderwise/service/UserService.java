package com.example.orderwise.service;

import com.example.orderwise.base.IBaseService;
import com.example.orderwise.bean.ChangePasswordRequest;
import com.example.orderwise.common.config.JsonProperties;
import com.example.orderwise.common.dto.UserDto;
import com.example.orderwise.entity.User;
import com.example.orderwise.mail.services.MailService;
import com.example.orderwise.repository.UserRepository;
import com.example.orderwise.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService implements IBaseService<User, UserDto> {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JsonProperties jsonProperties;
    private final MailService mailService;
    public static int counter = 1;

    @Value("${upload.dir}")
    private String uploadDir;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public UserDto save(UserDto dto) {
        userRepository.findByUsername(dto.getUsername())
                .ifPresent(a ->{
                    throw new BusinessException(String.format("User with the same username [%s] exist", dto.getUsername()));
                });
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(a ->{
                    throw new BusinessException(String.format("User with the same Email [%s] exist", dto.getEmail()));
                });
        try {
            mailService.sendLoginPasswordMail(jsonProperties.getNewCustomerSubject().replaceAll("[\",]", ""), dto);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        dto.setPassword(encoder.encode(dto.getPassword()));
        return modelMapper.map(userRepository.save(modelMapper.map(dto, User.class)), UserDto.class);
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
            return modelMapper.map(userRepository.save(modelMapper.map(user.get(), User.class)), UserDto.class);
        } else {
            throw new BusinessException(String.format("User not found [%s]", dto.getUsername()));
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
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new Exception("User not found"));

            if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
                throw new Exception("Old password is incorrect");
            }
            user.setPassword(encoder.encode(request.getNewPassword()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
