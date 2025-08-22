package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.model.Column;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T14:57:29+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ColumnMapperImpl implements ColumnMapper {

    @Override
    public ColumnResponseDto toDto(Column column) {
        if ( column == null ) {
            return null;
        }

        UUID id = null;
        String columnName = null;
        Integer taskLimit = null;
        Instant createdAt = null;

        id = column.getId();
        columnName = column.getColumnName();
        taskLimit = column.getTaskLimit();
        createdAt = column.getCreatedAt();

        ColumnResponseDto columnResponseDto = new ColumnResponseDto( id, columnName, taskLimit, createdAt );

        return columnResponseDto;
    }

    @Override
    public void updateColumn(ColumnPatchDto dto, Column column) {
        if ( dto == null ) {
            return;
        }

        if ( dto.taskLimit() != null ) {
            column.setTaskLimit( dto.taskLimit() );
        }
    }
}
