package com.kanban.task_service.service;

import com.kanban.task_service.dto.TaskRequestDto;
import com.kanban.task_service.dto.TaskResponseDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto getTaskById(UUID id);
    void deleteTaskById(UUID id);

}
