package com.kanban.task_service.repository;

import com.kanban.task_service.model.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColumnRepository extends JpaRepository<Column, UUID> {
}
