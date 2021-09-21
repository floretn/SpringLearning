package ru.mephi.boot.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.boot.dao.models.RoleEntity;

import java.util.Set;

@Repository
public interface RolesRepository extends CrudRepository<RoleEntity, Long> {
    Set<RoleEntity> findBySymbolicNameIn(Set<String> roles);
}
