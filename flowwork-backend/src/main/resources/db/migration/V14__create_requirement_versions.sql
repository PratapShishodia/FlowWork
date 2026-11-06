CREATE TABLE requirement_versions (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    requirement_id      UUID NOT NULL,
    version_number      INTEGER NOT NULL,
    title               VARCHAR(200) NOT NULL,
    description         TEXT,
    acceptance_criteria TEXT,
    changed_by          UUID,
    change_reason       TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_requirement_versions_requirement
        FOREIGN KEY (requirement_id) REFERENCES requirements(id) ON DELETE CASCADE,
    CONSTRAINT fk_requirement_versions_changed_by
        FOREIGN KEY (changed_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT uq_requirement_versions_number UNIQUE (requirement_id, version_number),
    CONSTRAINT ck_requirement_versions_number CHECK (version_number >= 1)
);

CREATE INDEX idx_requirement_versions_requirement
    ON requirement_versions (requirement_id, version_number DESC);
