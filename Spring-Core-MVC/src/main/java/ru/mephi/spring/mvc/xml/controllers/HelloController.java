package ru.mephi.spring.mvc.xml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    //Чтобы работало надо переименовать web.txt в web.xml, а текущий web.xml удалить
    // Видимо диспетчер из web.xml первостепеннее жавишного, поэтому чтобы работала жавишная конфигурация, я .
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
