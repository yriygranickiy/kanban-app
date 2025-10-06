package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Task.TaskMoveRequestDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.ColumnBoardTasks;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.repository.ColumnBoardTaskRepository;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.service.TaskToColumnService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskToColumnServiceImpl implements TaskToColumnService {

    private final ColumnBoardTaskRepository columnTaskRepository;
    private final ColumnRepository columnRepository;

    @Override
    public void createTaskToColumnAndBoard(Task task, Board board) {

        Column column = columnRepository.findByColumnName("TO DO");

        Integer maxPosition = columnTaskRepository.findMaxPositionByColumnId(column.getId(), board.getId());

        ColumnBoardTasks columnTasks = ColumnBoardTasks.builder()
                .column(column)
                .task(task)
                .board(board)
                .position((maxPosition != null ? maxPosition : 0) + 1).build();

        columnTaskRepository.save(columnTasks);
    }

    @Transactional
    @Override
    public void moveTaskToColumnAndBoard(UUID taskId, TaskMoveRequestDto requestDto) {
        ColumnBoardTasks columnBoardTasks = columnTaskRepository.findByTaskIdAndBoardId(taskId, requestDto.board_id())
                .orElseThrow(() -> new EntityNotFoundException("Column Board Tasks not found"));

        UUID sourceColumnId = columnBoardTasks.getColumn().getId();

        int oldPosition = columnBoardTasks.getPosition();

        columnTaskRepository.shiftPositionsAfterDelete(sourceColumnId, requestDto.board_id(), oldPosition);

        Integer targetIndex = requestDto.position();
        if (targetIndex == null) {
            targetIndex = 1;
        }

        columnTaskRepository.shiftPositionsFrom(requestDto.id_column(), requestDto.board_id(), targetIndex);

        Column targetColumn = columnRepository.findById(requestDto.id_column()).orElseThrow(() -> new EntityNotFoundException("Column not found"));

        columnBoardTasks.setColumn(targetColumn);

        columnBoardTasks.setPosition(targetIndex);

        columnTaskRepository.save(columnBoardTasks);

    }

}
