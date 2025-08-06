package com.kanban.task_service.repository;


import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    int countByColumn(Column column);

    List<Task> findByAssigneeId(UUID user_id);
}
