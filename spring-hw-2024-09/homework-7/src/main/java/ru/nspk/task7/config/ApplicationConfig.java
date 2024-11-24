package ru.nspk.task7.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.task7.service.AccountHistoryService;
import ru.nspk.task7.service.AccountHistoryServiceInMemory;
import ru.nspk.task7.service.AccountService;
import ru.nspk.task7.service.AccountServiceBase;
import ru.nspk.task7.utils.TimeUtil;

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
            ApplicationEventPublisher applicationEventPublisher) {
        return new AccountServiceBase(accountHistoryInMemory, timeUtil, applicationEventPublisher);
    }
}
