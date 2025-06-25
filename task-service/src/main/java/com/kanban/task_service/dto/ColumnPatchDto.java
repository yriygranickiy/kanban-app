package com.kanban.task_service.dto;

import java.util.UUID;

public record ColumnPatchDto(
        String columnName,
        UUID boardId,
        Integer position
) {}
