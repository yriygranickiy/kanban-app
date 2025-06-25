package com.kanban.task_service.service;

import com.kanban.task_service.dto.ColumnPatchDto;
import com.kanban.task_service.dto.ColumnRequestDto;
import com.kanban.task_service.dto.ColumnResponseDto;

import java.util.List;
import java.util.UUID;

public interface ColumnService {
    ColumnResponseDto createBoard(ColumnRequestDto columnRequestDto);
    List<ColumnResponseDto> getAllColumns();
    ColumnResponseDto getColumnById(UUID id);
    ColumnResponseDto updateColumn(UUID id, ColumnPatchDto columnPatchDto);
    void deleteColumnById(UUID id);
}
