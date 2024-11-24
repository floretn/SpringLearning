package ru.nspk.task8.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.nspk.task8.dao.model.Transaction;
import ru.nspk.task8.dao.repo.TransactionRepo;
import ru.nspk.task8.utils.TimeUtil;

@SpringBootTest
class TransactionRepoTest extends BaseContainer {

    @Autowired
    private TransactionRepo transactionRepo;

    @AfterEach
    @Sql(statements = "delete from ps.account")
    void cleanTable() {
        transactionRepo.deleteAll();
    }

    @Test
    @Sql(statements = {"insert into ps.account (balance) values (0)", "insert into ps.account (balance) values (0)"})
    void test_save() {
        transactionRepo.save(new Transaction(1, 2, 100, LocalDateTime.now()));

        List<Transaction> transactions = transactionRepo.findAll();
        assertEquals(1, transactions.size());
    }

    @Test
    @Sql(
            statements = {
                "insert into ps.account (balance) values (0)",
                "insert into ps.account (balance) values (0)",
                "insert into ps.transaction (id_credit_acc_fk, id_debit_acc_fk, sum, time) values (1, 2, 10, now()::timestamp)",
                "insert into ps.transaction (id_credit_acc_fk, id_debit_acc_fk, sum, time) values (2, 1, 100, (now()::timestamp - interval '1 days'))",
                "insert into ps.transaction (id_credit_acc_fk, id_debit_acc_fk, sum, time) values (1, 2, 200, (now()::timestamp - interval '2 days'))",
                "insert into ps.transaction (id_credit_acc_fk, id_debit_acc_fk, sum, time) values (2, 1, 300, (now()::timestamp - interval '3 days'))",
            })
    void test_find_by_id_and_time_period() {
        List<Transaction> transactions = transactionRepo.findAll();
        assertEquals(4, transactions.size());

        List<Transaction> transactionFirst =
                transactionRepo.getTransactionsByIdAndTimePeriod(1, TimeUtil.EPOCH_DATETIME, LocalDateTime.now());
        List<Transaction> transactionsSecond =
                transactionRepo.getTransactionsByIdAndTimePeriod(2, TimeUtil.EPOCH_DATETIME, LocalDateTime.now());

        assertEquals(4, transactionFirst.size());
        assertEquals(4, transactionsSecond.size());

        transactionFirst = transactionRepo.getTransactionsByIdAndTimePeriod(
                1, LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(1));
        transactionsSecond = transactionRepo.getTransactionsByIdAndTimePeriod(
                2, LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(1));

        assertEquals(2, transactionFirst.size());
        assertEquals(2, transactionsSecond.size());
    }
}
