package com.example.achivia.auth.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record UserRoleRequestDTO(

        @NotNull( message = "User id can't be null or empty." )
        UUID userId,

        @NotNull( message = "Role ids can't be null or empty." )
        Set< Long > roleIds
) {
}
