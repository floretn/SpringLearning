package ru.nspk.task2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.nspk.task2.model.Account;
import ru.nspk.task2.model.AccountId;
import ru.nspk.task2.service.AccountService;

@SpringBootTest
@ActiveProfiles("prod")
class HomeWork2ProdTest {

    @Value("${account.balance.initial-value:0}")
    private long startBalanceAmount;

    @Autowired
    private AccountService accountService;

    @Test
    void test_allow_negative() {
        Account debit = accountService.createAccount();
        Account credit = accountService.createAccount();

        AccountId debitAccountId = debit.accountId();
        AccountId creditAccountId = credit.accountId();
        long sumOfTransaction = startBalanceAmount + 1000;

        assertThrows(
                IllegalArgumentException.class,
                () -> accountService.makeEntry(debitAccountId, creditAccountId, sumOfTransaction));
    }
}
