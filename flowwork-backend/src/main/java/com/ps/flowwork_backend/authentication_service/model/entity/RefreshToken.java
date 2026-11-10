package com.ps.flowwork_backend.authentication_service.model.entity;
import com.ps.flowwork_backend.authentication_service.model.enums.STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RefreshToken {
    @Id
    private UUID refreshTokenId;
    private UUID userId;
    private String refreshToken;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    @Enumerated(EnumType.STRING)
    private STATUS status;
    @PrePersist
    protected void onCreate() {
        this.issuedAt = LocalDateTime.now();
        this.status = STATUS.ACTIVE;
        this.expiresAt = LocalDateTime.now().plusDays(7);
    }
}
