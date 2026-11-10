package com.ps.flowwork_backend.project_service.service;

import com.ps.flowwork_backend.project_service.model.dto.ProjectRequestDTO;
import com.ps.flowwork_backend.project_service.model.dto.ProjectResponseDTO;
import com.ps.flowwork_backend.project_service.model.enums.PROJECT_STATUS;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
//    POST /api/projects Create project
    ProjectResponseDTO createProject(ProjectRequestDTO requestDTO);
//    GET /api/projects List projects; support search, status and pagination
    List<ProjectResponseDTO> getProjects(int page_num,int page_size);
//    GET /api/projects/{projectId} Get project details
    ProjectResponseDTO getProjectById(UUID projectId);
//    PUT /api/projects/{projectId} Update project
    ProjectResponseDTO updateProject(UUID projectId, ProjectRequestDTO requestDTO);
//    DELETE /api/projects/{projectId} Delete or soft-delete project
    String deleteProject(UUID projectId);
//    PATCH /api/projects/{projectId}/status Change project status
    String changeProjectStatus(UUID projectId, PROJECT_STATUS status);
//    POST /api/projects/{projectId}/archive Archive project
    String archiveProject(UUID projectId);
//    GET /api/projects/{projectId}/overview Project overview read model
//    GET /api/projects/{projectId}/statistics Project counts and progress metrics
//    GET /api/projects/{projectId}/health Project health score and contributing factors
}
