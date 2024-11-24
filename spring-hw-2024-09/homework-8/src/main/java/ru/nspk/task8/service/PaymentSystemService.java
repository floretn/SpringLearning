package ru.nspk.task8.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nspk.task8.annotation.Timed;
import ru.nspk.task8.dao.model.Account;
import ru.nspk.task8.dao.model.Transaction;
import ru.nspk.task8.utils.TimeUtil;

@Service
@Timed
@RequiredArgsConstructor
public class PaymentSystemService implements IPaymentSystemService {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TimeUtil timeUtil;

    @Override
    public Transaction makeTransaction(long creditId, long debitId, long sum) {
        if (creditId == debitId) {
            throw new IllegalArgumentException("credit and debit account the same with id = %d".formatted(creditId));
        }
        creditAndDebitAccounts(creditId, debitId, sum);
        return transactionService.createTransaction(creditId, debitId, sum, timeUtil.getCurrentTime());
    }

    @Override
    public Transaction reverseTransaction(long transactionId) {
        Transaction original = transactionService.getTransactionById(transactionId);
        creditAndDebitAccounts(original.getDebitAccountId(), original.getCreditAccountId(), original.getSum());
        Transaction reversal = new Transaction(
                original.getDebitAccountId(),
                original.getCreditAccountId(),
                original.getSum(),
                timeUtil.getCurrentTime());
        return transactionService.saveTransaction(reversal);
    }

    @Override
    public List<Transaction> getAccountHistory(long accountId, LocalDateTime from, LocalDateTime to) {
        if (from == null && to == null) {
            Account account = accountService.getAccountById(accountId);
            List<Transaction> transactions = new ArrayList<>();
            transactions.addAll(account.getCreditTransactions());
            transactions.addAll(account.getDebitTransactions());
            return transactions;
        }
        return transactionService.getHistoryForAccountIdByTimePeriod(
                accountId,
                dateOrDefaultValue(from, TimeUtil.EPOCH_DATETIME),
                dateOrDefaultValue(to, LocalDateTime.now()));
    }

    private LocalDateTime dateOrDefaultValue(LocalDateTime time, LocalDateTime defaultValue) {
        if (time == null) {
            return defaultValue;
        }
        return time;
    }

    private void creditAndDebitAccounts(long creditId, long debitId, long sum) {
        Account credit = accountService.getAccountById(creditId);
        Account debit = accountService.getAccountById(debitId);
        credit.reduceBalance(sum);
        debit.increaseBalance(sum);
        accountService.updateAccount(credit);
        accountService.updateAccount(debit);
    }
}
