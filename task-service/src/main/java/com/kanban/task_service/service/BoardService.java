package com.kanban.task_service.service;


import com.kanban.task_service.dto.BoardCreateRequestDto;
import com.kanban.task_service.dto.BoardResponseDto;
import com.kanban.task_service.model.Board;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    BoardResponseDto createBoard(BoardCreateRequestDto board);
    BoardResponseDto getBoardById(UUID id);
    List<BoardResponseDto> getAllBoards();
    List<BoardResponseDto> getBoardsByOwnerId(UUID ownerId);
    Board updateBoard(BoardCreateRequestDto board);
    void deleteBoard(UUID id);
}
