package com.kanban.task_service.service;

import com.kanban.task_service.model.Board;
import com.kanban.task_service.model.Column;

public interface ColumnToBoardService {

    void createColumnToBoard(Column column, Board board);
}
