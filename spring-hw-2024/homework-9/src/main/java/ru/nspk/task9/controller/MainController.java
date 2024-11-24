package ru.nspk.task9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    String mainPageRoot() {
        return "main";
    }

    @GetMapping("main")
    String mainPage() {
        return "main";
    }
}
