package ru.nspk.task9.config;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig implements WebSecurityCustomizer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // static и activate нужны для freemarker
                .authorizeHttpRequests(reqs -> reqs.mvcMatchers("login", "registration", "/static/**", "/activate/*")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main", true)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers("/instances", "/actuator/**")
                .and()
                .build();
    }

    /**
     * Чтобы создавать объекты через Postman без авторизации.
     * Посмотреть всё созданное можно будет по адресу http://localhost:8081/account/{id} в браузере.
     * Просмотр через браузер будет доступен только после авторизации.
     * Прошу прощения, сделать полноценные страницы для работы с этими данными не успеваю(((
     */
    @Override
    public void customize(WebSecurity webSecurity) {
        webSecurity
                .ignoring()
                .antMatchers(POST, "/account/create", "/transaction/**")
                .antMatchers(PUT, "/transaction/**");
    }
}
