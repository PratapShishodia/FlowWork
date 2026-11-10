package com.ps.flowwork_backend.project_service.repository;

import com.ps.flowwork_backend.project_service.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepo extends JpaRepository<Project, UUID> {
    Page<Project> findByCreatedBy(String createdBy,
                                  Pageable pageable);
}
