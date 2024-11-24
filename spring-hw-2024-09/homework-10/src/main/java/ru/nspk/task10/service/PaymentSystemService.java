package ru.nspk.task10.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nspk.task10.annotation.Timed;
import ru.nspk.task10.dao.model.Account;
import ru.nspk.task10.dao.model.Transaction;
import ru.nspk.task10.utils.TimeUtil;

@Service
@Timed
@RequiredArgsConstructor
public class PaymentSystemService implements IPaymentSystemService {

    private static final String DISABLED_STATUS = "DISABLED";

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final ExtContractsWebService extContractsWebService;
    private final TimeUtil timeUtil;

    @Override
    public Mono<Transaction> makeTransaction(long creditId, long debitId, long sum) {
        if (creditId == debitId) {
            return Mono.error(new IllegalArgumentException("credit and debit account the same with id = %d".formatted(creditId)));
        }
        return extContractsWebService
                .verifyAccount(creditId)
                .filter(status -> !status.equals(DISABLED_STATUS))
                .switchIfEmpty(Mono.error(new IllegalArgumentException("credit account is disabled")))
                .flatMap(status -> changeAccountBalance(creditId, -sum))
                .flatMap(creditAcc -> changeAccountBalance(debitId, sum))
                .flatMap(debitAcc ->
                        transactionService.createTransaction(creditId, debitId, sum, timeUtil.getCurrentTime()))
                .ignoreElements();
    }

    @Override
    public Mono<Transaction> reverseTransaction(long transactionId) {
        return transactionService
                .getTransactionById(transactionId)
                .flatMap(original -> changeAccountBalance(original.getCreditAccountId(), original.getSum())
                        .map(credit -> original))
                .flatMap(original -> changeAccountBalance(original.getDebitAccountId(), -original.getSum())
                        .map(debit -> original))
                .map(original -> new Transaction(
                        original.getDebitAccountId(),
                        original.getCreditAccountId(),
                        original.getSum(),
                        timeUtil.getCurrentTime()))
                .flatMap(transactionService::saveTransaction);
    }

    @Override
    public Flux<Transaction> getAccountHistory(long accountId, LocalDateTime from, LocalDateTime to) {
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

    private Mono<Account> changeAccountBalance(long accountId, long sum) {
        return accountService
                .getAccountById(accountId)
                .map(account -> {
                    account.changeBalance(sum);
                    return account;
                })
                .flatMap(accountService::updateAccount);
    }
}
