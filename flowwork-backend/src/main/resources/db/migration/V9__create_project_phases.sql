CREATE TABLE project_phases (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id  UUID NOT NULL,
    name        VARCHAR(120) NOT NULL,
    description TEXT,
    start_date  DATE,
    end_date    DATE,
    status      VARCHAR(20) NOT NULL DEFAULT 'NOT_STARTED',
    position    INTEGER NOT NULL DEFAULT 0,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_project_phases_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT ck_project_phases_status
        CHECK (status IN ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED', 'ON_HOLD')),
    CONSTRAINT ck_project_phases_dates
        CHECK (end_date IS NULL OR start_date IS NULL OR end_date >= start_date)
);

CREATE INDEX idx_project_phases_project_position ON project_phases (project_id, position);
