package com.kanban.task_service.controller;

import com.kanban.task_service.dto.Board.BoardCreateRequestDto;
import com.kanban.task_service.dto.Board.BoardPatchDto;
import com.kanban.task_service.dto.Board.BoardResponseDto;
import com.kanban.task_service.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable UUID id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PreAuthorize("hasAuthority('CREATE_BOARD')")
    @PostMapping("/create-board")
    public ResponseEntity<BoardResponseDto> addBoard(@RequestBody BoardCreateRequestDto boardDto) {
        return new ResponseEntity<>(boardService.createBoard(boardDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('READ_BOARD')")
    @GetMapping("/all-boards")
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @PreAuthorize("hasAuthority('UPDATE_BOARD')")
    @PatchMapping("/update-board/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable UUID id,
                                                        @RequestBody BoardPatchDto dto){
        return ResponseEntity.ok(boardService.updateBoard(id, dto));
    }
    @PreAuthorize("hasAuthority('DELETE_BOARD')")
    @DeleteMapping("/delete-board/{id}")
    public void deleteBoard(@PathVariable UUID id) {
        boardService.deleteBoardById(id);
    }

}
