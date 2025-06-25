package com.kanban.task_service.dto.Board;

import java.util.UUID;

public record BoardCreateRequestDto(
        String name,
        UUID ownerId) {}
