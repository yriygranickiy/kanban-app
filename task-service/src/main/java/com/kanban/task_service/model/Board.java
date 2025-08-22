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
@Data
@Builder
@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue()
    private UUID id;

    private String name;

    @jakarta.persistence.Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @CreationTimestamp
    @jakarta.persistence.Column(name="created_at", updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardColumns> boardColumns = new ArrayList<>();

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColumnBoardTasks> columnBoardTasks = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
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

    public List<ColumnBoardTasks> getColumnBoardTasks() {
        return columnBoardTasks;
    }

    public void setColumnBoardTasks(List<ColumnBoardTasks> columnBoardTasks) {
        this.columnBoardTasks = columnBoardTasks;
    }
}
