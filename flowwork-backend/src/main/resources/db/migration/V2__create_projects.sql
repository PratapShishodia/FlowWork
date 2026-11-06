CREATE TABLE projects (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name            VARCHAR(160) NOT NULL,
    description     TEXT,
    project_key     VARCHAR(20) NOT NULL,
    status          VARCHAR(20) NOT NULL DEFAULT 'PLANNING',
    priority        VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    start_date      DATE,
    end_date        DATE,
    created_by      UUID NOT NULL,
    archived_at     TIMESTAMPTZ,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_projects_created_by
        FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT uq_projects_project_key UNIQUE (project_key),
    CONSTRAINT ck_projects_status
        CHECK (status IN ('PLANNING', 'ACTIVE', 'ON_HOLD', 'COMPLETED', 'ARCHIVED')),
    CONSTRAINT ck_projects_priority
        CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    CONSTRAINT ck_projects_dates
        CHECK (end_date IS NULL OR start_date IS NULL OR end_date >= start_date)
);

CREATE INDEX idx_projects_created_by ON projects (created_by);
CREATE INDEX idx_projects_status ON projects (status);
