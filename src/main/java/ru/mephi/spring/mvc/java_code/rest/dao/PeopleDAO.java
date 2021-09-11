package ru.mephi.spring.mvc.java_code.rest.dao;

import org.springframework.stereotype.Component;
import ru.mephi.spring.mvc.java_code.rest.models.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PeopleDAO {

    private static int cnt = 3;

    private static final List<Person> PEOPLE = new ArrayList<>(Arrays.asList(new Person(1, "Name", "SurName")
            , new Person(2, "N1", "N2")));

    public List<Person> getAll() {
        return PEOPLE;
    }

    public Person getPerson(int id) {
        return PEOPLE.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }

    public void addPerson(Person person) {
        person.setId(cnt++);
        PEOPLE.add(person);
    }
}
