package com.example.auth_service.dto;

import java.util.List;

public record JwtClaims(
        String username,
        List<String> authorities
) {}
