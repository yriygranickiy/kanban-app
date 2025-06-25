package com.kanban.task_service.service;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;

import java.util.List;
import java.util.UUID;

public interface ColumnService {
    ColumnResponseDto createColumn(ColumnRequestDto columnRequestDto);
    List<ColumnResponseDto> getAllColumns();
    ColumnResponseDto getColumnById(UUID id);
    ColumnResponseDto updateColumn(UUID id, ColumnPatchDto columnPatchDto);
    void deleteColumnById(UUID id);
}
