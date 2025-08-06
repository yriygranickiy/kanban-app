package com.kanban.task_service.dto.Column;

import java.util.UUID;

public record ColumnRequestDto(
        String columnName,
        UUID boardId,
        Integer task_limit
) {}
