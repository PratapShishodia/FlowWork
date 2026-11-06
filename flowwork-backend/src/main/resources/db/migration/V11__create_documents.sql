CREATE TABLE documents (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id      UUID NOT NULL,
    title           VARCHAR(200) NOT NULL,
    content         TEXT,
    storage_key     TEXT,
    document_type   VARCHAR(40) NOT NULL DEFAULT 'GENERAL',
    created_by      UUID,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_documents_project
        FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    CONSTRAINT fk_documents_created_by
        FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT ck_documents_type
        CHECK (document_type IN ('GENERAL', 'TECHNICAL', 'API', 'ARCHITECTURE', 'MEETING_NOTES', 'DECISION'))
);

CREATE INDEX idx_documents_project ON documents (project_id);
