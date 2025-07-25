package com.kanban.task_service.service;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(UUID id);
    TaskResponseDto updateTask(UUID id, TaskPathDto dto);
    void deleteTaskById(UUID id);

}
