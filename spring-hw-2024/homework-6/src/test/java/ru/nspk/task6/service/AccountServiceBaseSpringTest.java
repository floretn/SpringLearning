package ru.nspk.task6.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nspk.task6.model.AccountId;
import ru.nspk.task6.model.EntryId;

@SpringBootTest
class AccountServiceBaseSpringTest {

    @Autowired
    private AccountHistoryService accountHistoryService;

    @Autowired
    private AccountService accountService;

    @Test
    void test_initialization() {
        assertNotNull(accountHistoryService);
        assertNotNull(accountService);
    }

    @Test
    void test_create_entry() {
        AccountId credit = new AccountId(101);
        AccountId debit = new AccountId(102);

        accountService.makeEntry(debit, credit, 100);

        assertEquals(
                -100, accountHistoryService.getBalance(credit, LocalDate.now()).value());
        assertEquals(
                100, accountHistoryService.getBalance(debit, LocalDate.now()).value());
    }

    @Test
    void test_revert_entry() {
        AccountId credit = new AccountId(201);
        AccountId debit = new AccountId(202);

        EntryId entryId = accountService.makeEntry(debit, credit, 100);
        accountService.makeReverseEntry(entryId);

        assertEquals(
                0, accountHistoryService.getBalance(credit, LocalDate.now()).value());
        assertEquals(0, accountHistoryService.getBalance(debit, LocalDate.now()).value());
    }
}
