package com.example.achivia.auth.dto.response;

import java.util.Set;
import java.util.UUID;

public interface CustomRoleResponseDTO {

    Long getId();

    String getRoleType();

    Set< UserInfo > getUsers();

    interface UserInfo {
        UUID getId();

        String getUsername();

        String getEmail();
    }
}
