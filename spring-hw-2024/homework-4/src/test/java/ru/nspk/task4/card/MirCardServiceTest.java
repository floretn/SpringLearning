package ru.nspk.task4.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nspk.card.model.PaymentSystem.MIR;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.nspk.card.model.Card;
import ru.nspk.card.service.ICardService;
import ru.nspk.card.service.MirCardService;

@SpringBootTest(properties = "account.card.ps=MIR")
class MirCardServiceTest {

    @Autowired
    private ICardService cardService;

    @Test
    void test_ps_mir() {
        assertTrue(cardService instanceof MirCardService);
        Card card = cardService.createCard("test_name_mir");
        assertEquals("test_name_mir", card.name());
        assertEquals(MIR, card.ps());
    }
}
