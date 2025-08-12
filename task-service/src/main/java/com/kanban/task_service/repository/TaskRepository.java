package com.kanban.task_service.repository;


import com.kanban.task_service.model.Column;
import com.kanban.task_service.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT max(t.position) from Task t where t.column.id = :columnId")
    Optional<Integer> findByMaxPositionByColumnId(@Param("columnId") UUID columnId);

    @Query("SELECT t FROM Task t WHERE t.column.id = :columnId AND t.position >= :fromPosition" +
            " ORDER BY t.position ASC")
    List<Task> findByColumnIdAndPositionGreaterOrEqual(@Param("columnId") UUID columnId,
                                                       @Param("fromPosition") int fromPosition);

    @Query("SELECT t FROM Task t WHERE t.column.id = :columnId AND t.position > :fromPosition" +
            " ORDER BY t.position ASC")
    List<Task> findByColumnIdAndPositionGreater(@Param("columnId") UUID columnId,
                                                @Param("fromPosition") int fromPosition);


    @Modifying
    @Query("update Task t set t.position = t.position + 1 where t.column.id = :columnId " +
            "and t.position >= :fromPosition")
    void incrementPositionsGreaterOrEqual(@Param("columnId") UUID columnId,
                                          @Param("fromPosition") int fromPosition);

    @Modifying
    @Query("update Task t set t.position = t.position - 1 where t.column.id = :columnId and " +
            "t.position > :fromPosition")
    void decrementPositionsGreaterThan(@Param("columnId") UUID columnId,
                                       @Param("fromPosition")  int fromPosition);

    @Modifying
    @Query("update Task t set t.position = t.position + 1 where t.column.id = :columnId and t.position " +
            "between :start and :end")
    void incrementPositionsBetween(@Param("columnId") UUID columnId,
                                   @Param("start") int start,
                                   @Param("end") int end);

    @Modifying
    @Query("update Task t set t.position = t.position - 1 where t.column.id = :columnId and t.position between " +
            ":start and :end")
    void decrementPositionsBetween(@Param("columnId") UUID columnId,
                                   @Param("start") int start,
                                   @Param("end") int end);

    Integer countByColumn(Column column);

    List<Task> findByAssigneeId(UUID user_id);

    List<Task> findByColumnId(UUID columnId);


}
