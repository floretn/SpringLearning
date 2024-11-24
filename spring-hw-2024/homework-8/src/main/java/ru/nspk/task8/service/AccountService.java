package ru.nspk.task8.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nspk.task8.dao.model.Account;
import ru.nspk.task8.dao.repo.AccountRepo;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public Account getAccountById(long accountId) {
        return accountRepo
                .findById(accountId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account record was not found for id %s".formatted(accountId)));
    }

    public void updateAccount(Account account) {
        accountRepo.save(account);
    }

    public Account createEmptyAccount() {
        Account account = new Account(0);
        return accountRepo.save(account);
    }
}
