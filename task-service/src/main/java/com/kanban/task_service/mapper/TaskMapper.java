package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.model.Task;
import org.mapstruct.*;


@Mapper(componentModel = "spring",uses = TaskBoardColumnMapper.class)
public interface TaskMapper {

    @Mapping(target = "taskBoardColumns", source = "columnBoardTasks")
    TaskResponseDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTask(TaskPathDto dto, @MappingTarget Task task);

}
