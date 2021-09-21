package ru.mephi.boot.controllers.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "/main";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping
    public String goOnHomePage(HttpServletRequest request) {
        return "/home";
    }
}
