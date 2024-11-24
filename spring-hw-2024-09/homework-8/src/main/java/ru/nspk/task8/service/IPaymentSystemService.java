package ru.nspk.task8.service;

import java.time.LocalDateTime;
import java.util.List;
import ru.nspk.task8.dao.model.Transaction;

public interface IPaymentSystemService {
    Transaction makeTransaction(long creditId, long debitId, long sum);

    Transaction reverseTransaction(long historyId);

    List<Transaction> getAccountHistory(long accountId, LocalDateTime from, LocalDateTime to);
}
