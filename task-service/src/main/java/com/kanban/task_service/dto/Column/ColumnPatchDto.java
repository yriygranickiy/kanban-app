package com.kanban.task_service.dto.Column;

import java.util.UUID;

public record ColumnPatchDto(
        String columnName,
        UUID boardId,
        Integer taskLimit,
        Integer position
) {}
