package com.kanban.task_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "boards")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Board {

    @Id
    @GeneratedValue()
    private int id;

    private String name;

    @jakarta.persistence.Column(name = "owner_id", nullable = false)
    private String ownerId;

    @CreationTimestamp
    @jakarta.persistence.Column(name="created_at", updatable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Column> columns;

}
