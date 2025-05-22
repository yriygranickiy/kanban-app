package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.BoardCreateRequestDto;
import com.kanban.task_service.dto.ColumnRequestDto;
import com.kanban.task_service.dto.ColumnResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import org.springframework.stereotype.Component;

@Component
public class ColumnMapper {

    public Column toEntity(ColumnRequestDto columnDto, Board board) {
        return Column.builder()
                .name(columnDto.name())
                .board(board)
                .position(columnDto.position())
                .build();
    }

    public ColumnResponseDto toDto(Column column) {
        return new ColumnResponseDto(column.getId(),
                column.getName(),
                column.getBoard().getId(),
                column.getPosition(),
                column.getCreatedAt());
    }
}
