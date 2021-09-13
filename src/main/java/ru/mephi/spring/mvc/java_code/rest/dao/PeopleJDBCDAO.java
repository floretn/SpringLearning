package ru.mephi.spring.mvc.java_code.rest.dao;

import org.springframework.stereotype.Component;
import ru.mephi.spring.mvc.java_code.rest.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleJDBCDAO implements DAOPeople{

    private static final String URL = "jdbc:postgresql://localhost:5432/springdb";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "123";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM people.people");

            Person person;
            while (resultSet.next()) {
                person = getPersonFromSet(resultSet);
                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    private Person getPersonFromSet(ResultSet resultSet) throws SQLException {
        Person person;
        person = new Person();
        person.setId(resultSet.getInt("id_person"));
        person.setName(resultSet.getString("first_name"));
        person.setSurname(resultSet.getString("last_name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        return person;
    }

    @Override
    public Person getPerson(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM people.people WHERE id_person = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return getPersonFromSet(resultSet);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }



    @Override
    public void addPerson(Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO people.people (first_name" +
                    ", last_name, age, email) VALUES(?, ?, ?, ?)");

            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updatePerson(int id, Person person) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE people.people SET first_name = ?, last_name = ?" +
                    ", age = ?, email = ? WHERE id_person = ?");

            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deletePerson(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM people.people WHERE id_person = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
