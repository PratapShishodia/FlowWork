CREATE TABLE comments (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content         TEXT NOT NULL,
    author_id       UUID,
    task_id         UUID,
    requirement_id  UUID,
    feature_id      UUID,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_comments_author
        FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT fk_comments_task
        FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_requirement
        FOREIGN KEY (requirement_id) REFERENCES requirements(id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_feature
        FOREIGN KEY (feature_id) REFERENCES features(id) ON DELETE CASCADE,
    CONSTRAINT ck_comments_exactly_one_parent
        CHECK (
            (CASE WHEN task_id IS NOT NULL THEN 1 ELSE 0 END) +
            (CASE WHEN requirement_id IS NOT NULL THEN 1 ELSE 0 END) +
            (CASE WHEN feature_id IS NOT NULL THEN 1 ELSE 0 END) = 1
        ),
    CONSTRAINT ck_comments_content_not_blank CHECK (length(trim(content)) > 0)
);

CREATE INDEX idx_comments_task_created ON comments (task_id, created_at);
CREATE INDEX idx_comments_requirement_created ON comments (requirement_id, created_at);
CREATE INDEX idx_comments_feature_created ON comments (feature_id, created_at);
