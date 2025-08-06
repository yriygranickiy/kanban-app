package com.kanban.task_service.dto.Account;

import java.util.UUID;

public record AccountRequestDto(
        UUID id_account,
        String email
) {
}
