CREATE TABLE tasks (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id          UUID NOT NULL,
    feature_id          UUID,
    parent_task_id      UUID,
    task_key            VARCHAR(30) NOT NULL,
    title               VARCHAR(200) NOT NULL,
    description         TEXT,
    status              VARCHAR(20) NOT NULL DEFAULT 'TODO',
    priority            VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    task_type           VARCHAR(20) NOT NULL DEFAULT 'TASK',
    estimated_hours     NUMERIC(8,2),
    actual_hours        NUMERIC(8,2),
    start_date          DATE,
    due_date            DATE,
    position            NUMERIC(12,4) NOT NULL DEFAULT 0,
    created_by          UUID,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tasks_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_tasks_feature
        FOREIGN KEY (feature_id) REFERENCES features(id) ON DELETE SET NULL,
    CONSTRAINT fk_tasks_parent
        FOREIGN KEY (parent_task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT fk_tasks_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT uq_tasks_project_key UNIQUE (project_id, task_key),
    CONSTRAINT ck_tasks_status
        CHECK (status IN ('BACKLOG', 'TODO', 'IN_PROGRESS', 'IN_REVIEW', 'DONE')),
    CONSTRAINT ck_tasks_priority
        CHECK (priority IN ('LOW', 'MEDIUM', 'HIGH', 'CRITICAL')),
    CONSTRAINT ck_tasks_type
        CHECK (task_type IN ('TASK', 'BUG', 'IMPROVEMENT', 'RESEARCH')),
    CONSTRAINT ck_tasks_hours
        CHECK ((estimated_hours IS NULL OR estimated_hours >= 0)
           AND (actual_hours IS NULL OR actual_hours >= 0)),
    CONSTRAINT ck_tasks_dates
        CHECK (due_date IS NULL OR start_date IS NULL OR due_date >= start_date),
    CONSTRAINT ck_tasks_not_own_parent
        CHECK (parent_task_id IS NULL OR parent_task_id <> id)
);

CREATE INDEX idx_tasks_project ON tasks (project_id);
CREATE INDEX idx_tasks_feature ON tasks (feature_id);
CREATE INDEX idx_tasks_parent ON tasks (parent_task_id);
CREATE INDEX idx_tasks_status ON tasks (status);
CREATE INDEX idx_tasks_due_date ON tasks (due_date);
