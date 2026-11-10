package com.ps.flowwork_backend.requirement_service.model.mapper;

import com.ps.flowwork_backend.requirement_service.model.dto.RequirementRequestDTO;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementResponseDTO;
import com.ps.flowwork_backend.requirement_service.model.entity.Requirement;

public class RequirementDTOMapper {

    public static RequirementResponseDTO toDTO(Requirement requirement){
        return RequirementResponseDTO.builder()
                .requirementId(requirement.getRequirementId())
                .projectId(requirement.getProjectId())
                .requirementKey(requirement.getRequirementKey())
                .title(requirement.getTitle())
                .description(requirement.getDescription())
                .type(requirement.getType())
                .priority(requirement.getPriority())
                .status(requirement.getStatus())
                .acceptanceCriteria(requirement.getAcceptanceCriteria())
                .currentVersion(requirement.getCurrentVersion())
                .createdAt(requirement.getCreatedAt())
                .updatedAt(requirement.getUpdatedAt())
                .build();
    }

    public static Requirement toEntity(RequirementRequestDTO requestDTO){
        return Requirement.builder()
                .projectId(requestDTO.getProjectId())
                .requirementKey(requestDTO.getRequirementKey())
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .type(requestDTO.getType())
                .priority(requestDTO.getPriority())
                .status(requestDTO.getStatus())
                .acceptanceCriteria(requestDTO.getAcceptanceCriteria())
                .currentVersion(requestDTO.getCurrentVersion())
                .build();
    }
}
