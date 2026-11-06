CREATE TABLE change_requests (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id          UUID NOT NULL,
    title               VARCHAR(200) NOT NULL,
    description         TEXT NOT NULL,
    reason              TEXT,
    status              VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    impact_analysis     TEXT,
    affected_items      JSONB NOT NULL DEFAULT '[]'::jsonb,
    requested_by        UUID,
    reviewed_by         UUID,
    requested_at        TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reviewed_at         TIMESTAMPTZ,
    implemented_at      TIMESTAMPTZ,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_change_requests_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_change_requests_requested_by
        FOREIGN KEY (requested_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT fk_change_requests_reviewed_by
        FOREIGN KEY (reviewed_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT ck_change_requests_status
        CHECK (status IN ('DRAFT', 'SUBMITTED', 'UNDER_REVIEW', 'APPROVED', 'REJECTED', 'IMPLEMENTED')),
    CONSTRAINT ck_change_requests_affected_items_array
        CHECK (jsonb_typeof(affected_items) = 'array')
);

CREATE INDEX idx_change_requests_project_status
    ON change_requests (project_id, status);
