package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Column.ColumnPatchDto;
import com.kanban.task_service.dto.Column.ColumnRequestDto;
import com.kanban.task_service.dto.Column.ColumnResponseDto;
import com.kanban.task_service.mapper.ColumnMapper;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.repository.BoardRepository;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.service.ColumnService;
import com.kanban.task_service.service.ColumnToBoardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ColumnServiceImpl implements ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final ColumnMapper columnMapper;
    private final ColumnToBoardService columnToBoardService;


    public ColumnServiceImpl(ColumnRepository columnRepository,
                             BoardRepository boardRepository,
                             ColumnMapper columnMapper,
                             ColumnToBoardService columnToBoardService) {
        this.columnRepository = columnRepository;
        this.boardRepository = boardRepository;
        this.columnMapper = columnMapper;
        this.columnToBoardService = columnToBoardService;
    }

    @Override
    public ColumnResponseDto createColumn(ColumnRequestDto request) {

        Board board = boardRepository.findBoardById(request.boardId());


        Column column = Column.builder()
                .columnName(request.columnName())
                .taskLimit(request.task_limit())
                .build();
        columnRepository.save(column);

        columnToBoardService.createColumnToBoard(column, board);

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
    public ColumnResponseDto updateColumn(UUID id, ColumnPatchDto dto) {
        Column column = columnRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Column not found"));

        columnMapper.updateColumn(dto,column);

        return columnMapper.toDto(columnRepository.save(column));
    }

    @Override
    public void deleteColumnById(UUID id) {
        columnRepository.deleteById(id);
    }


}
