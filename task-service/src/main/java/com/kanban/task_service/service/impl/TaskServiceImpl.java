package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.mapper.TaskMapper;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.repository.TaskRepository;
import com.kanban.task_service.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
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

        int count = taskRepository.countByColumn(column);

        if (column.getTaskLimit() != null && count >= column.getTaskLimit()) {
            throw new IllegalStateException("Task limit for this column has been reached.");
        }
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
                new EntityNotFoundException("Task not found"));
    }

    @Override
    public TaskResponseDto updateTask(UUID id, TaskPathDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Task not found"));

        if (dto.columnId() != null && !task.getColumn().getId().equals(dto.columnId())) {
            Column newColumn = columnRepository.findById(dto.columnId()).orElseThrow(
                    () -> new EntityNotFoundException("Column not found")
            );
            int count = taskRepository.countByColumn(newColumn);
            if(newColumn.getTaskLimit() != null && count >= newColumn.getTaskLimit()) {
                throw new IllegalStateException("Task limit for this column has been reached.");
            }
            task.setColumn(newColumn);
        }
        taskMapper.updateTask(dto,task);
        return taskMapper.toDto(taskRepository.save(task));
    }
    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }
}
