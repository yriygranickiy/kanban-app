package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.TaskRequestDto;
import com.kanban.task_service.dto.TaskResponseDto;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDto taskRequestDto, Column column_id) {
        return Task.builder()
                .title(taskRequestDto.title())
                .description(taskRequestDto.description())
                .columnId(column_id)
                .assigneeId(taskRequestDto.assigneeId())
                .priority(taskRequestDto.priority())
                .status(taskRequestDto.status())
                .dueDate(taskRequestDto.dueDate())
                .build();
    }

    public TaskResponseDto toDto (Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getColumnId().getId(),
                task.getAssigneeId(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt()
        );
    }
}
