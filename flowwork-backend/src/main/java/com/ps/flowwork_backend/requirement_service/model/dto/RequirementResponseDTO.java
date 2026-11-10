package com.ps.flowwork_backend.requirement_service.model.dto;

import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_STATUS;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_TYPE;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementResponseDTO {
    private UUID requirementId;
    private UUID projectId;
    private String requirementKey;
    private String title;
    private String description;
    private REQ_TYPE type;
    private PRIORITY priority;
    private REQ_STATUS status;
    private String acceptanceCriteria;
    private Integer currentVersion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
