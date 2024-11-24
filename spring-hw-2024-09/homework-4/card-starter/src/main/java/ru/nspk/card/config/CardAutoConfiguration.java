package ru.nspk.card.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.card.service.ICardService;
import ru.nspk.card.service.McCardService;
import ru.nspk.card.service.MirCardService;
import ru.nspk.card.service.RandomIdGenerator;
import ru.nspk.card.service.VisaCardService;

@Configuration
public class CardAutoConfiguration {
    @Bean
    public RandomIdGenerator randomIdGenerator() {
        return new RandomIdGenerator();
    }

    @Bean
    @ConditionalOnProperty(prefix = "account.card", name = "ps", havingValue = "MIR")
    public ICardService mirCardService(RandomIdGenerator randomIdGenerator) {
        return new MirCardService(randomIdGenerator);
    }

    @Bean
    @ConditionalOnProperty(prefix = "account.card", name = "ps", havingValue = "VISA")
    public ICardService visaCardService(RandomIdGenerator randomIdGenerator) {
        return new VisaCardService(randomIdGenerator);
    }

    @Bean
    @ConditionalOnProperty(prefix = "account.card", name = "ps", havingValue = "MC")
    public ICardService mcCardService(RandomIdGenerator randomIdGenerator) {
        return new McCardService(randomIdGenerator);
    }
}
