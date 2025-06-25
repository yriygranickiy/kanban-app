package com.kanban.task_service.repository;

import com.kanban.task_service.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {

    List<Board> findAllByOwnerId(UUID ownerId);

}
