package ru.nspk.card.service;

import lombok.RequiredArgsConstructor;
import ru.nspk.card.model.CardId;

@RequiredArgsConstructor
public abstract class AbstractCardService implements ICardService {
    private final RandomIdGenerator randomIdGenerator;

    protected CardId createCardId() {
        return new CardId(randomIdGenerator.generateRandomLongId());
    }
}
