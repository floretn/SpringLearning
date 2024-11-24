package ru.nspk.task10.dao.repo;

import java.time.LocalDateTime;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.nspk.task10.dao.model.Transaction;

@Repository
public interface TransactionRepo extends ReactiveCrudRepository<Transaction, Long> {

    @NonNull
    Flux<Transaction> findAll();

    @Query(
            "select * from ps.transaction where (id_credit_acc_fk = :id or id_debit_acc_fk = :id) and time >= :from and time <= :to")
    Flux<Transaction> getTransactionsByIdAndTimePeriod(
            @Param("id") long accountId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
