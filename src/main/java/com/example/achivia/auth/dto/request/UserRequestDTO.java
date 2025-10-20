package com.example.achivia.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserRequestDTO {

        @NotEmpty(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        private String username;

        @NotEmpty(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotEmpty(message = "Password is required")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        private String password;

        // Optional roles for the user (UUIDs of roles)
        private Set<UUID> roleIds;

        // Optional OAuth info if user signs up via Google/Facebook
        private String authProvider;   // e.g., "google", "facebook"
        private String authProviderId; // OAuth provider ID

}
