package ru.mephi.spring.mvc.java_code.rest.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.mephi.spring.mvc.java_code.rest.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person;
        person = new Person();
        person.setId(resultSet.getInt("id_person"));
        person.setName(resultSet.getString("first_name"));
        person.setSurname(resultSet.getString("last_name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }
}
