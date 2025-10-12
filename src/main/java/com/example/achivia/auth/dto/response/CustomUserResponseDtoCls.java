package com.example.achivia.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserResponseDtoCls {
    private UUID id;
    private String username;
    private String email;
    private Set<CustomRoleResponseDTO> roles;
    private Double rating;
    private Boolean isActive;
    private Boolean isAvailable;
    private Boolean isVerified;
}

