package com.kanban.task_service.dto.Task;

import com.kanban.task_service.model.TaskPriority;
import com.kanban.task_service.model.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequestDto(
        @NotBlank(message = "Please provide task title")
        String title,
        @NotBlank(message = "Please provide task description")
        String description,
        UUID boardId,
        UUID assigneeId,
        TaskStatus status,
        TaskPriority priority,
        @FutureOrPresent
        LocalDate due_date
) {}
