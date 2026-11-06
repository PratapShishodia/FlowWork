CREATE TABLE features (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id          UUID NOT NULL,
    feature_key         VARCHAR(30) NOT NULL,
    title               VARCHAR(200) NOT NULL,
    description         TEXT,
    priority            VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    status              VARCHAR(20) NOT NULL DEFAULT 'PROPOSED',
    estimated_effort    INTEGER,
    created_by          UUID,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_features_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_features_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT uq_features_project_key UNIQUE (project_id, feature_key),
    CONSTRAINT ck_features_priority
        CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    CONSTRAINT ck_features_status
        CHECK (status IN ('PROPOSED', 'READY', 'IN_PROGRESS', 'DONE', 'CANCELLED')),
    CONSTRAINT ck_features_effort CHECK (estimated_effort IS NULL OR estimated_effort >= 0)
);

CREATE INDEX idx_features_project ON features (project_id);
CREATE INDEX idx_features_status ON features (status);
