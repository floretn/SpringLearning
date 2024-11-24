package ru.nspk.task8.dao.model;

import java.util.Set;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table(value = "account", schema = "ps")
public class Account {

    @Id
    @Column("id_acc")
    private long id;

    @Column("balance")
    private long balance;

    @MappedCollection(idColumn = "id_credit_acc_fk")
    private final Set<Transaction> creditTransactions;

    @MappedCollection(idColumn = "id_debit_acc_fk")
    private final Set<Transaction> debitTransactions;

    @PersistenceCreator
    public Account(long id, long balance, Set<Transaction> creditTransactions, Set<Transaction> debitTransactions) {
        this.id = id;
        this.balance = balance;
        this.creditTransactions = creditTransactions;
        this.debitTransactions = debitTransactions;
    }

    public Account(long balance) {
        this(0, balance, null, null);
    }

    public void increaseBalance(long value) {
        balance += value;
    }

    public void reduceBalance(long value) {
        balance -= value;
    }
}
