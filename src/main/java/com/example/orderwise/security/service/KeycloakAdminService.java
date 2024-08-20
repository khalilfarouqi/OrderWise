package com.example.orderwise.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import com.example.orderwise.entity.enums.UserType;
import com.example.orderwise.common.dto.UserDto;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.*;
import org.springframework.http.*;

import org.keycloak.admin.client.resource.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.orderwise.security.config.JwtConverter.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KeycloakAdminService {

    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public String createUser(UserDto userDto) {
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
            if (userDto.getUserType() != null) {
                RoleRepresentation roleRepresentation = getInstance()
                        .realm(appProperties.getRealm())
                        .roles()
                        .get(userDto.getUserType().name())
                        .toRepresentation();
                userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
            }

            return userId;
        } else
            return null;
    }

    public ResponseEntity<String> updateUser(String userId, UserDto userDto) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        UserResource userResource = usersResource.get(userId);

        // Fetch the existing user representation
        UserRepresentation user = userResource.toRepresentation();

        // Update the necessary fields
        user.setFirstName(userDto.getFirstname());
        user.setLastName(userDto.getLastname());
        user.setEmail(userDto.getEmail());

        // Update the user in Keycloak
        userResource.update(user);

        return ResponseEntity.ok().body("User updated successfully");
    }

    public ResponseEntity<String> changeUserPassword(String userId, String newPassword) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        UserResource userResource = usersResource.get(userId);

        // Create new password credentials
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setTemporary(false);
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(newPassword);

        // Update the user's password
        userResource.resetPassword(newCredential);

        return ResponseEntity.ok().body("Password updated successfully");
    }

    public ResponseEntity<String> removeUserRole(String userId, String roleName) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        UserResource userResource = usersResource.get(userId);

        // Fetch the role representation
        RoleRepresentation roleRepresentation = getInstance()
                .realm(appProperties.getRealm())
                .roles()
                .get(roleName)
                .toRepresentation();

        // Remove the role from the user
        userResource.roles().realmLevel().remove(Collections.singletonList(roleRepresentation));

        return ResponseEntity.ok().body("Role " + roleName + " removed successfully");
    }

    public ResponseEntity<String> assignUserRole(String userId, String roleName) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        UserResource userResource = usersResource.get(userId);

        // Fetch the new role representation
        RoleRepresentation roleRepresentation = getInstance()
                .realm(appProperties.getRealm())
                .roles()
                .get(roleName)
                .toRepresentation();

        // Assign the new role to the user
        userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));

        return ResponseEntity.ok().body("Role " + roleName + " assigned successfully");
    }

    public void changeRole(String userId, String oldRoleName, String newRoleName) {
        removeUserRole(userId, oldRoleName);
        assignUserRole(userId, newRoleName);
    }

    public ResponseEntity<String> resetUserPassword(String userId, String newPassword) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        UserResource userResource = usersResource.get(userId);

        // Create new password credentials
        CredentialRepresentation newCredential = new CredentialRepresentation();
        newCredential.setTemporary(false);
        newCredential.setType(CredentialRepresentation.PASSWORD);
        newCredential.setValue(newPassword);

        // Reset the user's password
        userResource.resetPassword(newCredential);

        return ResponseEntity.ok().body("Password reset successfully");
    }

    public boolean checkPassword(String username, String password) {
        try {
            // Attempt to authenticate the user with Keycloak
            Keycloak keycloak = getUserInstance(username, password);

            // Perform the authentication and get an access token
            AccessTokenResponse tokenResponse = keycloak.tokenManager().getAccessToken();
            return true;
        } catch (Exception e) {
            // If authentication fails, return an error response
            return false;
        }
    }

    public ResponseEntity<String> deleteAccount(String userId) {
        UsersResource usersResource = getInstance().realm(appProperties.getRealm()).users();
        usersResource.get(userId).remove();
        return ResponseEntity.ok("Account deleted successfully");
    }
}
