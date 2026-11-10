package com.ps.flowwork_backend.requirement_service.model.entity;

import com.ps.flowwork_backend.common.entity.BaseEntity;
import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_STATUS;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_TYPE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Requirement extends BaseEntity {
    @Id
    private UUID requirementId;
    private UUID projectId;
    @Column(nullable = false,unique = true)
    private String requirementKey;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private REQ_TYPE type;
    @Column(nullable = false)
    private PRIORITY priority;
    private REQ_STATUS status;
    private String acceptanceCriteria;
    @Column(nullable = false)
    private Integer currentVersion;

    @PrePersist()
    public void onCreate(){
        this.requirementId = UUID.randomUUID();
        this.status = REQ_STATUS.DRAFT;
    }
}
