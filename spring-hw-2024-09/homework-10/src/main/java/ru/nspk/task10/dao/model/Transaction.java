package ru.nspk.task10.dao.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table(value = "transaction", schema = "ps")
public class Transaction {

    @Id
    @Column("id_trans")
    private long id;

    // Why can't I create link to creditAccount and debitAccount like @ManyToOne in jpa?!?!?!?!?

    @Column("id_credit_acc_fk")
    private final long creditAccountId;

    @Column("id_debit_acc_fk")
    private final long debitAccountId;

    @Column("sum")
    private final long sum;

    @Column("time")
    private final LocalDateTime time;

    @PersistenceCreator
    public Transaction(long id, long creditAccountId, long debitAccountId, long sum, LocalDateTime time) {
        this.id = id;
        this.creditAccountId = creditAccountId;
        this.debitAccountId = debitAccountId;
        this.sum = sum;
        this.time = time;
    }

    public Transaction(long creditAccountId, long debitAccountId, long sum, LocalDateTime time) {
        this.creditAccountId = creditAccountId;
        this.debitAccountId = debitAccountId;
        this.sum = sum;
        this.time = time;
    }
}
