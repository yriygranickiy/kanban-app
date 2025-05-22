package com.kanban.task_service.dto;

import java.time.Instant;
import java.util.UUID;

public record ColumnResponseDto(
        UUID id,
        String name,
        UUID boardId,
        Integer position,
        Instant createdAt
) {
}
