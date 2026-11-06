CREATE TABLE requirements (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id          UUID NOT NULL,
    requirement_key     VARCHAR(30) NOT NULL,
    title               VARCHAR(200) NOT NULL,
    description         TEXT,
    requirement_type    VARCHAR(30) NOT NULL DEFAULT 'FUNCTIONAL',
    priority            VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    status              VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    acceptance_criteria TEXT,
    current_version     INTEGER NOT NULL DEFAULT 1,
    created_by          UUID,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_requirements_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_requirements_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT uq_requirements_project_key UNIQUE (project_id, requirement_key),
    CONSTRAINT ck_requirements_type
        CHECK (requirement_type IN ('FUNCTIONAL', 'NON_FUNCTIONAL', 'TECHNICAL', 'BUSINESS')),
    CONSTRAINT ck_requirements_priority
        CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    CONSTRAINT ck_requirements_status
        CHECK (status IN ('DRAFT', 'PROPOSED', 'APPROVED', 'IN_PROGRESS', 'COMPLETED', 'REJECTED')),
    CONSTRAINT ck_requirements_current_version CHECK (current_version >= 1)
);

CREATE INDEX idx_requirements_project ON requirements (project_id);
CREATE INDEX idx_requirements_status ON requirements (status);
CREATE INDEX idx_requirements_priority ON requirements (priority);
