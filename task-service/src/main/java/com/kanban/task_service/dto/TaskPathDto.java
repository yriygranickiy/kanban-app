package com.kanban.task_service.dto;

import com.kanban.task_service.model.TaskPriority;
import com.kanban.task_service.model.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskPathDto(
    String title,
    String description,
    UUID columnId,
    UUID assigneeId,
    TaskStatus status,
    TaskPriority priority,
    Instant dueDate
) {
}
