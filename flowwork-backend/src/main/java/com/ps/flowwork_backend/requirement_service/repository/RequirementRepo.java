package com.ps.flowwork_backend.requirement_service.repository;

import com.ps.flowwork_backend.requirement_service.model.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequirementRepo extends JpaRepository<Requirement, UUID> {
    Requirement findByRequirementId(UUID requirementId);
    List<Requirement> findByProjectId(UUID projectId);
}
