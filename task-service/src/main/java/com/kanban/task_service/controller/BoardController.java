package com.kanban.task_service.controller;

import com.kanban.task_service.dto.BoardCreateRequestDto;
import com.kanban.task_service.dto.BoardResponseDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable UUID id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PostMapping("/create-board")
    public ResponseEntity<BoardResponseDto> addBoard(@RequestBody BoardCreateRequestDto boardDto) {
        return new ResponseEntity<>(boardService.createBoard(boardDto), HttpStatus.CREATED);
    }

    @GetMapping("/all-boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }


}
