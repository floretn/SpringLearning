package ru.nspk.task6.listener;

import ru.nspk.task6.model.EntryId;

public record EntryEvent(EntryId id, EntryEventType eventType) {}
