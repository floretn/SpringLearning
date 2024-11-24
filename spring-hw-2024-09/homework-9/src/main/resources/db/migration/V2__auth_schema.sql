create schema if not exists auth;

create table if not exists auth.user(
                                         id_usr bigserial,
                                         login varchar(20),
                                         pwd varchar(100),
                                         primary key (id_usr)
);

-- login: 'user', password: '123'
insert into auth."user" (login, pwd) values ('user', '$2a$08$ofiaasiAmG3vXff/iRs22.BWmCTCsu53XGrYlbyLDFYMrttE4l3Ve');