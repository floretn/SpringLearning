package ru.nspk.task10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.nspk.task10.dao.model.Account;
import ru.nspk.task10.dao.repo.AccountRepo;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public Mono<Account> getAccountById(long accountId) {
        return accountRepo.findById(accountId);
    }

    public Mono<Account> updateAccount(Account account) {
        return accountRepo.save(account);
    }

    public Mono<Account> createEmptyAccount() {
        Account account = new Account(0);
        return accountRepo.save(account);
    }
}
