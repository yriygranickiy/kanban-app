package com.kanban.task_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@RequiredArgsConstructor
@Entity
@Table(name = "tasks")
@Builder
public class Tasks {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id", nullable = false)
    private Column column;

    @jakarta.persistence.Column(name = "assignee_id")
    private UUID assigneeId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDate dueDate;

    @CreationTimestamp
    @jakarta.persistence.Column(name = "created_at", nullable = false)
    private Instant createdAt;
}
