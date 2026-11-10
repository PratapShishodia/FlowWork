package com.ps.flowwork_backend.authentication_service.repository;

import com.ps.flowwork_backend.authentication_service.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, UUID> {

}
