package com.sajid.Achievia.auth.controller;

import com.sajid.Achievia.auth.dto.request.LoginRequestDTO;
import com.sajid.Achievia.auth.dto.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO );

}
