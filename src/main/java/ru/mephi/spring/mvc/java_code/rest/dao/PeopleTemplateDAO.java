package ru.mephi.spring.mvc.java_code.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mephi.spring.mvc.java_code.rest.dao.mapper.PersonMapper;
import ru.mephi.spring.mvc.java_code.rest.models.Person;

import java.util.List;

@Component
public class PeopleTemplateDAO implements DAOPeople {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleTemplateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM people.people", new PersonMapper()); //Можно было бы использовать BeanPropertyRowMapper
                                                            // , если бы имена полей Person были такие же, как в таблице (И наоборот).
                                                            // типа new BeanPropertyRowMapper<>(Person.class)
    }

    @Override
    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM people.people WHERE id_person = ?", new PersonMapper(), id)
                .stream().findAny().orElse(null);
    }

    @Override
    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO people.people (first_name, last_name, age, email) VALUES(?, ?, ?, ?)"
                , person.getName(), person.getSurname(), person.getAge(), person.getEmail());
    }

    @Override
    public void updatePerson(int id, Person person) {
        jdbcTemplate.update("UPDATE people.people SET first_name = ?, last_name = ?, age = ?, email = ? WHERE id_person = ?"
                , person.getName(), person.getSurname(), person.getAge(), person.getEmail(), id);
    }

    @Override
    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM people.people WHERE id_person = ?", id);
    }
}
