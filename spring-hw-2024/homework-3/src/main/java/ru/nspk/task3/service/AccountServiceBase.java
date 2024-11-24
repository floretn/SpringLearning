package ru.nspk.task3.service;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import ru.nspk.task3.config.AccountProperties;
import ru.nspk.task3.logging.Loggable;
import ru.nspk.task3.model.Account;
import ru.nspk.task3.model.AccountBalance;
import ru.nspk.task3.model.AccountHistoryRecord;
import ru.nspk.task3.model.AccountId;
import ru.nspk.task3.model.EntryId;
import ru.nspk.task3.utils.TimeUtil;

@RequiredArgsConstructor
public class AccountServiceBase implements AccountService {

    private final AccountHistoryService accountHistoryService;
    private final TimeUtil timeUtil;

    private final AccountProperties accountProperties;

    @Loggable
    @Override
    public EntryId makeEntry(AccountId debitAccount, AccountId creditAccountId, long sum) {
        AccountBalance creditBalance = accountHistoryService.getBalance(creditAccountId, timeUtil.getCurrentDate());
        if (creditBalance.value() < sum && !accountProperties.allowNegative()) {
            throw new IllegalArgumentException("There are not enough funds on the credit balance!");
        }

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

    @Override
    public Account createAccount() {
        EntryId createAccountEntryId = new EntryId(generateRandomLongId());
        AccountId newAccountId = new AccountId(generateRandomLongId());

        LocalDateTime now = timeUtil.getCurrentTime();
        accountHistoryService.add(
                new AccountHistoryRecord(createAccountEntryId, newAccountId, now, accountProperties.initialValue()));

        AccountBalance newAccountBalance = new AccountBalance(accountProperties.initialValue());
        return new Account(newAccountId, newAccountBalance);
    }

    private long generateRandomLongId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
