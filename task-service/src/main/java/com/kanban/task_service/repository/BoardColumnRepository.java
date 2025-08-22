package com.kanban.task_service.repository;

import com.kanban.task_service.model.BoardColumns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumns, UUID> {

    @Query("select max(bc.position) from BoardColumns bc where " +
            "bc.board.id = :boardId")
    Integer findMaxPositionByColumnId(@Param("boardId") UUID boardId);
}
