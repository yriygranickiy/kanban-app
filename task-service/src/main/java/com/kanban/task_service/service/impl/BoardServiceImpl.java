package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Board.BoardRequestDto;
import com.kanban.task_service.dto.Board.BoardPatchDto;
import com.kanban.task_service.dto.Board.BoardResponseDto;
import com.kanban.task_service.mapper.BoardMapper;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.repository.BoardRepository;
import com.kanban.task_service.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardDto, UUID userId) {
         Board board = Board.builder()
                 .id(userId)
                 .name(boardDto.name())
                 .build();

         boardRepository.save(board);

         return boardMapper.toDto(board);
    }

    @Override
    public BoardResponseDto getBoardById(UUID id) {
        return boardRepository.findById(id).map(boardMapper::toDto).orElseThrow(()->
                new RuntimeException("Board not found with id: "+ id));
    }

    @Override
    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(boardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> getBoardsByOwnerId(UUID ownerId) {
        return boardRepository.findAllByOwnerId(ownerId).stream()
                .map(boardMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto updateBoard(UUID boardId, BoardPatchDto boardPatchDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(()->
                new RuntimeException("Board not found"));

        boardMapper.updateBoard(boardPatchDto, board);

        boardRepository.save(board);

        return boardMapper.toDto(board);
    }


    @Override
    public void deleteBoardById(UUID id) {
        boardRepository.deleteById(id);
    }
}
