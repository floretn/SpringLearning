package ru.nspk.task10.service;

import reactor.core.publisher.Flux;

public interface ExtContractsWebService {
    Flux<String> verifyAccount(long accountId);
}
