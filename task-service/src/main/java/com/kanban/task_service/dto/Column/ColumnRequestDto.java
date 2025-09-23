package com.kanban.task_service.dto.Column;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ColumnRequestDto(
        @NotBlank(message = "Please provide column name")
        String columnName,
        UUID boardId,
        @NotBlank(message = "Please provide task limit")
        @Positive
        @Min(value = 1, message = "Task limit should not be less 1")
        Integer task_limit
) {}
