package com.ps.flowwork_backend.project_service.controller;

import com.ps.flowwork_backend.project_service.model.dto.ProjectRequestDTO;
import com.ps.flowwork_backend.project_service.model.dto.ProjectResponseDTO;
import com.ps.flowwork_backend.project_service.model.enums.PROJECT_STATUS;
import com.ps.flowwork_backend.project_service.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(requestDTO));
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> getProjects(@RequestParam(defaultValue = "0") int page_num,@RequestParam(defaultValue = "5") int page_size){
        return ResponseEntity.ok(projectService.getProjects(page_num,page_size));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable UUID projectId){
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable UUID projectId, @RequestBody ProjectRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(projectService.updateProject(projectId,requestDTO));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable UUID projectId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(projectService.deleteProject(projectId));
    }

    @PatchMapping("/{projectId}/status")
    public ResponseEntity<String> changeProjectStatus(@PathVariable UUID projectId, @RequestParam PROJECT_STATUS status){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(projectService.changeProjectStatus(projectId,status));
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<String> archiveProject(@PathVariable UUID projectId){
        return ResponseEntity.ok(projectService.archiveProject(projectId));
    }
}
