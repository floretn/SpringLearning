package ru.nspk.task10.dao.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

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
    private final Flux<Transaction> creditTransactions;

    @MappedCollection(idColumn = "id_debit_acc_fk")
    private final Flux<Transaction> debitTransactions;

    @PersistenceCreator
    public Account(long id, long balance, Flux<Transaction> creditTransactions, Flux<Transaction> debitTransactions) {
        this.id = id;
        this.balance = balance;
        this.creditTransactions = creditTransactions;
        this.debitTransactions = debitTransactions;
    }

    public Account(long balance) {
        this(0, balance, null, null);
    }

    public void changeBalance(long value) {
        balance += value;
    }
}
