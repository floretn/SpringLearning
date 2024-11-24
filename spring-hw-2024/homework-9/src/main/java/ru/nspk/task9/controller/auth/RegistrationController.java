package ru.nspk.task9.controller.auth;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nspk.task9.dao.model.auth.User;
import ru.nspk.task9.service.auth.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {

        boolean isPasswordDifferent =
                user.getPassword() != null && !user.getPassword().equals(passwordConfirm);

        if (isPasswordDifferent) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        boolean isConfirmEmpty = ObjectUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cancelled!");
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || isPasswordDifferent) {
            Map<String, String> errorsMap = getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User already exists!");
            return "registration";
        }

        model.addAttribute("isRegisterForm", true);

        return "redirect:/login";
    }

    public Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector =
                Collectors.toMap(fieldError -> fieldError.getField() + "Error", FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
