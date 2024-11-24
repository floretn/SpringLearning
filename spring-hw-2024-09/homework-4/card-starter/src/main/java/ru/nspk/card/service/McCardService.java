package ru.nspk.card.service;

import static ru.nspk.card.model.PaymentSystem.MC;

import ru.nspk.card.model.Card;

public class McCardService extends AbstractCardService {

    public McCardService(RandomIdGenerator randomIdGenerator) {
        super(randomIdGenerator);
    }

    @Override
    public Card createCard(String name) {
        return new Card(createCardId(), name, MC);
    }
}
