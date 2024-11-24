package ru.nspk.card.service;

import static ru.nspk.card.model.PaymentSystem.MIR;

import ru.nspk.card.model.Card;

public class MirCardService extends AbstractCardService {

    public MirCardService(RandomIdGenerator randomIdGenerator) {
        super(randomIdGenerator);
    }

    @Override
    public Card createCard(String name) {
        return new Card(createCardId(), name, MIR);
    }
}
