package com.example.achivia.auth.service;

import com.example.achivia.auth.dto.request.UserRequestDTO;
import com.example.achivia.auth.dto.request.UserRoleRequestDTO;
import com.example.achivia.auth.dto.response.CustomRoleResponseDTO;
import com.example.achivia.auth.dto.response.CustomUserResponseDTO;
import com.example.achivia.auth.dto.response.CustomUserResponseDtoCls;
import com.example.achivia.auth.model.Role;
import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.RoleRepo;

import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.config.image.service.CloudneryImageService;
import com.example.achivia.config.mail.EmailService;
import com.example.achivia.config.mail.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CloudneryImageService cloudneryImageService;

    private User tempUser;

    public UserServiceIMPL(UserRepo userRepository, PasswordEncoder passwordEncoder, RoleRepo roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User convertToEntity(User user, UserRequestDTO userRequestDto) {
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return user;
    }

    private CustomUserResponseDtoCls convertToResponseDtoCls(User user) {
        Set<CustomRoleResponseDTO> roles = user.getRoles().stream()
                .map(role -> new CustomRoleResponseDTO() {
                    @Override
                    public Long getId() {
                        return role.getId();
                    }

                    @Override
                    public String getRoleType() {
                        return role.getRoleType().toString();
                    }

                    @Override
                    public Set<UserInfo> getUsers() {
                        return null;
                    }
                })
                .collect(Collectors.toSet());

        CustomUserResponseDtoCls dto = new CustomUserResponseDtoCls();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(roles);

        return dto;
    }

    public String create(UserRequestDTO requestDto) {
        if (userRepository.findByUsernameNative(requestDto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String generatedOtp = otpService.generateOtp(requestDto.getEmail());
        emailService.sendOtpEmail(requestDto.getEmail(), generatedOtp);

        tempUser = convertToEntity(new User(), requestDto);

        return "OTP sent to email. Please verify before proceeding.";
    }

    public String validateOtp(String email, String otp) {
        if (!otpService.verifyOtp(email, otp)) {
            return "Invalid OTP! Please try again.";
        }

        if (tempUser == null || !tempUser.getEmail().equals(email)) {
            return "No user data found. Please register again.";
        }

        userRepository.save(tempUser);
        otpService.removeOtp(email);
        tempUser = null;

        return "User registered successfully!";
    }


    public CustomUserResponseDTO readOne(UUID id) {
        CustomUserResponseDTO singleUserById = userRepository.findUserByUserId(id);
        if (Objects.isNull(singleUserById)) {
            throw new RuntimeException("User with id " + id + " not found.");
        }
        return singleUserById;
    }


    public User setUserRoles(UserRoleRequestDTO requestDTO) {
        User foundUser = userRepository.findUserById(requestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User with id " + requestDTO.userId() + " not found."));

        Set<Role> foundRoles = roleRepository.findAllByIdIn(requestDTO.roleIds());
        foundUser.getRoles().addAll(foundRoles);

        return userRepository.save(foundUser);
    }


    @Override
    public void updateUser(UUID id, UserRequestDTO userRequestDto) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        User updateUser = convertToEntity(user, userRequestDto);
        userRepository.save(updateUser);
    }


    @Override
    public CustomUserResponseDTO searchByUsername(String username) {
        CustomUserResponseDTO userDto = userRepository.searchByUsername(username);
        if (Objects.isNull(userDto)) {
            throw new RuntimeException("User with username " + username + " not found.");
        }
        return userDto;
    }
}
