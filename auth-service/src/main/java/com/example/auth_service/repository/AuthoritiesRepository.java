package com.example.auth_service.repository;

import com.example.auth_service.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, UUID> {
    Optional<Authorities> findByName(String name);
    Optional<Authorities> findById(UUID id);
}
