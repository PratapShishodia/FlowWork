package com.ps.flowwork_backend.common.dto;

import com.ps.flowwork_backend.authentication_service.model.dto.UserResponseDTO;

public record LoginResponseDTO(UserResponseDTO responseDTO,String authToken,String refreshToken) {
}
