package ru.nspk.task9.service.ps;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nspk.task9.dao.model.ps.Account;
import ru.nspk.task9.dao.repo.ps.AccountRepo;

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
