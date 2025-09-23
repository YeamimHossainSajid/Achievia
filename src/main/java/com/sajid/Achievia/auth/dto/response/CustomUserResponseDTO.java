package com.sajid.Achievia.auth.dto.response;

import java.util.List;
import java.util.Set;

public interface CustomUserResponseDTO {
    Long getId();
    String getUsername();
    String getEmail();
    Set<RoleInfo> getRoles();

    interface RoleInfo {
        Long getId();
        String getRoleType();
    }


}
