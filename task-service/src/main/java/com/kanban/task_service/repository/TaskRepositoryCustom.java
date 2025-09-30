package com.kanban.task_service.repository;

import com.kanban.task_service.dto.Task.TaskFilter;
import com.kanban.task_service.model.Task;

import java.util.List;

public interface TaskRepositoryCustom {
    List<Task> findAll(TaskFilter filter);
}
