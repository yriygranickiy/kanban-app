package com.kanban.task_service.dto.TaskBoardColumn;

import com.kanban.task_service.dto.Board.BoardRequestDto;
import com.kanban.task_service.dto.Board.BoardResponseDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;


public record TaskBoardColumResponseDto(
        BoardResponseDto board,
        ColumnResponseDto column,
        Integer position
) {
}
