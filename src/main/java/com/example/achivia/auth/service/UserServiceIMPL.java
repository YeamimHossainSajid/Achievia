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

import java.util.List;
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

    public UserServiceIMPL(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepository) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User ConvertToEntity(User user, UserRequestDTO userRequestDTO) {
        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
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
        if (userRepository.findByUsername(requestDto.username()) != null) {
            throw new RuntimeException("User already exists");
        }

        String generatedOtp = otpService.generateOtp(requestDto.email());
        emailService.sendOtpEmail(requestDto.email(), generatedOtp);

        tempUser = ConvertToEntity(new User(), requestDto);

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
        User foundUser = userRepository.findById(requestDTO.userId()).get();

        if (foundUser == null) {
            throw new RuntimeException("User with id " + requestDTO.userId() + " not found.");
        }

        Set<Role> foundRoles = roleRepository.findAllByIdIn(requestDTO.roleIds());
        foundUser.getRoles().addAll(foundRoles);

        return userRepository.save(foundUser);
    }

    @Override
    public void updateUser(UUID id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).get();

        User updateUser = ConvertToEntity(user, userRequestDTO);
        userRepository.save(updateUser);
    }

    @Override
    public CustomUserResponseDTO searchByUsername(String username) {
        return userRepository.searchByUsername(username);
    }


}
