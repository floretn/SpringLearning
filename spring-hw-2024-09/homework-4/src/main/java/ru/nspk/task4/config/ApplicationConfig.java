package ru.nspk.task4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.card.service.RandomIdGenerator;
import ru.nspk.task4.service.AccountHistoryService;
import ru.nspk.task4.service.AccountHistoryServiceInMemory;
import ru.nspk.task4.service.AccountService;
import ru.nspk.task4.service.AccountServiceBase;
import ru.nspk.task4.utils.TimeUtil;

@Configuration
public class ApplicationConfig {

    @Bean
    public TimeUtil timeService() {
        return new TimeUtil();
    }

    @Bean
    public AccountHistoryService accountHistoryInMemory(TimeUtil timeUtil) {
        return new AccountHistoryServiceInMemory(timeUtil);
    }

    @Bean
    public AccountService accountServiceBase(
            AccountHistoryService accountHistoryInMemory,
            TimeUtil timeUtil,
            RandomIdGenerator randomIdGenerator,
            AccountProperties accountProperties) {
        return new AccountServiceBase(accountHistoryInMemory, timeUtil, randomIdGenerator, accountProperties);
    }
}
