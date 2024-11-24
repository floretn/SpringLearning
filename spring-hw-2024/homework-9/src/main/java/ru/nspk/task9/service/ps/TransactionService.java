package ru.nspk.task9.service.ps;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nspk.task9.dao.model.ps.Transaction;
import ru.nspk.task9.dao.repo.ps.TransactionRepo;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Transaction createTransaction(long creditId, long debitId, long sum, LocalDateTime time) {
        Transaction transaction = new Transaction(creditId, debitId, sum, time);
        return transactionRepo.save(transaction);
    }

    public Transaction getTransactionById(long transactionId) {
        return transactionRepo
                .findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "History record was not found for id %s".formatted(transactionId)));
    }

    public List<Transaction> getHistoryForAccountIdByTimePeriod(long accountId, LocalDateTime from, LocalDateTime to) {
        return transactionRepo.getTransactionsByIdAndTimePeriod(accountId, from, to);
    }
}
