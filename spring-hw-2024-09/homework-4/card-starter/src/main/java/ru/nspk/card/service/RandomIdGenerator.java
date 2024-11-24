package ru.nspk.card.service;

import java.util.UUID;

public class RandomIdGenerator {
    public long generateRandomLongId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
