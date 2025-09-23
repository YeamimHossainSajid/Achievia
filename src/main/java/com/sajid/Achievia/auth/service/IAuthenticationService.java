package com.sajid.Achievia.auth.service;


import com.sajid.Achievia.auth.dto.request.LoginRequestDTO;
import com.sajid.Achievia.auth.dto.response.LoginResponseDTO;

public interface IAuthenticationService {
    LoginResponseDTO login(LoginRequestDTO requestDTO );
}
