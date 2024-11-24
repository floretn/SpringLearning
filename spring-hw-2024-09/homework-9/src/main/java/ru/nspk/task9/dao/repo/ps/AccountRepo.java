package ru.nspk.task9.dao.repo.ps;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.nspk.task9.dao.model.ps.Account;

public interface AccountRepo extends CrudRepository<Account, Long> {
    @NonNull
    List<Account> findAll();
}
