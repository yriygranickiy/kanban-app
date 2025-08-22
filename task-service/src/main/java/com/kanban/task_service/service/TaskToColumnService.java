package com.kanban.task_service.service;

import com.kanban.task_service.dto.Task.TaskMoveRequestDto;
import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;

import java.util.UUID;

public interface TaskToColumnService {

    void createTaskToColumnAndBoard(Task task, Board board);

    void moveTaskToColumnAndBoard(UUID task, TaskMoveRequestDto requestDto);
}
