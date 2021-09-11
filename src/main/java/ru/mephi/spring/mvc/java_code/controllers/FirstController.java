package ru.mephi.spring.mvc.java_code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    private String name;
    private String surname;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request, Model model) {
        name = request.getParameter("name");
        surname = request.getParameter("surname");
        System.out.println(name + " " + surname);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "first/hello";
    }

    @GetMapping("/poka")
    public String goodBye(@RequestParam("name") String name
            , @RequestParam(value = "surname", required = false) String surname, Model model) {
        this.name = name;
        this.surname = surname;
        System.out.println(name + " " + surname);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "first/poka";
    }
}
