package ru.mephi.boot.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.boot.dao.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    Person findAllById(long id);
}
