package ru.mephi.spring.mvc.java_code.rest.dao;

import ru.mephi.spring.mvc.java_code.rest.models.Person;

import java.util.List;

public interface DAOPeople {

    List<Person> getAll();

    Person getPerson(int id);

    void addPerson(Person person);

    void updatePerson(int id, Person person);

    void deletePerson(int id);
}
