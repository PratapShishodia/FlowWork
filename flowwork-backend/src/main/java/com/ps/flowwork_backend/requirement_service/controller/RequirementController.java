package com.ps.flowwork_backend.requirement_service.controller;

import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementRequestDTO;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementResponseDTO;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_STATUS;
import com.ps.flowwork_backend.requirement_service.service.RequirementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;

    @PostMapping("/projects/{projectId}/requirements")
    public ResponseEntity<RequirementResponseDTO> addRequirement(@RequestBody RequirementRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(requirementService.createRequirement(requestDTO));
    }

    @GetMapping("/projects/{projectId}/requirements")
    public ResponseEntity<List<RequirementResponseDTO>> getRequirements(@PathVariable UUID projectId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requirementService.getRequirementOfProject(projectId));
    }

    @GetMapping("/requirements/{requirementId}")
    public ResponseEntity<RequirementResponseDTO> getRequirement(@PathVariable UUID requirementId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requirementService.getRequirement(requirementId));
    }

    @PutMapping("/requirements/{requirementId}")
    public ResponseEntity<RequirementResponseDTO>  updateRequirement(@PathVariable UUID requirementId, @RequestBody RequirementRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(requirementService.updateRequirement(requirementId, requestDTO));
    }

    @DeleteMapping("/requirements/{requirementId}")
    public ResponseEntity<String> deleteRequirement(@PathVariable UUID requirementId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requirementService.deleteRequirement(requirementId));
    }

    @PatchMapping("requirements/{requirementId}/status")
    public ResponseEntity<String> updateRequirementStatus(@PathVariable UUID requirementId, @RequestParam REQ_STATUS status){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requirementService.statusUpdate(requirementId,status));
    }

    @PatchMapping("/requirements/{requirementId}/priority")
    public ResponseEntity<String>  updateRequirementPriority(@PathVariable UUID requirementId, @RequestParam PRIORITY priority){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(requirementService.changePriority(requirementId,priority));
    }
}
