package com.kanban.task_service.repository;

import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ColumnRepository extends JpaRepository<Column, UUID> {

}
