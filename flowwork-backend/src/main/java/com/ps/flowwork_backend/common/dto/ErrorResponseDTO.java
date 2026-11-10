package com.ps.flowwork_backend.common.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String description, HttpStatus httpStatus, String message, LocalDateTime now) {
}
