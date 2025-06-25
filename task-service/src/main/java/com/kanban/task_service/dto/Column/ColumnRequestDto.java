package com.kanban.task_service.dto.Column;

import java.util.UUID;

public record ColumnRequestDto(
        String name,
        UUID boardId,
        Integer taskLimit,
        Integer position
) {}
