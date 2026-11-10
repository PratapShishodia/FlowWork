package com.ps.flowwork_backend.project_service.service.impl;

import com.ps.flowwork_backend.authentication_service.service.UserService;
import com.ps.flowwork_backend.authentication_service.service.impl.UserServiceImpl;
import com.ps.flowwork_backend.authentication_service.util.CustomUserDetails;
import com.ps.flowwork_backend.handler.customExceptions.ProjectNotFound;
import com.ps.flowwork_backend.project_service.model.dto.ProjectRequestDTO;
import com.ps.flowwork_backend.project_service.model.dto.ProjectResponseDTO;
import com.ps.flowwork_backend.project_service.model.entity.Project;
import com.ps.flowwork_backend.project_service.model.enums.PROJECT_STATUS;
import com.ps.flowwork_backend.project_service.model.mapper.ProjectDTOMapper;
import com.ps.flowwork_backend.project_service.repository.ProjectRepo;
import com.ps.flowwork_backend.project_service.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;

    @Override
    @Transactional
    public ProjectResponseDTO createProject(ProjectRequestDTO requestDTO) {
        Project project = ProjectDTOMapper.toEntity(requestDTO);
        return ProjectDTOMapper.toDTO(projectRepo.save(project));
    }

    @Override
    public List<ProjectResponseDTO> getProjects(int page_num, int page_size) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String createdBy = userDetails.getUsers().getUserId().toString();
        Pageable pageable = PageRequest.of(page_num,page_size, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Project> page = projectRepo.findByCreatedBy(createdBy,pageable);
        List<Project> projectList = page.getContent();
        return projectList.stream().map(ProjectDTOMapper::toDTO).toList();
    }

    @Override
    public ProjectResponseDTO getProjectById(UUID projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(()-> new ProjectNotFound("Project Not Found with Project Id: "+projectId));
        return ProjectDTOMapper.toDTO(project);
    }

    @Override
    @Transactional
    public ProjectResponseDTO updateProject(UUID projectId, ProjectRequestDTO requestDTO) {
        Project project = projectRepo.findById(projectId).orElseThrow(()-> new ProjectNotFound("Project Not Found with Project Id: "+projectId));
        if(project.getProjectName() != null){
            project.setProjectName(requestDTO.getProjectName());
        }
        if(project.getDescription() != null){
            project.setDescription(requestDTO.getDescription());
        }
        if(project.getProjectKey() != null){
            project.setProjectKey(requestDTO.getProjectKey());
        }
        if(project.getStatus() != null){
            project.setStatus(requestDTO.getStatus());
        }
        if(project.getPriority() != null){
            project.setPriority(requestDTO.getPriority());
        }
        if(project.getEndDate() != null){
            project.setEndDate(requestDTO.getEndDate());
        }
        return ProjectDTOMapper.toDTO(projectRepo.save(project));
    }

    @Override
    @Transactional
    public String deleteProject(UUID projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(()-> new ProjectNotFound("Project Not Found with Project Id: "+projectId));
        projectRepo.delete(project);
        return "Project Deleted Successfully";
    }

    @Override
    @Transactional
    public String changeProjectStatus(UUID projectId, PROJECT_STATUS status) {
        Project project = projectRepo.findById(projectId).orElseThrow(()-> new ProjectNotFound("Project Not Found with Project Id: "+projectId));
        project.setStatus(status);
        projectRepo.save(project);
        return "Status Updated Successfully";
    }

    @Override
    public String archiveProject(UUID projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(()-> new ProjectNotFound("Project Not Found with Project Id: "+projectId));
        project.setStatus(PROJECT_STATUS.ARCHIVED);
        return "Project Archived Successfully";
    }
}
