package ru.nspk.task6.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import ru.nspk.task6.model.AccountHistoryRecord;
import ru.nspk.task6.model.AccountId;
import ru.nspk.task6.model.EntryId;
import ru.nspk.task6.utils.TimeUtil;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AccountHistoryServiceInMemoryGetBalanceTest {

    @Mock
    private TimeUtil timeUtil;

    @InjectMocks
    private AccountHistoryServiceInMemory historyServiceInMemory;

    private final LocalDateTime nowTime = LocalDateTime.now();
    private final LocalDate nowDate = nowTime.toLocalDate();

    @Test
    void throws_for_empty_history() {
        AccountId accountId = new AccountId(100);
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> historyServiceInMemory.getBalance(accountId, nowDate));
        assertEquals("Account with id '100' was not found", ex.getMessage());
    }

    @Test
    void throws_for_future_data() {
        AccountId accountId = new AccountId(100);
        fillHistoryForAccountId(accountId);
        LocalDate tommorow = nowDate.plusDays(1);
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> historyServiceInMemory.getBalance(accountId, tommorow));
        assertEquals("Balance request for future data", ex.getMessage());
    }

    @Test
    void test_history_calculating() {
        AccountId accountId = new AccountId(100);
        fillHistoryForAccountId(accountId);

        assertEquals(
                0,
                historyServiceInMemory
                        .getBalance(accountId, nowDate.minusDays(4))
                        .value());
        assertEquals(
                500,
                historyServiceInMemory
                        .getBalance(accountId, nowDate.minusDays(3))
                        .value());
        assertEquals(
                -500,
                historyServiceInMemory
                        .getBalance(accountId, nowDate.minusDays(2))
                        .value());
        assertEquals(
                1000,
                historyServiceInMemory
                        .getBalance(accountId, nowDate.minusDays(1))
                        .value());
        assertEquals(2700, historyServiceInMemory.getBalance(accountId, nowDate).value());
    }

    private void fillHistoryForAccountId(AccountId accountId) {

        when(timeUtil.getCurrentDate()).thenReturn(nowDate.minusDays(3));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(3), 1000));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(3), -500));

        when(timeUtil.getCurrentDate()).thenReturn(nowDate.minusDays(2));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(2), -1000));

        when(timeUtil.getCurrentDate()).thenReturn(nowDate.minusDays(1));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(1), 5000));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(1), -2000));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(1), -1000));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime.minusDays(1), -500));

        when(timeUtil.getCurrentDate()).thenReturn(nowDate);
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime, 1000));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime, -200));
        historyServiceInMemory.add(new AccountHistoryRecord(new EntryId(1), accountId, nowTime, 900));
    }
}
