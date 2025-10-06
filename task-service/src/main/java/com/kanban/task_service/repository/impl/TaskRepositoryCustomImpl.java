package com.kanban.task_service.repository.impl;

import com.kanban.task_service.dto.Task.TaskFilter;
import com.kanban.task_service.model.QColumnBoardTasks;
import com.kanban.task_service.model.QTask;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.repository.TaskRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Task> findAllbyFilter(TaskFilter filter) {
        QTask task = QTask.task;
        QColumnBoardTasks columnBoardTasks = QColumnBoardTasks.columnBoardTasks;

        BooleanBuilder builder = new BooleanBuilder();

        if (filter.boardId() != null) {
            builder.and(columnBoardTasks.board.id.eq(filter.boardId()));
        }
        if (filter.columnId() != null) {
            builder.and(columnBoardTasks.column.id.eq(filter.columnId()));
        }
        if (filter.assignedId() != null) {
            builder.and(task.assigneeId.eq(filter.assignedId()));
        }
        if (filter.status() != null) {
            builder.and(task.status.eq(filter.status()));
        }
        if (filter.createdAfter() != null) {
            builder.and(task.createdAt.after(filter.createdAfter()));
        }
        if (filter.createdBefore() != null) {
            builder.and(task.createdAt.before(filter.createdBefore()));
        }
        return queryFactory.selectFrom(task)
                .join(task.columnBoardTasks, columnBoardTasks)
                .fetchJoin()
                .where(builder)
                .fetch();
    }
}
