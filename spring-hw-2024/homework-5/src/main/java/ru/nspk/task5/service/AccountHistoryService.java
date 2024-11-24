package ru.nspk.task5.service;

import java.time.LocalDate;
import java.util.List;
import ru.nspk.task5.model.AccountBalance;
import ru.nspk.task5.model.AccountHistoryRecord;
import ru.nspk.task5.model.AccountId;
import ru.nspk.task5.model.EntryId;

public interface AccountHistoryService {

    void add(AccountHistoryRecord historyRecord);

    AccountBalance getBalance(AccountId accountId, LocalDate data);

    List<AccountHistoryRecord> getHistory(AccountId accountId, LocalDate from, LocalDate to);

    List<AccountHistoryRecord> getHistoryByEntryId(EntryId entryId);
}
