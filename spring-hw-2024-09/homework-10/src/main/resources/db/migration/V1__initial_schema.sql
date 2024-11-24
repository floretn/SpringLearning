create schema if not exists ps;

create table if not exists ps.account(
                                         id_acc bigserial,
                                         balance bigint,
                                         primary key (id_acc)
);

create table if not exists ps.transaction(
                                             id_trans bigserial,
                                             id_credit_acc_fk integer,
                                             id_debit_acc_fk integer,
                                             sum bigint,
                                             time timestamp,
                                             PRIMARY KEY (id_trans),
                                             FOREIGN KEY (id_credit_acc_fk) REFERENCES ps.account(id_acc),
                                             FOREIGN KEY (id_debit_acc_fk) REFERENCES ps.account(id_acc)
);