package ru.nspk.task5.listener;

import ru.nspk.task5.model.EntryId;

public record EntryEvent(EntryId id, EntryEventType eventType) {}
