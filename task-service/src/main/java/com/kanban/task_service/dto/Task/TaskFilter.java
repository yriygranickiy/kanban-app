package com.kanban.task_service.dto.Task;

import com.kanban.task_service.model.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskFilter(
        UUID boardId,
        UUID columnId,
        UUID assignedId,
        TaskStatus status,
        Instant createdAfter,
        Instant createdBefore
) {
    public TaskFilter toFilter() {
        return new TaskFilter(boardId, columnId, assignedId, status, createdAfter, createdBefore);
    }
}
