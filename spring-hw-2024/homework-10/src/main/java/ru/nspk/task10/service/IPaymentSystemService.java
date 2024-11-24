package ru.nspk.task10.service;

import java.time.LocalDateTime;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nspk.task10.dao.model.Transaction;

public interface IPaymentSystemService {
    Mono<Transaction> makeTransaction(long creditId, long debitId, long sum);

    Mono<Transaction> reverseTransaction(long historyId);

    Flux<Transaction> getAccountHistory(long accountId, LocalDateTime from, LocalDateTime to);
}
