package ru.nspk.task5.service;

import static ru.nspk.task5.listener.EntryEventType.CREATE;
import static ru.nspk.task5.listener.EntryEventType.REVERSE;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import ru.nspk.task5.listener.EntryEvent;
import ru.nspk.task5.model.AccountHistoryRecord;
import ru.nspk.task5.model.AccountId;
import ru.nspk.task5.model.EntryId;
import ru.nspk.task5.utils.TimeUtil;

@RequiredArgsConstructor
public class AccountServiceBase implements AccountService {

    private final AccountHistoryService accountHistoryService;
    private final TimeUtil timeUtil;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum) {
        if (debitAccount.id() == creditAccountId.id()) {
            throw new IllegalArgumentException(
                    String.format("debit and credit account have the same id = '%d'", creditAccountId.id()));
        }
        if (sum <= 0) {
            throw new IllegalArgumentException(String.format("sum '%d' is equals or less then zero", sum));
        }

        EntryId entryId = new EntryId(generateRandomLongId());
        LocalDateTime now = timeUtil.getCurrentTime();

        AccountHistoryRecord debitRecord = new AccountHistoryRecord(entryId, debitAccount, now, sum);
        AccountHistoryRecord creditAccount = new AccountHistoryRecord(entryId, creditAccountId, now, -sum);

        accountHistoryService.add(debitRecord);
        accountHistoryService.add(creditAccount);

        // Just 2 examples
        applicationEventPublisher.publishEvent(entryId);
        applicationEventPublisher.publishEvent(new EntryEvent(entryId, CREATE));

        return entryId;
    }

    @Override
    public EntryId makeReverseEntry(EntryId originalEntryId) {
        EntryId newEntryId = new EntryId(generateRandomLongId());
        LocalDateTime now = timeUtil.getCurrentTime();
        accountHistoryService
                .getHistoryByEntryId(originalEntryId)
                .forEach(accountHistoryRecord -> accountHistoryService.add(new AccountHistoryRecord(
                        newEntryId, accountHistoryRecord.accountId(), now, -accountHistoryRecord.operationSum())));

        applicationEventPublisher.publishEvent(new EntryEvent(newEntryId, REVERSE));

        return newEntryId;
    }

    private long generateRandomLongId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
