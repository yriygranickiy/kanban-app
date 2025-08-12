package com.kanban.task_service.service.impl;

import com.kanban.task_service.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskPositionService {

    private final TaskRepository taskRepository;

    @Transactional
    public void insertAt(UUID columnId, int position) {
        taskRepository.incrementPositionsGreaterOrEqual(columnId, position);
    }

    @Transactional
    public void removeTaskFromOldPosition(UUID columnId,int position) {
        taskRepository.decrementPositionsGreaterThan(columnId, position);
    }

    @Transactional
    public void moveTaskPositionInColumn(UUID columnId, int oldPosition, int newPosition) {
        if (oldPosition < newPosition) {
            taskRepository.incrementPositionsBetween(columnId,newPosition, oldPosition - 1);
        }else  {
            taskRepository.decrementPositionsBetween(columnId, oldPosition - 1, newPosition);
        }

    }
}
