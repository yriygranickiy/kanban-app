package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Task.TaskMoveRequestDto;
import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.mapper.TaskMapper;
import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.model.TaskStatus;
import com.kanban.task_service.repository.ColumnRepository;
import com.kanban.task_service.repository.TaskRepository;
import com.kanban.task_service.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ColumnRepository columnRepository;
    private final TaskMapper taskMapper;
    private final TaskPositionService taskPositionService;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ColumnRepository columnRepository,
                           TaskMapper taskMapper,
                           TaskPositionService taskPositionService) {
        this.taskRepository = taskRepository;
        this.columnRepository = columnRepository;
        this.taskMapper = taskMapper;
        this.taskPositionService = taskPositionService;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto, UUID userId) {
        Column column = getColumnId(requestDto.columnId());

        int count = taskRepository.countByColumn(column);

        int position;

        if (requestDto.position() == null) {
            position = taskRepository.findByMaxPositionByColumnId(requestDto.columnId())
                    .map(maxPosition->maxPosition+1)
                    .orElse(1);
        }else {
            taskPositionDown(column.getId(), requestDto.position());
            position = requestDto.position();
        }

        if (column.getTaskLimit() != null && count >= column.getTaskLimit()) {
            throw new IllegalStateException("Task limit for this column has been reached.");
        }

        Task task = Task.builder()
                .title(requestDto.title())
                .description(requestDto.description())
                .due_date(requestDto.due_date())
                .position(position)
                .assigneeId(requestDto.assigneeId() != null ? requestDto.assigneeId() : userId)
                .id_user_creator(userId)
                .status(requestDto.status())
                .priority(requestDto.priority())
                .column(column)
                .build();

        taskRepository.save(task);

        return taskMapper.toDto(task);
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
    public List<TaskResponseDto> getAllTasksByColumnId(UUID columnId) {

       List<Task> listTask = taskRepository.findByColumnId(columnId);
       return listTask.stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getTasksByUserId(UUID user_id) {
         List<Task> list =  taskRepository.findByAssigneeId(user_id)
                .stream()
                .toList();

         return list.stream().map(taskMapper::toDto).collect(Collectors.toList());
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
    public TaskResponseDto moveTask(UUID id_task, TaskMoveRequestDto requestDto) {
        Task task = taskRepository.findById(id_task).orElseThrow(()->
                new EntityNotFoundException("Task not found"));

        Column oldColumn = task.getColumn();

        Column newColumn = columnRepository.findById(requestDto.id_column()).orElseThrow(()->
                new EntityNotFoundException("Column not found"));

        //move in own column
        Integer newPosition = requestDto.position();
        if (oldColumn.getId().equals(newColumn.getId())) {
            if(requestDto.position() == null) {
                taskPositionService.moveTaskPositionInColumn(oldColumn.getId(),task.getPosition(),newPosition);
                task.setPosition(newPosition);
                taskRepository.save(task);
            }
            return taskMapper.toDto(task);
        }


        //chek limit in new column
        int count = taskRepository.countByColumn(task.getColumn());
        if (oldColumn.getTaskLimit() != null && count >= oldColumn.getTaskLimit()) {
            throw new IllegalStateException("Task limit for this column has been reached.");
        }


        //delete old position
        taskPositionService.removeTaskFromOldPosition(oldColumn.getId(),task.getPosition());


        int targetPosition;
        if (requestDto.position() == null) {
            targetPosition = taskRepository.findByMaxPositionByColumnId(newColumn.getId()).orElse(0)+1;
        }else {
            targetPosition = requestDto.position();
            taskPositionService.insertAt(newColumn.getId(),targetPosition);
        }

        TaskStatus status = updateStatus(newColumn.getColumnName());
        task.setStatus(status);
        task.setColumn(newColumn);
        task.setPosition(targetPosition);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Task not found"));

        UUID columnId = task.getColumn().getId();
        int deletedTask = task.getPosition();

        taskRepository.deleteById(id);

        taskPositionUp(columnId, deletedTask);

    }

    private Column getColumnId(UUID id) {
        return columnRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Column not found"));
    }

    private TaskStatus updateStatus(String name_status) {
        return switch (name_status.trim().toUpperCase().replace(" ","_")){
          case "TO_DO" -> TaskStatus.TODO;
          case "IN_PROGRESS" -> TaskStatus.IN_PROGRESS;
          case "DONE" -> TaskStatus.DONE;
            default -> throw new IllegalArgumentException("Unknown status " + name_status);
        };
    }

    private void taskPositionDown(UUID id, Integer position) {
        List<Task> tasks = taskRepository.findByColumnIdAndPositionGreaterOrEqual(id,position);
        for (Task task : tasks) {
            task.setPosition(task.getPosition() + 1);
        }
        taskRepository.saveAll(tasks);
    }

    private void taskPositionUp(UUID id, Integer position) {
        List<Task> tasks = taskRepository.findByColumnIdAndPositionGreater(id,position);
        for (Task task : tasks) {
            task.setPosition(task.getPosition() - 1);
        }
        taskRepository.saveAll(tasks);
    }
}


