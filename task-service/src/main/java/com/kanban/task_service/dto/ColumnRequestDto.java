package com.kanban.task_service.dto;

import java.util.UUID;

public record ColumnRequestDto(
        String name,
        UUID boardId,
        Integer position
) {
}
