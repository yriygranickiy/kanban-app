package com.example.auth_service.dto;

import java.sql.Timestamp;

public record UserDTO(
        String username,
        String email,
        Timestamp createdAt) {
}
