package com.kanban.task_service.service;


import com.kanban.task_service.dto.Board.BoardCreateRequestDto;
import com.kanban.task_service.dto.Board.BoardPatchDto;
import com.kanban.task_service.dto.Board.BoardResponseDto;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    BoardResponseDto createBoard(BoardCreateRequestDto board);
    BoardResponseDto getBoardById(UUID id);
    List<BoardResponseDto> getAllBoards();
    List<BoardResponseDto> getBoardsByOwnerId(UUID ownerId);
    BoardResponseDto updateBoard(UUID boardId, BoardPatchDto boardPatchDto);
    void deleteBoardById(UUID id);
}
