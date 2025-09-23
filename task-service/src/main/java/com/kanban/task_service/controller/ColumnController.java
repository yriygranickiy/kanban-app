package com.kanban.task_service.controller;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.service.ColumnService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PreAuthorize("hasAuthority('CREATE_COLUMN')")
    @PostMapping("/create-column")
    public ResponseEntity<ColumnResponseDto> addColumn(@RequestBody @Valid ColumnRequestDto columnRequestDto) {
        return ResponseEntity.ok(columnService.createColumn(columnRequestDto));
    }

    @PreAuthorize("hasAuthority('READ_COLUMN')")
    @GetMapping("/all-columns")
    public ResponseEntity<List<ColumnResponseDto>> getAllColumns(){
        return ResponseEntity.ok(columnService.getAllColumns());
    }

    @PreAuthorize("hasAuthority('READ_COLUMN')")
    @GetMapping("/column/{id}")
    public ResponseEntity<ColumnResponseDto> getColumnById(@PathVariable("id") UUID id){
        return ResponseEntity.ok(columnService.getColumnById(id));
    }

    @PreAuthorize("hasAuthority('UPDATE_COLUMN')")
    @PatchMapping("/update-column/{id}")
    public ResponseEntity<ColumnResponseDto> updateColumn(@PathVariable("id") UUID id,
                                                          @RequestBody ColumnPatchDto dto){
        return ResponseEntity.ok(columnService.updateColumn(id,dto));
    }

    @PreAuthorize("hasAuthority('DELETE_COLUMN')")
    @DeleteMapping("/delete-column/{id}")
    public void deleteColumnById(@PathVariable("id") UUID id){
        columnService.deleteColumnById(id);
    }
}
