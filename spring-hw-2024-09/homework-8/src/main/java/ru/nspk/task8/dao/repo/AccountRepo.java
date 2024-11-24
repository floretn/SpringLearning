package ru.nspk.task8.dao.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.nspk.task8.dao.model.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {
    @NonNull
    List<Account> findAll();
}
