package com.kanban.task_service.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record BoardResponseDto(
        UUID id,
        String name,
        UUID ownerId,
        Instant createdAt
) {}
