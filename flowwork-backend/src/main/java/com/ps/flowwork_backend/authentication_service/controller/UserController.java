package com.ps.flowwork_backend.authentication_service.controller;

import com.ps.flowwork_backend.authentication_service.model.dto.PasswordChangeRequest;
import com.ps.flowwork_backend.authentication_service.model.dto.UserRequestDTO;
import com.ps.flowwork_backend.authentication_service.model.dto.UserResponseDTO;
import com.ps.flowwork_backend.authentication_service.service.UserService;
import com.ps.flowwork_backend.common.dto.LoginRequestDTO;
import com.ps.flowwork_backend.common.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(requestDTO));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.login(requestDTO));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Boolean> logout(@RequestParam UUID userId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.logout(userId));
    }

    @PutMapping("/auth/forget-password")
    public ResponseEntity<Boolean> forgetPassword(@RequestParam String email, @RequestParam String OTP, @RequestBody PasswordChangeRequest changeRequest){
        return ResponseEntity.ok(userService.forgetPassword(email,OTP,changeRequest));
    }

    @PutMapping("/auth/send-otp")
    public ResponseEntity<Boolean> sendOTP(@RequestParam String email){
        return ResponseEntity.ok(userService.sendOTP(email));
    }

    @PutMapping("/user/changePassword")
    public ResponseEntity<Boolean> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest){
        return ResponseEntity.ok(userService.changePassword(passwordChangeRequest));
    }

    @PutMapping("/user/changeEmail")
    public ResponseEntity<Boolean> changeEmail(@RequestParam UUID userId,@RequestParam String newEmail){
        return ResponseEntity.ok(userService.changeEmail(userId, newEmail));
    }

    @GetMapping("/user/me")
    public ResponseEntity<UserResponseDTO> getMe(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getLoggedInUser());
    }

    @PostMapping("/auth/verifyOTP")
    public ResponseEntity<Boolean> verifyOTP(@RequestParam String email,@RequestParam String OTP){
        return ResponseEntity.ok(userService.verifyOTP(email, OTP));
    }
}
