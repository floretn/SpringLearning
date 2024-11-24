package ru.nspk.task10.dao.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import ru.nspk.task10.dao.model.Account;

public interface AccountRepo extends ReactiveCrudRepository<Account, Long> {
    @NonNull
    Flux<Account> findAll();
}
