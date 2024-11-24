package ru.nspk.task2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.task2.service.AccountHistoryService;
import ru.nspk.task2.service.AccountHistoryServiceInMemory;
import ru.nspk.task2.service.AccountService;
import ru.nspk.task2.service.AccountServiceBase;
import ru.nspk.task2.utils.TimeUtil;

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
            AccountHistoryService accountHistoryInMemory, TimeUtil timeUtil, AccountProperties accountProperties) {
        return new AccountServiceBase(accountHistoryInMemory, timeUtil, accountProperties);
    }
}
