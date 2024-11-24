package ru.nspk.task4.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nspk.card.model.PaymentSystem.MC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nspk.card.model.Card;
import ru.nspk.card.service.ICardService;
import ru.nspk.card.service.McCardService;

@SpringBootTest(properties = "account.card.ps=MC")
class McCardServiceTest {

    @Autowired
    private ICardService cardService;

    @Test
    void test_ps_mc() {
        assertTrue(cardService instanceof McCardService);
        Card card = cardService.createCard("test_name_mc");
        assertEquals("test_name_mc", card.name());
        assertEquals(MC, card.ps());
    }
}
