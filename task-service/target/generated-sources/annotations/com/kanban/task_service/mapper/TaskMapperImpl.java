package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.model.TaskPriority;
import com.kanban.task_service.model.TaskStatus;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-25T14:57:27+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskRequestDto taskRequestDto, Column column) {
        if ( taskRequestDto == null && column == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        if ( taskRequestDto != null ) {
            task.title( taskRequestDto.title() );
            task.description( taskRequestDto.description() );
            task.assigneeId( taskRequestDto.assigneeId() );
            task.status( taskRequestDto.status() );
            task.priority( taskRequestDto.priority() );
            task.dueDate( taskRequestDto.dueDate() );
        }
        if ( column != null ) {
            task.id( column.getId() );
            task.createdAt( column.getCreatedAt() );
        }

        return task.build();
    }

    @Override
    public TaskResponseDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        UUID columnId = null;
        UUID id = null;
        String title = null;
        String description = null;
        UUID assigneeId = null;
        TaskStatus status = null;
        TaskPriority priority = null;
        Instant dueDate = null;
        Instant createdAt = null;

        columnId = taskColumnId( task );
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        assigneeId = task.getAssigneeId();
        status = task.getStatus();
        priority = task.getPriority();
        dueDate = task.getDueDate();
        createdAt = task.getCreatedAt();

        TaskResponseDto taskResponseDto = new TaskResponseDto( id, title, description, columnId, assigneeId, status, priority, dueDate, createdAt );

        return taskResponseDto;
    }

    @Override
    public void updateTask(TaskPathDto dto, Task task) {
        if ( dto == null ) {
            return;
        }

        if ( dto.title() != null ) {
            task.setTitle( dto.title() );
        }
        if ( dto.description() != null ) {
            task.setDescription( dto.description() );
        }
        if ( dto.assigneeId() != null ) {
            task.setAssigneeId( dto.assigneeId() );
        }
        if ( dto.status() != null ) {
            task.setStatus( dto.status() );
        }
        if ( dto.priority() != null ) {
            task.setPriority( dto.priority() );
        }
        if ( dto.dueDate() != null ) {
            task.setDueDate( dto.dueDate() );
        }
    }

    private UUID taskColumnId(Task task) {
        Column column = task.getColumn();
        if ( column == null ) {
            return null;
        }
        return column.getId();
    }
}
