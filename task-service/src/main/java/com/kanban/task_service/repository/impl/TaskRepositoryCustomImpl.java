package com.kanban.task_service.repository.impl;

import com.kanban.task_service.dto.Task.TaskFilter;
import com.kanban.task_service.model.QColumnBoardTasks;
import com.kanban.task_service.model.QTask;
import com.kanban.task_service.model.Task;
import com.kanban.task_service.repository.TaskRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryCustomImpl implements TaskRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Task> findAll(TaskFilter filter) {

        QTask task = QTask.task;
        QColumnBoardTasks columnBoardTasks = QColumnBoardTasks.columnBoardTasks;

        JPAQuery<Task> query = queryFactory.select(task)
                .from(columnBoardTasks)
                .join(columnBoardTasks.task, task);

        if (filter.boardId() != null) {
            query.where(columnBoardTasks.board.id.eq(filter.boardId()));
        }

        if (filter.columnId() != null) {
            query.where(columnBoardTasks.column.id.eq(filter.columnId()));
        }

        if (filter.assignedId() != null) {
            query.where(task.assigneeId.eq(filter.assignedId()));
        }

        if (filter.status() != null) {
            query.where(task.status.eq(filter.status()));
        }

        if (filter.createdAfter() != null) {
            query.where(task.createdAt.after(filter.createdAfter()));
        }

        if (filter.createdBefore() != null) {
            query.where(task.createdAt.before(filter.createdBefore()));
        }

        return query.fetch();
    }
}
