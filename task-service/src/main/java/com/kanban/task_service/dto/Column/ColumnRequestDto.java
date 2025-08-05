package com.kanban.task_service.dto.Column;

import java.util.UUID;

public record ColumnRequestDto(
        String column_name,
        UUID boardId,
        int task_limit
) {}
