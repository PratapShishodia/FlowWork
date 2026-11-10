package com.ps.flowwork_backend.authentication_service.util;


import com.ps.flowwork_backend.authentication_service.model.entity.Users;
import com.ps.flowwork_backend.authentication_service.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Users user= usersRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found!"));
        return new CustomUserDetails(user);
    }
}