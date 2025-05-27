package com.kanban.task_service.dto;

import java.util.UUID;

public record ColumnPatchDto(
        String name,
        UUID boardId,
        Integer position
) {}
