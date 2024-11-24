package ru.nspk.task3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.task3.service.AccountHistoryService;
import ru.nspk.task3.service.AccountHistoryServiceInMemory;
import ru.nspk.task3.service.AccountService;
import ru.nspk.task3.service.AccountServiceBase;
import ru.nspk.task3.utils.TimeUtil;

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
