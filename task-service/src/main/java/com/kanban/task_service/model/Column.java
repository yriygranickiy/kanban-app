package com.kanban.task_service.model;

import jakarta.annotation.Nullable;
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

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @jakarta.persistence.Column(nullable = false)
    private Integer position;

    @jakarta.persistence.Column(name = "task_limit")
    private Integer taskLimit;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at")
    private Instant createdAt;


    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public Integer getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(Integer taskLimit) {
        this.taskLimit = taskLimit;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}