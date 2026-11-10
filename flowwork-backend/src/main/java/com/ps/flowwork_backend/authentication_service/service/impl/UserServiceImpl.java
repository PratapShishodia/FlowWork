package com.ps.flowwork_backend.authentication_service.service.impl;


import com.ps.flowwork_backend.authentication_service.model.dto.PasswordChangeRequest;
import com.ps.flowwork_backend.authentication_service.model.dto.UserRequestDTO;
import com.ps.flowwork_backend.authentication_service.model.dto.UserResponseDTO;
import com.ps.flowwork_backend.authentication_service.model.entity.Users;
import com.ps.flowwork_backend.authentication_service.model.mapper.UserDTOMapper;
import com.ps.flowwork_backend.authentication_service.repository.UsersRepo;
import com.ps.flowwork_backend.authentication_service.service.UserService;
import com.ps.flowwork_backend.authentication_service.util.CustomUserDetails;
import com.ps.flowwork_backend.authentication_service.util.JWTUtil;
import com.ps.flowwork_backend.common.dto.LoginRequestDTO;
import com.ps.flowwork_backend.common.dto.LoginResponseDTO;
import com.ps.flowwork_backend.handler.customExceptions.InvalidCredentials;
import com.ps.flowwork_backend.handler.customExceptions.OTPNotVerified;
import com.ps.flowwork_backend.handler.customExceptions.UserAlreadyRegistered;
import com.ps.flowwork_backend.handler.customExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UsersRepo usersRepo;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    @Transactional
    public UserResponseDTO registerUser(UserRequestDTO request) {
        if(usersRepo.existsByEmail(request.getEmail())){
            throw new UserAlreadyRegistered("User Already Registered with Email "+request.getEmail());
        }
        Users user = UserDTOMapper.toEntity(request);
        user.setPasswordHash(encoder.encode(request.getPasswordHash()));
        return UserDTOMapper.toDTO(usersRepo.save(user));
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        System.out.println("Inside Login");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.email(),loginRequestDTO.password()));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if(userDetails == null){
            throw new InvalidCredentials("Invalid Credentials");
        }
        Users user = userDetails.getUsers();
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        return new LoginResponseDTO(UserDTOMapper.toDTO(user),accessToken,refreshToken);
    }

    @Override
    public Boolean logout(UUID userId) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean forgetPassword(String email, String OTP, PasswordChangeRequest passwordChangeRequest) {
        Users user = usersRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User Not Found with Email "+email));
        if(!user.getIsOTPVerified()){
            throw new OTPNotVerified("OTP not verified");
        }

        user.setPasswordHash(encoder.encode(passwordChangeRequest.newPassword()));
        user.setOTP(null);
        user.setIsOTPVerified(false);
        user.setOTPExpiry(null);
        usersRepo.save(user);
        return true;
    }

    @Override
    public Boolean sendOTP(String email) {
        System.out.println("OTP Sent will implement Later");
        return true;
    }

    @Override
    public Boolean changePassword(PasswordChangeRequest passwordChangeRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails != null){
            Users user = userDetails.getUsers();
            if (!encoder.matches(passwordChangeRequest.oldPassword(),user.getPasswordHash())) {
                throw new RuntimeException("Old Password do not match");
            }
            user.setPasswordHash(encoder.encode(passwordChangeRequest.newPassword()));
            usersRepo.save(user);
//            log.info("Password changed for userId={}", user.getUserId());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean changeEmail(UUID userId, String newEmail) {
        Users user = usersRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if(usersRepo.existsByEmailAndUserIdNot(user.getEmail(), userId)){
            throw new RuntimeException("Email Already Exists");
        }
        user.setEmail(newEmail);
        usersRepo.save(user);
        return Boolean.TRUE;
    }

    @Override
    public UserResponseDTO getLoggedInUser() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UserDTOMapper.toDTO(userDetails.getUsers());
    }

    @Override
    public Boolean verifyOTP(String email, String OTP) {
        Users user = usersRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOTP() == null) {
            throw new RuntimeException("Something Went Wrong!");
        }

        if (!user.getOTP().equals(OTP))
            throw new RuntimeException("Invalid OTP");

        if (user.getOTPExpiry().isBefore(LocalDateTime.now()))
            throw new RuntimeException("OTP expired");

        user.setIsOTPVerified(true);

        usersRepo.save(user);
//        log.info("OTP verified for userId={}", user.getUserId());
        return Boolean.TRUE;
    }
}
