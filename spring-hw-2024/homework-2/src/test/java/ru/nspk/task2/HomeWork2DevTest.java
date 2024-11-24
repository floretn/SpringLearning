package ru.nspk.task2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.nspk.task2.model.Account;
import ru.nspk.task2.model.AccountBalance;
import ru.nspk.task2.service.AccountHistoryService;
import ru.nspk.task2.service.AccountService;
import ru.nspk.task2.utils.TimeUtil;

@SpringBootTest
@ActiveProfiles("dev")
class HomeWork2DevTest {

    @Value("${account.balance.initial-value:0}")
    private long startBalanceAmount;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private TimeUtil timeUtil;

    @Test
    void test_initial_sum() {
        Account account = accountService.createAccount();
        assertEquals(startBalanceAmount, account.balance().value());
        AccountBalance balance = accountHistoryService.getBalance(account.accountId(), timeUtil.getCurrentDate());
        assertEquals(startBalanceAmount, balance.value());
    }

    @Test
    void test_allow_negative() {
        Account debit = accountService.createAccount();
        Account credit = accountService.createAccount();

        assertDoesNotThrow(() -> accountService.makeEntry(debit.accountId(), credit.accountId(), 1000));
        assertDoesNotThrow(() -> accountService.makeEntry(debit.accountId(), credit.accountId(), startBalanceAmount));
    }
}
