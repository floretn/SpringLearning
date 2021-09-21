package ru.mephi.boot.controllers.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mephi.boot.dao.models.Person;
import ru.mephi.boot.services.people.PeopleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String showAll(Model model) {
        model.addAttribute("people", peopleService.showAll());
        return "people/show_all";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", peopleService.getPerson(id));
        return "people/show";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person()); // Можно заменить на "@ModelAttribute("person") Person person" в параметрах
        return "people/new";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {
        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.addPerson(person);
        return "redirect:/people";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.getPerson(id));
        return "people/edit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult
            , @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.updatePerson(id, person);
        return "redirect:/people";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }
}
