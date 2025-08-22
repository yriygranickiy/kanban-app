package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Board.BoardResponseDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.dto.TaskBoardColumn.TaskBoardColumResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.ColumnBoardTasks;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskBoardColumnMapper {

    TaskBoardColumResponseDto toDto(ColumnBoardTasks entity);

    BoardResponseDto toDto(Board entity);

    ColumnResponseDto toDto(Column entity);

}
