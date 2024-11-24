package ru.nspk.task4.model;

import java.time.LocalDateTime;

public record AccountHistoryRecord(EntryId entryId, AccountId accountId, LocalDateTime date, long operationSum) {}
