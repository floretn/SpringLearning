package ru.nspk.task7.listener;

import ru.nspk.task7.model.EntryId;

public record EntryEvent(EntryId id, EntryEventType eventType) {}
