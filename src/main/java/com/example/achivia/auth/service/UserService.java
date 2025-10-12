package com.example.achivia.auth.service;

import com.example.achivia.auth.dto.request.UserRequestDTO;
import com.example.achivia.auth.dto.request.UserRoleRequestDTO;
import com.example.achivia.auth.dto.response.CustomUserResponseDTO;
import com.example.achivia.auth.model.User;

import java.util.UUID;

public interface UserService {

    public String create(UserRequestDTO requestDto);
    public CustomUserResponseDTO readOne(UUID id );
    public User setUserRoles(UserRoleRequestDTO requestDTO );
    public void updateUser(UUID id, UserRequestDTO userRequestDTO);
    public CustomUserResponseDTO searchByUsername(String username);
    public String validateOtp(String email, String otp);
}