package com.kanban.task_service.controller;

import com.kanban.task_service.dto.Task.TaskPathDto;
import com.kanban.task_service.dto.Task.TaskRequestDto;
import com.kanban.task_service.dto.Task.TaskResponseDto;
import com.kanban.task_service.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/create-task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        return ResponseEntity.ok(taskService.createTask(taskRequestDto));
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<TaskResponseDto>> getAllTask() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PatchMapping("/update-task/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID id,
                                                      @RequestBody TaskPathDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/delete-task/{id}")
    public void deleteTaskById(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
    }
}
