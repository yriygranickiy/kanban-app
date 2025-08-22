package com.kanban.task_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "columns")
public class Column {
    @Id
    @GeneratedValue
    private UUID id;

    @jakarta.persistence.Column(name = "name")
    private String columnName;

    @jakarta.persistence.Column(name = "task_limit")
    private Integer taskLimit;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BoardColumns> boardColumns = new ArrayList<>();

    @OneToMany(mappedBy = "column",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColumnBoardTasks> columnTasks = new ArrayList<>();



    public UUID getId() {
        return id;
    }

    public Integer getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(Integer taskLimit) {
        this.taskLimit = taskLimit;
    }


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<BoardColumns> getBoardColumns() {
        return boardColumns;
    }

    public void setBoardColumns(List<BoardColumns> boardColumns) {
        this.boardColumns = boardColumns;
    }

    public List<ColumnBoardTasks> getColumnTasks() {
        return columnTasks;
    }

    public void setColumnTasks(List<ColumnBoardTasks> columnTasks) {
        this.columnTasks = columnTasks;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}