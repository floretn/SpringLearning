package ru.nspk.task1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.task1.service.AccountHistoryService;
import ru.nspk.task1.service.AccountHistoryServiceInMemory;
import ru.nspk.task1.service.AccountService;
import ru.nspk.task1.service.AccountServiceBase;
import ru.nspk.task1.utils.TimeUtil;

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
    public AccountService accountServiceBase(AccountHistoryService accountHistoryInMemory, TimeUtil timeUtil) {
        return new AccountServiceBase(accountHistoryInMemory, timeUtil);
    }
}
