package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.BoardCreateRequestDto;
import com.kanban.task_service.dto.BoardPatchDto;
import com.kanban.task_service.dto.BoardResponseDto;
import com.kanban.task_service.model.Board;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BoardMapper {


    Board toEntity(BoardCreateRequestDto boardCreateRequestDto);

    BoardResponseDto toDto(Board board);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBoard(BoardPatchDto boardDto, @MappingTarget Board entity);
}
