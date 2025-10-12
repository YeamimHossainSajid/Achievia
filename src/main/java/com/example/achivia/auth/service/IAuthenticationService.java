package com.example.achivia.auth.service;


import com.example.achivia.auth.dto.request.LoginRequestDTO;
import com.example.achivia.auth.dto.response.LoginResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login(LoginRequestDTO requestDTO );
}
