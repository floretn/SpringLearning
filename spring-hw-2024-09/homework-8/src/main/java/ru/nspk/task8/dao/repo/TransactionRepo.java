package ru.nspk.task8.dao.repo;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.nspk.task8.dao.model.Transaction;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Long> {

    @NonNull
    List<Transaction> findAll();

    @Query(
            "select * from ps.transaction where (id_credit_acc_fk = :id or id_debit_acc_fk = :id) and time >= :from and time <= :to")
    List<Transaction> getTransactionsByIdAndTimePeriod(
            @Param("id") long accountId, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
