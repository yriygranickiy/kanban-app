package com.example.auth_service.dto;

import java.util.List;
import java.util.UUID;

public record UserInfoDTO(
        UUID user_id,
        List<String> permission
) {
}
