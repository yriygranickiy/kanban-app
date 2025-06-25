package com.kanban.task_service.dto;

import java.util.UUID;

public record BoardCreateRequestDto(
        String name,
        UUID ownerId) {}
