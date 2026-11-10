package com.ps.flowwork_backend.authentication_service.model.dto;

public record PasswordChangeRequest(String oldPassword,String newPassword) {
}
