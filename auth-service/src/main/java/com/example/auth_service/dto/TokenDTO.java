package com.example.auth_service.dto;

import lombok.Builder;

@Builder
public record TokenDTO(
        String auth_token
) {
}
