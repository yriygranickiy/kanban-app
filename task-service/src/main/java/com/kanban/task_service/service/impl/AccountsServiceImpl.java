package com.kanban.task_service.service.impl;


import com.kanban.task_service.model.Accounts;
import com.kanban.task_service.repository.AccountRepository;
import com.kanban.task_service.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

    @Override
    public void existAccount(UUID id, String email) {
        accountRepository.findById(id)
                .orElseGet(()->{
            Accounts account = Accounts.builder()
                    .id(id)
                    .email(email)
                    .build();
            return accountRepository.save(account);
        });
    }
}
