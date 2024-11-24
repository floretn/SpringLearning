package ru.nspk.task7.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import ru.nspk.task7.model.AccountHistoryRecord;
import ru.nspk.task7.model.AccountId;
import ru.nspk.task7.model.EntryId;
import ru.nspk.task7.utils.TimeUtil;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AccountServiceBaseMakeEntryTest {

    @Mock
    private AccountHistoryService accountHistoryService;

    @Mock
    private TimeUtil timeUtil;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private AccountServiceBase accountServiceBase;

    @Test
    void test_throw_if_same_acc() {
        AccountId creditAcc = new AccountId(1);
        AccountId debitAcc = new AccountId(1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> accountServiceBase.makeEntry(debitAcc, creditAcc, 100));
        assertEquals("debit and credit account have the same id = '1'", exception.getMessage());
    }

    @Test
    void test_throw_if_zero_or_negative_sum() {
        AccountId creditAcc = new AccountId(1);
        AccountId debitAcc = new AccountId(2);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> accountServiceBase.makeEntry(debitAcc, creditAcc, 0));
        assertEquals("sum '0' is equals or less then zero", exception.getMessage());

        exception = assertThrows(
                IllegalArgumentException.class, () -> accountServiceBase.makeEntry(debitAcc, creditAcc, -100));
        assertEquals("sum '-100' is equals or less then zero", exception.getMessage());
    }

    @Test
    void test_create_entry() {
        AccountId creditAcc = new AccountId(1);
        AccountId debitAcc = new AccountId(2);

        LocalDateTime now = LocalDateTime.now();
        when(timeUtil.getCurrentTime()).thenReturn(now);

        long sum = 100;
        EntryId entryId = accountServiceBase.makeEntry(debitAcc, creditAcc, sum);

        verify(timeUtil).getCurrentTime();
        verify(accountHistoryService, times(2))
                .add(argThat(record -> checkCreatedRecord(entryId, creditAcc, debitAcc, sum, record)));
    }

    private boolean checkCreatedRecord(
            EntryId entryId, AccountId creaditAcc, AccountId debitAcc, long sum, AccountHistoryRecord record) {
        if (entryId != record.entryId() || timeUtil.getCurrentTime() != record.date()) {
            return false;
        }
        if (creaditAcc == record.accountId()) {
            return record.operationSum() == -sum;
        } else if (debitAcc == record.accountId()) {
            return record.operationSum() == sum;
        }
        return false;
    }
}
