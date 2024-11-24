package ru.nspk.task4.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nspk.card.model.PaymentSystem.VISA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nspk.card.model.Card;
import ru.nspk.card.service.ICardService;
import ru.nspk.card.service.VisaCardService;

@SpringBootTest(properties = "account.card.ps=VISA")
class VisaCardServiceTest {

    @Autowired
    private ICardService cardService;

    @Test
    void test_ps_visa() {
        assertTrue(cardService instanceof VisaCardService);
        Card card = cardService.createCard("test_name_visa");
        assertEquals("test_name_visa", card.name());
        assertEquals(VISA, card.ps());
    }
}
