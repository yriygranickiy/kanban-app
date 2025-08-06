package com.kanban.task_service.dto.Account;

import java.util.UUID;

public record AccountResponseDto(
        UUID id_account,
        String email,
        String full_name,
        String url_img
) {
}
