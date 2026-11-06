CREATE TABLE activity_events (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id      UUID NOT NULL,
    actor_id        UUID,
    entity_type     VARCHAR(40) NOT NULL,
    entity_id       UUID NOT NULL,
    action          VARCHAR(60) NOT NULL,
    description     TEXT,
    metadata        JSONB NOT NULL DEFAULT '{}'::jsonb,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_activity_events_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_activity_events_actor
        FOREIGN KEY (actor_id) REFERENCES users(id) ON DELETE SET NULL
);

CREATE INDEX idx_activity_events_project_created
    ON activity_events (project_id, created_at DESC);
CREATE INDEX idx_activity_events_entity
    ON activity_events (entity_type, entity_id);
CREATE INDEX idx_activity_events_metadata
    ON activity_events USING GIN (metadata);
