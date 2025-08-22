package com.kanban.task_service.service;

import com.kanban.task_service.dto.Task.TaskMoveRequestDto;
import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto,UUID userId);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(UUID id);
    List<TaskResponseDto> getTasksByUserId(UUID user_id);
    TaskResponseDto updateTask(UUID id, TaskPathDto dto);
    TaskResponseDto moveTask(UUID id_task, TaskMoveRequestDto requestDto);
    void deleteTaskById(UUID id);

}
