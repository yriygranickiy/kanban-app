package com.kanban.task_service.service;

import com.kanban.task_service.dto.Account.AccountRequestDto;
import com.kanban.task_service.model.Accounts;

import java.util.UUID;

public interface AccountsService {

    void existAccount(UUID id,String email);
}
