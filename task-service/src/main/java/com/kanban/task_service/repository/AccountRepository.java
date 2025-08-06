package com.kanban.task_service.repository;

import com.kanban.task_service.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, UUID> {

    boolean existsById(UUID id);
}
