package com.kanban.task_service.dto.Board;

import java.util.UUID;

public record BoardPatchDto(
        String name,
        UUID ownerId
) {}
