package com.ps.flowwork_backend.authentication_service.repository;

import com.ps.flowwork_backend.authentication_service.model.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<Users, UUID> {
    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByEmailAndUserIdNot(String email,UUID userId);
    Optional<Users> findByUserId(UUID userId);
}
