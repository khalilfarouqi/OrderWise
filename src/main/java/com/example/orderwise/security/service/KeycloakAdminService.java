package com.example.orderwise.security.service;

import com.example.orderwise.security.config.AppProperties;
import org.keycloak.admin.client.resource.UsersResource;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.common.dto.UserDto;
import org.springframework.stereotype.Service;
import org.keycloak.OAuth2Constants;
import org.keycloak.representations.idm.*;
import org.keycloak.admin.client.*;
import org.springframework.http.*;

import jakarta.ws.rs.client.*;

import java.util.*;

@Service
public class KeycloakAdminService {

    private final AppProperties appProperties;

    static Keycloak keycloak = null;

    public KeycloakAdminService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Keycloak getInstance() {
        Client resteasyClient = ClientBuilder.newBuilder().build();
        keycloak = KeycloakBuilder.builder()
                .serverUrl(appProperties.getAuthServerUrl())
                .grantType(OAuth2Constants.PASSWORD)
                .realm(appProperties.getRealm())
                .clientId(appProperties.getResource())
                .username(appProperties.getUsername())
                .password(appProperties.getPassword())
                .resteasyClient(resteasyClient)
                .build();
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }

    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public ResponseEntity<String> createUser(UserDto userDto) {
        CredentialRepresentation credential = createPasswordCredentials(userDto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstname());
        user.setLastName(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        Map<String, List<String>> userAttributes = new HashMap<>();
        userAttributes.put("userType", Collections.singletonList("ADHERENT"));
        userAttributes.put("celluleRef", Collections.singletonList("1"));
        user.setAttributes(userAttributes);
        //waiting adherent role
        user.setRealmRoles(Collections.singletonList(UserType.ADMIN.name()));
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource instance = getInstance().realm(appProperties.getRealm()).users();
        Integer status = instance.create(user).getStatus();
        System.out.println("status ====> "+status);
        if(status==201){
            instance.search(user.getUsername(),true).get(0).getId();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("this user Name or mail ready exist");
        }

        return ResponseEntity.ok().body("User created successfully");
    }
}
