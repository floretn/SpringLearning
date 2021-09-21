package ru.mephi.boot.controllers.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mephi.boot.dao.models.UserEntity;
import ru.mephi.boot.exeptions.MyRuntimeException;
import ru.mephi.boot.services.auth.RegistrationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @GetMapping
    public String registrationPage(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "auth/registration";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") RegistrationRequest registrationRequest, BindingResult bindingResult,
            Model model) {

        boolean isPasswordDifferent = registrationRequest.getPassword() != null
                && !registrationRequest.getPassword().equals(registrationRequest.getPasswordConfirm());

        if (isPasswordDifferent){
            model.addAttribute("passwordError", "Пароли не совпадают!");
        }

        if (bindingResult.hasErrors() || isPasswordDifferent) {
            return "auth/registration";
        }

        model.addAttribute("isRegisterForm", true);

        try {
            registrationService.registerUser(new UserEntity(0, registrationRequest.getMiddleName(), registrationRequest.getFirstName()
                    , registrationRequest.getLastName(), registrationRequest.getEmail(), registrationRequest.getPhone()
                    , registrationRequest.getUsername(), registrationRequest.getPassword()
                    , null));
        } catch (MyRuntimeException ex) {
            model.addAttribute("usernameError", ex.getMessage());
            return "auth/registration";
        }

        return "redirect:/auth/login";
    }

}
