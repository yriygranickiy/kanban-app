package com.example.auth_service.dto;

import java.util.List;
import java.util.UUID;

public record JwtClaims(
        UUID user_id,
        String email,
        List<String> authorities

) {}
