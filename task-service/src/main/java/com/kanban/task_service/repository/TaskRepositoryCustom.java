package com.kanban.task_service.repository;

import com.kanban.task_service.dto.Task.TaskFilter;
import com.kanban.task_service.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryCustom {

    List<Task> findAllbyFilter(TaskFilter filter);
}
