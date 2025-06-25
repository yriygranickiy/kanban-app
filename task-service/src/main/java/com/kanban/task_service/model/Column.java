package com.kanban.task_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    private Integer position;

    @jakarta.persistence.Column(name = "task_limit")
    private Integer taskLimit;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at")
    private Instant createdAt;


    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks;

}