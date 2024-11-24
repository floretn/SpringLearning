package ru.nspk.task9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.nspk.task9.utils.TimeUtil;

@Configuration
public class ApplicationConfig {
    @Bean
    public TimeUtil timeService() {
        return new TimeUtil();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(8);
    }
}
