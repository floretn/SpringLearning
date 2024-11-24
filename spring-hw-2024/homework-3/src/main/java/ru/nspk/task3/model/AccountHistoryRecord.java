package ru.nspk.task3.model;

import java.time.LocalDateTime;

public record AccountHistoryRecord(EntryId entryId, AccountId accountId, LocalDateTime date, long operationSum) {}
