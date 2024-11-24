package ru.nspk.task3.service;

import java.time.LocalDate;
import java.util.List;
import ru.nspk.task3.model.AccountBalance;
import ru.nspk.task3.model.AccountHistoryRecord;
import ru.nspk.task3.model.AccountId;
import ru.nspk.task3.model.EntryId;

public interface AccountHistoryService {

    void add(AccountHistoryRecord historyRecord);

    AccountBalance getBalance(AccountId accountId, LocalDate data);

    List<AccountHistoryRecord> getHistory(AccountId accountId, LocalDate from, LocalDate to);

    List<AccountHistoryRecord> getHistoryByEntryId(EntryId entryId);
}
