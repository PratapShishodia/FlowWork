package com.ps.flowwork_backend.authentication_service.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImgURL;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
