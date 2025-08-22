package com.kanban.task_service.service.impl;

import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.BoardColumns;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.repository.BoardColumnRepository;
import com.kanban.task_service.service.ColumnToBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnToBoardServiceImpl implements ColumnToBoardService {

    private final BoardColumnRepository repository;

    @Override
    public void createColumnToBoard(Column column, Board board) {

        Integer maxPosition = repository.findMaxPositionByColumnId(board.getId());

        BoardColumns boardColumns = BoardColumns.builder()
                .column(column)
                .board(board)
                .position((maxPosition != null ? maxPosition : 0)+1)
                .build();

        repository.save(boardColumns);
    }
}
