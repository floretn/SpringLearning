package ru.nspk.task1.service;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.nspk.task1.model.AccountHistoryRecord;
import ru.nspk.task1.model.AccountId;
import ru.nspk.task1.model.EntryId;
import ru.nspk.task1.utils.TimeUtil;

@RequiredArgsConstructor
public class AccountServiceBase implements AccountService {

    private final AccountHistoryService accountHistoryService;
    private final TimeUtil timeUtil;

    @Override
    public EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum) {
        EntryId entryId = new EntryId(generateRandomLongId());
        LocalDateTime now = timeUtil.getCurrentTime();
        AccountHistoryRecord debitRecord = new AccountHistoryRecord(entryId, debitAccount, now, sum);
        AccountHistoryRecord creditAccount = new AccountHistoryRecord(entryId, creditAccountId, now, -sum);
        accountHistoryService.add(debitRecord);
        accountHistoryService.add(creditAccount);
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
        return newEntryId;
    }

    private long generateRandomLongId() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}