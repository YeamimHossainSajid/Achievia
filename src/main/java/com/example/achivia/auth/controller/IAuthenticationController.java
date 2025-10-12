package com.example.achivia.auth.controller;

import com.example.achivia.auth.dto.request.LoginRequestDTO;
import com.example.achivia.auth.dto.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationController {

    ResponseEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO );

}
