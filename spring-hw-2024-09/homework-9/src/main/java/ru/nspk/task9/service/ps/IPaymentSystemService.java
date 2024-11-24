package ru.nspk.task9.service.ps;

import java.time.LocalDateTime;
import java.util.List;
import ru.nspk.task9.dao.model.ps.Transaction;

public interface IPaymentSystemService {
    Transaction makeTransaction(long creditId, long debitId, long sum);

    Transaction reverseTransaction(long historyId);

    List<Transaction> getAccountHistory(long accountId, LocalDateTime from, LocalDateTime to);
}
