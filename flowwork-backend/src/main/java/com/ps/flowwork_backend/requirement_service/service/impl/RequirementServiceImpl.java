package com.ps.flowwork_backend.requirement_service.service.impl;

import com.ps.flowwork_backend.project_service.model.enums.PRIORITY;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementRequestDTO;
import com.ps.flowwork_backend.requirement_service.model.dto.RequirementResponseDTO;
import com.ps.flowwork_backend.requirement_service.model.entity.Requirement;
import com.ps.flowwork_backend.requirement_service.model.enums.REQ_STATUS;
import com.ps.flowwork_backend.requirement_service.model.mapper.RequirementDTOMapper;
import com.ps.flowwork_backend.requirement_service.repository.RequirementRepo;
import com.ps.flowwork_backend.requirement_service.service.RequirementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepo requirementRepo;

    @Override
    @Transactional
    public RequirementResponseDTO createRequirement(RequirementRequestDTO requestDTO) {
        Requirement requirement = RequirementDTOMapper.toEntity(requestDTO);
        return RequirementDTOMapper.toDTO(requirementRepo.save(requirement));
    }

    @Override
    public List<RequirementResponseDTO> getRequirementOfProject(UUID projectId) {
        List<Requirement> requirementList = requirementRepo.findByProjectId(projectId);
        return requirementList.stream().map(RequirementDTOMapper::toDTO).toList();
    }

    @Override
    public RequirementResponseDTO getRequirement(UUID requirementId) {
        return RequirementDTOMapper.toDTO(requirementRepo.findByRequirementId(requirementId));
    }

    @Override
    @Transactional
    public RequirementResponseDTO updateRequirement(UUID requirementId, RequirementRequestDTO requestDTO) {
        Requirement requirement = requirementRepo.findByRequirementId(requirementId);
        if(requestDTO.getRequirementKey()!=null){
            requirement.setRequirementKey(requestDTO.getRequirementKey());
        }
        if(requestDTO.getPriority()!=null){
            requirement.setPriority(requestDTO.getPriority());
        }
        if(requestDTO.getType()!=null){
            requirement.setType(requestDTO.getType());
        }
        if(requestDTO.getStatus()!=null){
            requirement.setStatus(requestDTO.getStatus());
        }
        if(requestDTO.getDescription()!=null){
            requirement.setDescription(requestDTO.getDescription());
        }
        if(requestDTO.getTitle()!=null){
            requirement.setTitle(requestDTO.getTitle());
        }
        if(requestDTO.getAcceptanceCriteria()!=null){
            requirement.setAcceptanceCriteria(requestDTO.getAcceptanceCriteria());
        }
        if(requestDTO.getCurrentVersion()!=null){
            requirement.setCurrentVersion(requestDTO.getCurrentVersion());
        }
        return RequirementDTOMapper.toDTO(requirementRepo.save(requirement));
    }

    @Override
    @Transactional
    public String deleteRequirement(UUID requirementId) {
        Requirement requirement = requirementRepo.findByRequirementId(requirementId);
        requirementRepo.delete(requirement);
        return "Successfully deleted requirement";
    }

    @Override
    @Transactional
    public String statusUpdate(UUID requirementId, REQ_STATUS status) {
        Requirement requirement = requirementRepo.findByRequirementId(requirementId);
        requirement.setStatus(status);
        return "Successfully updated requirement Status";
    }

    @Override
    public String changePriority(UUID requirementId, PRIORITY priority) {
        Requirement requirement = requirementRepo.findByRequirementId(requirementId);
        requirement.setPriority(priority);
        return "Successfully updated requirement Priority";
    }
}
