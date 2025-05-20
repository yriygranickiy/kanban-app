package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.BoardCreateRequestDto;
import com.kanban.task_service.dto.BoardResponseDto;
import com.kanban.task_service.model.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public Board toEntity(BoardCreateRequestDto boardDto) {
       return Board.builder()
               .name(boardDto.name())
               .ownerId(boardDto.ownerId())
               .build();
    }

    public BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(board.getId(), board.getName(), board.getOwnerId(), board.getCreatedAt());
    }
}
