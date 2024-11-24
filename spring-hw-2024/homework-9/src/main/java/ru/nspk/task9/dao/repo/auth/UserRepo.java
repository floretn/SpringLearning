package ru.nspk.task9.dao.repo.auth;

import org.springframework.data.repository.CrudRepository;
import ru.nspk.task9.dao.model.auth.User;

public interface UserRepo extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}
