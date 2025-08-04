package com.example.get_away.api_gateway.dto;

import java.util.List;
import java.util.UUID;

public record UserInfo(
        UUID user_id,
        List<String> permission
) {
}
