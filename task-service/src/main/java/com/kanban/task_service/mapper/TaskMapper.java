package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.TaskPathDto;
import com.kanban.task_service.dto.TaskRequestDto;
import com.kanban.task_service.dto.TaskResponseDto;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import org.mapstruct.*;



@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequestDto taskRequestDto, Column column);

    @Mapping(source = "column.id",target = "columnId")
    TaskResponseDto toDto(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTask(TaskPathDto dto, @MappingTarget Task task);
}
