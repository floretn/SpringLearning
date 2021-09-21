package ru.mephi.boot.services.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.boot.dao.models.Person;
import ru.mephi.boot.dao.repositories.PeopleRepository;
import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person getPerson(long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public List<Person> showAll() {
        return peopleRepository.findAll();
    }

    public void addPerson(Person person) {
        peopleRepository.save(person);
    }

    public void updatePerson(long id, Person person) {
        peopleRepository.save(person);
    }

    public void deletePerson(long id) {
        peopleRepository.deleteById(id);
    }
}
