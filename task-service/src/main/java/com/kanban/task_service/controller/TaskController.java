package com.kanban.task_service.controller;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todo")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAuthority('CREATE_TASK')")
    @PostMapping("/create-task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskService.createTask(taskRequestDto));
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

    @PreAuthorize("hasAnyAuthority('READ_TASK')")
    @GetMapping("/task/my")
    public ResponseEntity<List<TaskResponseDto>> getAllTasksByUserId(Authentication authentication) {
            UUID user_id = (UUID) authentication.getPrincipal();
            return ResponseEntity.ok(taskService.getTasksByUserId(user_id));
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
