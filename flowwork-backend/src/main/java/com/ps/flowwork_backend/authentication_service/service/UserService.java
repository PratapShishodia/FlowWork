package com.ps.flowwork_backend.authentication_service.service;

import com.ps.flowwork_backend.authentication_service.model.dto.PasswordChangeRequest;
import com.ps.flowwork_backend.authentication_service.model.dto.UserRequestDTO;
import com.ps.flowwork_backend.authentication_service.model.dto.UserResponseDTO;
import com.ps.flowwork_backend.authentication_service.model.entity.Users;
import com.ps.flowwork_backend.common.dto.LoginRequestDTO;
import com.ps.flowwork_backend.common.dto.LoginResponseDTO;

import java.util.UUID;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
//    LoginResponseDTO refreshToken(String refreshToken);
    Boolean logout(UUID userId);
//    Boolean activateProfile(String token);
    Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest);
    Boolean sendOTP(String email);
    Boolean changePassword(PasswordChangeRequest passwordChangeRequest);
    Boolean changeEmail(UUID userId, String newEmail);
    UserResponseDTO getLoggedInUser();
    Boolean verifyOTP(String email, String OTP);
}
