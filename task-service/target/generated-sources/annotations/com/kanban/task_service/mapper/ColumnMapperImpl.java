package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-08T19:45:33+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class ColumnMapperImpl implements ColumnMapper {

    @Override
    public Column toEntity(ColumnRequestDto columnRequestDto, Board board) {
        if ( columnRequestDto == null && board == null ) {
            return null;
        }

        Column.ColumnBuilder column = Column.builder();

        if ( columnRequestDto != null ) {
            column.position( columnRequestDto.position() );
            column.taskLimit( columnRequestDto.taskLimit() );
        }
        if ( board != null ) {
            column.id( board.getId() );
            column.createdAt( board.getCreatedAt() );
        }

        return column.build();
    }

    @Override
    public ColumnResponseDto toDto(Column column) {
        if ( column == null ) {
            return null;
        }

        UUID boardId = null;
        UUID id = null;
        String columnName = null;
        Integer taskLimit = null;
        Integer position = null;
        Instant createdAt = null;

        boardId = columnBoardId( column );
        id = column.getId();
        columnName = column.getColumnName();
        taskLimit = column.getTaskLimit();
        position = column.getPosition();
        createdAt = column.getCreatedAt();

        ColumnResponseDto columnResponseDto = new ColumnResponseDto( id, columnName, boardId, taskLimit, position, createdAt );

        return columnResponseDto;
    }

    @Override
    public void updateColumn(ColumnPatchDto dto, Column column) {
        if ( dto == null ) {
            return;
        }

        if ( dto.columnName() != null ) {
            column.setColumnName( dto.columnName() );
        }
        if ( dto.position() != null ) {
            column.setPosition( dto.position() );
        }
        if ( dto.taskLimit() != null ) {
            column.setTaskLimit( dto.taskLimit() );
        }
    }

    private UUID columnBoardId(Column column) {
        Board board = column.getBoard();
        if ( board == null ) {
            return null;
        }
        return board.getId();
    }
}
