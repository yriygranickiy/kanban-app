package com.kanban.task_service.service.impl;

import com.kanban.task_service.dto.Task.*;
import com.kanban.task_service.mapper.TaskMapper;
import com.kanban.task_service.model.*;
import com.kanban.task_service.repository.BoardRepository;
import com.kanban.task_service.repository.TaskRepository;
import com.kanban.task_service.service.AccountsService;
import com.kanban.task_service.service.TaskService;
import com.kanban.task_service.service.TaskToColumnService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    //TODO: сделать сортировку по позиции

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TaskToColumnService taskToColumnService;
    private final AccountsService accountsService;
    private final BoardRepository boardRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           BoardRepository boardRepository,
                           TaskMapper taskMapper,
                           AccountsService accountsService,
                           TaskToColumnService taskToColumnService) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.accountsService = accountsService;
        this.taskToColumnService = taskToColumnService;
        this.boardRepository = boardRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto requestDto, UUID userId) {

        Board board = getBoardId(requestDto.boardId());

        Accounts accounts = accountsService.getAccountById(userId);

        Task task = Task.builder()
                .title(requestDto.title())
                .description(requestDto.description())
                .due_date(requestDto.due_date())
                .assigneeId(requestDto.assigneeId() != null ? requestDto.assigneeId() : accounts.getId())
                .id_user_creator(accounts.getId())
                .status(requestDto.status())
                .priority(requestDto.priority())
                .build();

        taskRepository.save(task);

        taskToColumnService.createTaskToColumnAndBoard(task,board);

        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getAllTasksByFilter(TaskFilter taskFilter) {
        return taskRepository.findAllbyFilter(taskFilter).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto getTaskById(UUID id) {
        return taskRepository.findById(id).map(taskMapper::toDto).orElseThrow(()->
                new EntityNotFoundException("Task not found"));
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

        taskMapper.updateTask(dto,task);

        return taskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto moveTask(UUID id_task, TaskMoveRequestDto requestDto) {

        taskToColumnService.moveTaskToColumnAndBoard(id_task,requestDto);

        Task task = taskRepository.findById(id_task).orElseThrow(()->
                new EntityNotFoundException("Task not found"));

        return taskMapper.toDto(task);
    }

    @Override
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }

    private Board getBoardId(UUID id) {
        return boardRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Board not found"));
    }
 }


