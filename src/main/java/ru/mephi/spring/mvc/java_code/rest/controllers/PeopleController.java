package ru.mephi.spring.mvc.java_code.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mephi.spring.mvc.java_code.rest.dao.PeopleDAO;
import ru.mephi.spring.mvc.java_code.rest.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("people", peopleDAO.getAll());
        return "people/show_all";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.getPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person()); // Можно заменить на "@ModelAttribute("person") Person person" в параметрах
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        peopleDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        peopleDAO.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleDAO.deletePerson(id);
        return "redirect:/people";
    }
}
