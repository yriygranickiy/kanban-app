package com.kanban.task_service.controller;

import com.kanban.task_service.dto.Task.*;
import com.kanban.task_service.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class TaskController {

    //TODO: сделать exception для эндпоинтов на которых нет прав для того что бы понимать что нет прав на запрос этой урлы

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('CREATE_TASK')")
    @PostMapping("/create-task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody @Valid TaskRequestDto taskRequestDto,
                                                      @AuthenticationPrincipal UUID userId) {
        return ResponseEntity.ok(taskService.createTask(taskRequestDto, userId));
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/task/filter")
    public ResponseEntity<List<TaskResponseDto>> findAllByFilter(@ModelAttribute TaskFilter taskFilter) {
        return ResponseEntity.ok(taskService.getAllTasksByFilter(taskFilter));
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/all-tasks")
    public ResponseEntity<List<TaskResponseDto>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PreAuthorize("hasAuthority('READ_TASK')")
    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/task/{task_id}/move")
    public ResponseEntity<TaskResponseDto> moveTask(@PathVariable UUID task_id,
                                                    @RequestBody TaskMoveRequestDto taskMoveRequestDto) {
        return ResponseEntity.ok(taskService.moveTask(task_id, taskMoveRequestDto));
    }


    @PreAuthorize("hasAnyAuthority('READ_TASK')")
    @GetMapping("/task/my")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByUserId(@AuthenticationPrincipal UUID id) {
            return ResponseEntity.ok(taskService.getTasksByUserId(id));
    }

    @PreAuthorize("hasAuthority('UPDATE_TASK')")
    @PatchMapping("/update-task/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID id,
                                                      @RequestBody TaskPathDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @PreAuthorize("hasAuthority('DELETE_TASK')")
    @DeleteMapping("/delete-task/{id}")
    public void deleteTaskById(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
    }
}
