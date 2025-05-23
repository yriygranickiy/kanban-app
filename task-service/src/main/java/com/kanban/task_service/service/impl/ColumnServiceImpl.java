package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.ColumnRequestDto;
import com.kanban.task_service.dto.ColumnResponseDto;
import com.kanban.task_service.mapper.ColumnMapper;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.repository.BoardRepository;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.service.ColumnService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final ColumnMapper columnMapper;


    public ColumnServiceImpl(ColumnRepository columnRepository, BoardRepository boardRepository, ColumnMapper columnMapper) {
        this.columnRepository = columnRepository;
        this.boardRepository = boardRepository;
        this.columnMapper = columnMapper;
    }

    @Override
    public ColumnResponseDto createBoard(ColumnRequestDto columnRequestDto) {
        Board board = boardRepository.findById(columnRequestDto.boardId()).orElseThrow(()->
                new RuntimeException("Board not found"));
        Column column = columnMapper.toEntity(columnRequestDto,board);
        columnRepository.save(column);
        return columnMapper.toDto(column);
    }

    @Override
    public List<ColumnResponseDto> getAllColumns() {
        return columnRepository.findAll().stream()
                .map(columnMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ColumnResponseDto getColumnById(UUID id) {
        return columnRepository.findById(id).map(columnMapper::toDto).orElseThrow(()->
                new RuntimeException("Column not found"));
    }

    @Override
    public void deleteColumnById(UUID id) {
        columnRepository.deleteById(id);
    }
}
