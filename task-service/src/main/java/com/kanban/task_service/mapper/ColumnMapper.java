package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ColumnMapper {

     Column toEntity(ColumnRequestDto columnRequestDto, Board board);

     @Mapping(source = "board.id", target = "boardId")
     ColumnResponseDto toDto(Column column);

     @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
     void updateColumn(ColumnPatchDto dto, @MappingTarget Column column);
}

