package ru.nspk.task10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nspk.task10.utils.TimeUtil;

@Configuration
public class ApplicationConfig {
    @Bean
    public TimeUtil timeService() {
        return new TimeUtil();
    }
}
