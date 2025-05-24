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
@Entity
@Table(name = "columns")
@Builder
public class Column {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private Integer position;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at")
    private Instant createdAt;


    @OneToMany(mappedBy = "columnId", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Task> tasks;

}