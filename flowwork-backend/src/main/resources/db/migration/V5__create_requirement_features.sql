CREATE TABLE requirement_features (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    requirement_id      UUID NOT NULL,
    feature_id          UUID NOT NULL,
    relationship_type   VARCHAR(30) NOT NULL DEFAULT 'IMPLEMENTS',
    notes               TEXT,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_requirement_features_requirement
        FOREIGN KEY (requirement_id) REFERENCES requirements(id) ON DELETE CASCADE,
    CONSTRAINT fk_requirement_features_feature
        FOREIGN KEY (feature_id) REFERENCES features(id) ON DELETE CASCADE,
    CONSTRAINT uq_requirement_features_pair UNIQUE (requirement_id, feature_id),
    CONSTRAINT ck_requirement_features_relationship
        CHECK (relationship_type IN ('IMPLEMENTS', 'SUPPORTS', 'PARTIALLY_COVERS'))
);

CREATE INDEX idx_requirement_features_feature ON requirement_features (feature_id);
