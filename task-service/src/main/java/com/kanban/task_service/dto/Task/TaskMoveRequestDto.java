package com.kanban.task_service.dto.Task;

import java.util.UUID;

public record TaskMoveRequestDto(
        UUID id_column,
        UUID board_id,
        Integer position
) {
}
