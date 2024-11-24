package ru.nspk.task7.config;

import static org.mockito.Mockito.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.nspk.task7.service.AccountHistoryService;

@TestConfiguration
public class TestConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AccountHistoryService accountHistoryService() {
        return mock(AccountHistoryService.class);
    }
}
