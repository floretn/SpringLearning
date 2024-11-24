package ru.nspk.card.service;

import static ru.nspk.card.model.PaymentSystem.VISA;

import ru.nspk.card.model.Card;

public class VisaCardService extends AbstractCardService {

    public VisaCardService(RandomIdGenerator randomIdGenerator) {
        super(randomIdGenerator);
    }

    @Override
    public Card createCard(String name) {
        return new Card(createCardId(), name, VISA);
    }
}
