package ru.nspk.task6.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import ru.nspk.task6.model.AccountBalance;
import ru.nspk.task6.model.AccountHistoryRecord;
import ru.nspk.task6.model.AccountId;
import ru.nspk.task6.model.EntryId;
import ru.nspk.task6.utils.TimeUtil;

@RequiredArgsConstructor
public class AccountHistoryServiceInMemory implements AccountHistoryService {

    // + List, because there can be several records in one day
    @SuppressWarnings("java:S1068")
    private final Map<AccountId, NavigableMap<LocalDate, List<AccountHistoryRecord>>> history = new HashMap<>();

    private final TimeUtil timeUtil;

    @Override
    public void add(AccountHistoryRecord historyRecord) {
        history.computeIfAbsent(historyRecord.accountId(), f -> new TreeMap<>())
                .computeIfAbsent(timeUtil.getCurrentDate(), f -> new ArrayList<>())
                .add(historyRecord);
    }

    @Override
    public AccountBalance getBalance(AccountId accountId, LocalDate data) {
        if (!history.containsKey(accountId)) {
            throw new IllegalArgumentException(String.format("Account with id '%d' was not found", accountId.id()));
        }
        if (timeUtil.getCurrentDate().isBefore(data)) {
            throw new IllegalArgumentException("Balance request for future data");
        }
        long balance = history.get(accountId).headMap(data, true).values().stream()
                .mapToLong(accountHistoryRecords -> accountHistoryRecords.stream()
                        .mapToLong(AccountHistoryRecord::operationSum)
                        .sum())
                .sum();
        return new AccountBalance(balance);
    }

    @Override
    public List<AccountHistoryRecord> getHistory(AccountId accountId, LocalDate from, LocalDate to) {
        if (!history.containsKey(accountId)) {
            throw new IllegalArgumentException();
        }
        return history.get(accountId).subMap(from, true, to, true).values().stream()
                .flatMap(Collection::stream)
                .toList();
    }

    @Override
    public List<AccountHistoryRecord> getHistoryByEntryId(EntryId entryId) {
        return history.values().stream()
                .flatMap(recordsMap -> recordsMap.values().stream())
                .flatMap(Collection::stream)
                .filter(accountHistoryRecord -> accountHistoryRecord.entryId().equals(entryId))
                .toList();
    }
}
