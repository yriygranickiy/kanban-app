package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.ColumnPatchDto;
import com.kanban.task_service.dto.ColumnRequestDto;
import com.kanban.task_service.dto.ColumnResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-24T15:57:55+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
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
        Integer position = null;
        Instant createdAt = null;

        boardId = columnBoardId( column );
        id = column.getId();
        columnName = column.getColumnName();
        position = column.getPosition();
        createdAt = column.getCreatedAt();

        ColumnResponseDto columnResponseDto = new ColumnResponseDto( id, columnName, boardId, position, createdAt );

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
    }

    private UUID columnBoardId(Column column) {
        Board board = column.getBoard();
        if ( board == null ) {
            return null;
        }
        return board.getId();
    }
}
