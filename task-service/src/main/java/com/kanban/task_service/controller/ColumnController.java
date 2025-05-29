package com.kanban.task_service.controller;

import com.kanban.task_service.dto.ColumnPatchDto;
import com.kanban.task_service.dto.ColumnRequestDto;
import com.kanban.task_service.dto.ColumnResponseDto;
import com.kanban.task_service.service.ColumnService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/columns")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping("/create-column")
    public ResponseEntity<ColumnResponseDto> addColumn(@RequestBody ColumnRequestDto columnRequestDto) {
        return ResponseEntity.ok(columnService.createBoard(columnRequestDto));
    }

    @GetMapping("/all-columns")
    public ResponseEntity<List<ColumnResponseDto>> getAllColumns(){
        return ResponseEntity.ok(columnService.getAllColumns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColumnResponseDto> getColumnById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(columnService.getColumnById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ColumnResponseDto> updateColumn(@PathVariable("id") UUID id,
                                                          @RequestBody ColumnPatchDto dto){
        return ResponseEntity.ok(columnService.updateColumn(id,dto));
    }
}
