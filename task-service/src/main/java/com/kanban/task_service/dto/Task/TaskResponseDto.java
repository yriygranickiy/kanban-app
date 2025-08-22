package com.kanban.task_service.dto.Task;

import com.kanban.task_service.dto.TaskBoardColumn.TaskBoardColumResponseDto;
import com.kanban.task_service.model.TaskPriority;
import com.kanban.task_service.model.TaskStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        UUID assigneeId,
        TaskStatus status,
        TaskPriority priority,
        LocalDate due_date,
        Instant createdAt,
        UUID id_user_creator,
        List<TaskBoardColumResponseDto> taskBoardColumns) {
}
