package ru.nspk.task10.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ExtContractsWebServiceImpl implements ExtContractsWebService {

    private final WebClient webClient;

    @Override
    public Flux<String> verifyAccount(long accountId) {
        return webClient
                .get()
                .uri(UriComponentsBuilder.fromUriString("/account/{accountNumber}/status")
                        .queryParam("accountNumber", accountId)
                        .toUriString())
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(String.class));
    }
}
