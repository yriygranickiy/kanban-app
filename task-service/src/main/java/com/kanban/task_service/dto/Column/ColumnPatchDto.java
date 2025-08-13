package com.kanban.task_service.dto.Column;

import java.util.UUID;

public record ColumnPatchDto(
        String column_name,
        UUID boardId,
        Integer taskLimit
) {}
