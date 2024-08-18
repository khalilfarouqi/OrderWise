package com.example.orderwise.security.service;

import org.keycloak.admin.client.resource.UsersResource;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.common.dto.UserDto;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.*;
import org.springframework.http.*;

import org.keycloak.admin.client.resource.*;

import java.util.*;

import static com.example.orderwise.security.config.JwtConverter.*;

@Service
public class KeycloakAdminService {

    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public ResponseEntity<String> createUser(UserDto userDto) {
        //set user password
        CredentialRepresentation credential = createPasswordCredentials(userDto.getPassword());
        //set user info
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstname());
        user.setLastName(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        //set user role
        user.setRealmRoles(Collections.singletonList(UserType.ADMIN.name()));
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource instance = getInstance().realm(appProperties.getRealm()).users();
        if(instance.create(user).getStatus() == 201){
            // Get the ID of the newly created user
            String userId = instance.search(user.getUsername(), true).get(0).getId();

            // Set user role
            UserResource userResource = instance.get(userId);
            RoleRepresentation roleRepresentation = getInstance()
                    .realm(appProperties.getRealm())
                    .roles()
                    .get(userDto.getUserType().name())
                    .toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));

            return ResponseEntity.ok().body("User created successfully with role " + userDto.getUserType().name());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("this user Name or mail ready exist");
        }
    }
}
