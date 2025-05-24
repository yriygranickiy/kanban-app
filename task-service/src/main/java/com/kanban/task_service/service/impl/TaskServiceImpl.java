package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.TaskRequestDto;
import com.kanban.task_service.dto.TaskResponseDto;
import com.kanban.task_service.mapper.TaskMapper;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.repository.TaskRepository;
import com.kanban.task_service.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ColumnRepository columnRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository,ColumnRepository columnRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.columnRepository = columnRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Column column = columnRepository.findById(taskRequestDto.columnId()).orElseThrow(()->
                new RuntimeException("Column not found"));
        return taskMapper.toDto(taskRepository.save(taskMapper.toEntity(taskRequestDto, column)));
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto getTaskById(UUID id) {
        return taskRepository.findById(id).map(taskMapper::toDto).orElseThrow(()->
                new RuntimeException("Task not found"));
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }
}
