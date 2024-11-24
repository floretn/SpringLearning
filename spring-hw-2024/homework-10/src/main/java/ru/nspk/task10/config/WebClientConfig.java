package ru.nspk.task10.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Value("${app.ext-service-url}")
    private String extServiceUrl;

    @Bean
    public WebClient externalContractServiceWebClient() {
        return WebClient.builder()
                .baseUrl(extServiceUrl)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
                .build();
    }
}
