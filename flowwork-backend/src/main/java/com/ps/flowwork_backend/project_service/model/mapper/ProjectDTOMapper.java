package com.ps.flowwork_backend.project_service.model.mapper;

import com.ps.flowwork_backend.project_service.model.dto.ProjectRequestDTO;
import com.ps.flowwork_backend.project_service.model.dto.ProjectResponseDTO;
import com.ps.flowwork_backend.project_service.model.entity.Project;

public class ProjectDTOMapper {

    public static ProjectResponseDTO toDTO(Project project){
        return ProjectResponseDTO.builder()
                .projectId(project.getProjectId())
                .projectName(project.getProjectName())
                .description(project.getDescription())
                .projectKey(project.getProjectKey())
                .status(project.getStatus())
                .priority(project.getPriority())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdBy(project.getCreatedBy())
                .createdAt(project.getCreatedAt())
                .updatedBy(project.getUpdatedBy())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    public static Project toEntity(ProjectRequestDTO requestDTO){
        return Project.builder()
                .projectName(requestDTO.getProjectName())
                .description(requestDTO.getDescription())
                .projectKey(requestDTO.getProjectKey())
                .status(requestDTO.getStatus())
                .priority(requestDTO.getPriority())
                .startDate(requestDTO.getStartDate())
                .endDate(requestDTO.getEndDate())
                .build();
    }
}
