package com.sajid.Achievia.auth.service;


import com.sajid.Achievia.auth.dto.request.UserRequestDTO;
import com.sajid.Achievia.auth.dto.request.UserRoleRequestDTO;
import com.sajid.Achievia.auth.dto.response.CustomUserResponseDTO;
import com.sajid.Achievia.auth.model.User;

public interface UserService {

    public String create(UserRequestDTO requestDto);
    public CustomUserResponseDTO readOne(Long id );
    public User setUserRoles(UserRoleRequestDTO requestDTO );
    public void updateUser(Long id, UserRequestDTO userRequestDTO);
    public CustomUserResponseDTO searchByUsername(String username);
    public String validateOtp(String email, String otp);
}