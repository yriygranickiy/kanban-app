package com.kanban.task_service.mapper;

import com.kanban.task_service.dto.Board.BoardCreateRequestDto;
import com.kanban.task_service.dto.Board.BoardPatchDto;
import com.kanban.task_service.dto.Board.BoardResponseDto;
import com.kanban.task_service.model.Board;
import java.time.Instant;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-29T14:46:53+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public Board toEntity(BoardCreateRequestDto boardCreateRequestDto) {
        if ( boardCreateRequestDto == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.name( boardCreateRequestDto.name() );
        board.ownerId( boardCreateRequestDto.ownerId() );

        return board.build();
    }

    @Override
    public BoardResponseDto toDto(Board board) {
        if ( board == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        UUID ownerId = null;
        Instant createdAt = null;

        id = board.getId();
        name = board.getName();
        ownerId = board.getOwnerId();
        createdAt = board.getCreatedAt();

        BoardResponseDto boardResponseDto = new BoardResponseDto( id, name, ownerId, createdAt );

        return boardResponseDto;
    }

    @Override
    public void updateBoard(BoardPatchDto boardDto, Board entity) {
        if ( boardDto == null ) {
            return;
        }

        if ( boardDto.name() != null ) {
            entity.setName( boardDto.name() );
        }
        if ( boardDto.ownerId() != null ) {
            entity.setOwnerId( boardDto.ownerId() );
        }
    }
}
