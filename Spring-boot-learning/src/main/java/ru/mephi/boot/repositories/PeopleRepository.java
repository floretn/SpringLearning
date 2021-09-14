package ru.mephi.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.boot.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    Person findAllById(long id);
}
