package com.kanban.task_service.dto.Column;

import java.time.Instant;
import java.util.UUID;

public record ColumnResponseDto(
        UUID id,
        String column_name,
        UUID boardId,
        Integer taskLimit,
        Integer position,
        Instant createdAt
) {
}
