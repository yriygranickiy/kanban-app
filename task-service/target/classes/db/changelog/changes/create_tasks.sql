CREATE TABLE tasks (
   id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
   title VARCHAR(255) NOT NULL,
   description TEXT,
   column_id UUID NOT NULL REFERENCES columns(id) ON DELETE CASCADE,
   assignee_id UUID, -- предполагаем, что ID пользователя придет из auth-сервиса
   status VARCHAR(50) DEFAULT 'TODO',
   priority VARCHAR(50) DEFAULT 'NORMAL',
   due_date DATE,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
