CREATE TABLE labels (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id  UUID NOT NULL,
    name        VARCHAR(60) NOT NULL,
    color       VARCHAR(7) NOT NULL DEFAULT '#64748B',
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_labels_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT uq_labels_project_name UNIQUE (project_id, name),
    CONSTRAINT ck_labels_color
        CHECK (color ~ '^#[0-9A-Fa-f]{6}$')
);

CREATE TABLE task_labels (
    task_id     UUID NOT NULL,
    label_id    UUID NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (task_id, label_id),
    CONSTRAINT fk_task_labels_task
        FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT fk_task_labels_label
        FOREIGN KEY (label_id) REFERENCES labels(id) ON DELETE CASCADE
);

CREATE TABLE requirement_labels (
    requirement_id UUID NOT NULL,
    label_id       UUID NOT NULL,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (requirement_id, label_id),
    CONSTRAINT fk_requirement_labels_requirement
        FOREIGN KEY (requirement_id) REFERENCES requirements(id) ON DELETE CASCADE,
    CONSTRAINT fk_requirement_labels_label
        FOREIGN KEY (label_id) REFERENCES labels(id) ON DELETE CASCADE
);

CREATE TABLE feature_labels (
    feature_id  UUID NOT NULL,
    label_id    UUID NOT NULL,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (feature_id, label_id),
    CONSTRAINT fk_feature_labels_feature
        FOREIGN KEY (feature_id) REFERENCES features(id) ON DELETE CASCADE,
    CONSTRAINT fk_feature_labels_label
        FOREIGN KEY (label_id) REFERENCES labels(id) ON DELETE CASCADE
);

CREATE INDEX idx_labels_project ON labels (project_id);
