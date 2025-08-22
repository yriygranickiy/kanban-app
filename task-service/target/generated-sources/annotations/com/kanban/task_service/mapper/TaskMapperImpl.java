package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.dto.TaskBoardColumn.TaskBoardColumResponseDto;
import com.kanban.task_service.model.ColumnBoardTasks;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.model.TaskPriority;
import com.kanban.task_service.model.TaskStatus;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T14:57:29+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Autowired
    private TaskBoardColumnMapper taskBoardColumnMapper;

    @Override
    public TaskResponseDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        List<TaskBoardColumResponseDto> taskBoardColumns = null;
        UUID id = null;
        String title = null;
        String description = null;
        UUID assigneeId = null;
        TaskStatus status = null;
        TaskPriority priority = null;
        LocalDate due_date = null;
        Instant createdAt = null;
        UUID id_user_creator = null;

        taskBoardColumns = columnBoardTasksListToTaskBoardColumResponseDtoList( task.getColumnBoardTasks() );
        id = task.getId();
        title = task.getTitle();
        description = task.getDescription();
        assigneeId = task.getAssigneeId();
        status = task.getStatus();
        priority = task.getPriority();
        due_date = task.getDue_date();
        createdAt = task.getCreatedAt();
        id_user_creator = task.getId_user_creator();

        TaskResponseDto taskResponseDto = new TaskResponseDto( id, title, description, assigneeId, status, priority, due_date, createdAt, id_user_creator, taskBoardColumns );

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
        if ( dto.due_date() != null ) {
            task.setDue_date( dto.due_date() );
        }
    }

    protected List<TaskBoardColumResponseDto> columnBoardTasksListToTaskBoardColumResponseDtoList(List<ColumnBoardTasks> list) {
        if ( list == null ) {
            return null;
        }

        List<TaskBoardColumResponseDto> list1 = new ArrayList<TaskBoardColumResponseDto>( list.size() );
        for ( ColumnBoardTasks columnBoardTasks : list ) {
            list1.add( taskBoardColumnMapper.toDto( columnBoardTasks ) );
        }

        return list1;
    }
}
