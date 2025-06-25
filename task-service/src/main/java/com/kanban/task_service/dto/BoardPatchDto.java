package com.kanban.task_service.dto;

import java.util.UUID;

public record BoardPatchDto(
        String name,
        UUID ownerId
) {}
