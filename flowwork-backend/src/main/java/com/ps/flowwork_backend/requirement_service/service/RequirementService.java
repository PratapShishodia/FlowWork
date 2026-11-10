package com.ps.flowwork_backend.requirement_service.service;

import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementRequestDTO;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementResponseDTO;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_STATUS;

import java.util.List;
import java.util.UUID;

public interface RequirementService {
//    POST /api/projects/{projectId}/requirements Create requirement
    RequirementResponseDTO createRequirement(RequirementRequestDTO requestDTO);
//    GET /api/projects/{projectId}/requirements List requirements with filters
    List<RequirementResponseDTO> getRequirementOfProject(UUID projectId);
//    GET /api/requirements/{requirementId} Get requirement
    RequirementResponseDTO getRequirement(UUID requirementId);
//    PUT /api/requirements/{requirementId} Update requirement
    RequirementResponseDTO updateRequirement(UUID requirementId,RequirementRequestDTO requestDTO);
//    DELETE /api/requirements/{requirementId} Delete/archive requirement
    String deleteRequirement(UUID requirementId);
//    PATCH /api/requirements/{requirementId}/status Change lifecycle status
    String statusUpdate(UUID requirementId, REQ_STATUS status);
//    PATCH /api/requirements/{requirementId}/priority Change priority
    String changePriority(UUID requirementId, PRIORITY priority);
//    GET /api/requirements/{requirementId}/features List linked features
//    GET /api/requirements/{requirementId}/history List requirement versions/history
//    GET /api/requirements/{requirementId}/traceability Show downstream links and coverage

}
