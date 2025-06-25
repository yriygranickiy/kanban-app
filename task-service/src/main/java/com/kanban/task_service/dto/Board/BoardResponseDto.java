package com.kanban.task_service.dto.Board;

import java.time.Instant;
import java.util.UUID;

public record BoardResponseDto(
        UUID id,
        String name,
        UUID ownerId,
        Instant createdAt
) {}
