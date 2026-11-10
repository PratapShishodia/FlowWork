package com.ps.flowwork_backend.project_service.model.entity;

import com.ps.flowwork_backend.common.annotations.ValidDateRange;
import com.ps.flowwork_backend.common.entity.BaseEntity;
import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.project_service.model.enums.PROJECT_STATUS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ValidDateRange
public class Project extends BaseEntity {
    @Id
    private UUID projectId;
    @Column(nullable = false)
    private String projectName;
    private String description;
    @Column(nullable = false,unique = true,length = 20)
    private String projectKey;
    @Enumerated(EnumType.STRING)
    private PROJECT_STATUS status;
    @Enumerated(EnumType.STRING)
    private PRIORITY priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @PrePersist
    public void onCreate(){
        this.projectId = UUID.randomUUID();
    }
}
