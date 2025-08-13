package com.kanban.task_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
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

    @jakarta.persistence.Column(name = "task_limit")
    private Integer taskLimit;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at")
    private Instant createdAt;




    public UUID getId() {
        return id;
    }

    public Integer getTaskLimit() {
        return taskLimit;
    }

    public void setTaskLimit(Integer taskLimit) {
        this.taskLimit = taskLimit;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}