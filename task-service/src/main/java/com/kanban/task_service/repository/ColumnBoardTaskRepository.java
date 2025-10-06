package com.kanban.task_service.repository;

import com.kanban.task_service.model.ColumnBoardTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ColumnBoardTaskRepository extends JpaRepository<ColumnBoardTasks, UUID> {

    @Query("select max(ct.position) from ColumnBoardTasks ct where " +
            "ct.column.id = :columnId and ct.board.id = :boardId")
    Integer findMaxPositionByColumnId(@Param("columnId") UUID columnId,
                                      @Param("boardId") UUID boardId);

    Optional<ColumnBoardTasks> findByTaskIdAndBoardId(UUID columnId, UUID boardId);

    @Modifying
    @Query("update ColumnBoardTasks ct " +
            "set ct.position = ct.position - 1 " +
            "where ct.column.id = :columnId and ct.board.id = :boardId and ct.position > :position")
    void shiftPositionsAfterDelete(@Param("columnId") UUID columnId,
                                   @Param("boardId") UUID boardId,
                                   @Param("position") int position);

    @Modifying
    @Query("update ColumnBoardTasks ct " +
            "set ct.position = ct.position + 1 " +
            "where ct.column.id = :columnId and ct.board.id = :boardId and ct.position >= :fromPosition")
    void shiftPositionsFrom(@Param("columnId") UUID columnId,
                            @Param("boardId") UUID boardId,
                            @Param("fromPosition") int fromPosition);

}
