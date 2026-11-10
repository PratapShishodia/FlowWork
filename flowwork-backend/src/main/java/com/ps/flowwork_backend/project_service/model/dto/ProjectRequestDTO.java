package com.ps.flowwork_backend.project_service.model.dto;

import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.project_service.model.enums.PROJECT_STATUS;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequestDTO {
    private String projectName;
    private String description;
    private String projectKey;
    private PROJECT_STATUS status;
    private PRIORITY priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
