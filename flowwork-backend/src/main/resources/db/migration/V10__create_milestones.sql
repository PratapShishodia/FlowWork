CREATE TABLE milestones (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id      UUID NOT NULL,
    phase_id        UUID,
    name            VARCHAR(160) NOT NULL,
    description     TEXT,
    due_date        DATE,
    status          VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    completed_at    TIMESTAMPTZ,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_milestones_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_milestones_phase
        FOREIGN KEY (phase_id) REFERENCES project_phases(id) ON DELETE SET NULL,
    CONSTRAINT ck_milestones_status
        CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'))
);

CREATE INDEX idx_milestones_project_due ON milestones (project_id, due_date);
CREATE INDEX idx_milestones_phase ON milestones (phase_id);
