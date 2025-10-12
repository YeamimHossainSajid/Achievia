package com.example.achivia.auth.dto.response;

import java.util.Set;
import java.util.UUID;

public interface CustomUserResponseDTO {
    UUID getId();
    String getUsername();
    String getEmail();
    Set<RoleInfo> getRoles();

    interface RoleInfo {
        Long getId();
        String getRoleType();
    }


}
