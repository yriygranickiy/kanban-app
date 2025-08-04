package com.example.auth_service.dto;

import java.util.List;
import java.util.UUID;

public record JwtClaims(
        String username,
        List<String> authorities,
        UUID user_id
) {}
