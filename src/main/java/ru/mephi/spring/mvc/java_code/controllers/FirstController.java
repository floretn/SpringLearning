package ru.mephi.spring.mvc.java_code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String hello() {
        return "first/hello";
    }

    @GetMapping("/poka")
    public String goodBye() {return "first/poka";}
}
