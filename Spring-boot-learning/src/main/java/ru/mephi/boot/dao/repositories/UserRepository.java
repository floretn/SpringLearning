package ru.mephi.boot.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.mephi.boot.dao.models.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
   UserEntity findByUsername(String username);
}
