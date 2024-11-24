package ru.nspk.task10.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nspk.task10.dao.model.Transaction;
import ru.nspk.task10.dao.repo.TransactionRepo;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public Mono<Transaction> saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Mono<Transaction> createTransaction(long creditId, long debitId, long sum, LocalDateTime time) {
        Transaction transaction = new Transaction(creditId, debitId, sum, time);
        return transactionRepo.save(transaction);
    }

    public Mono<Transaction> getTransactionById(long transactionId) {
        return transactionRepo.findById(transactionId);
    }

    public Flux<Transaction> getHistoryForAccountIdByTimePeriod(long accountId, LocalDateTime from, LocalDateTime to) {
        return transactionRepo.getTransactionsByIdAndTimePeriod(accountId, from, to);
    }
}
