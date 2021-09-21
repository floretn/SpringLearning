package ru.mephi.boot.controllers.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "Фамилия должна быть заполнена!")
    @Size(min = 2, max = 20, message = "Не поверю, что бывают фамилии длиннее 20 символов или короче 2...")
    @Pattern(regexp = "^[А-Я]{1}[а-я]*", message = "Фамилия должна состоять только из русских букв " +
            "и начинаться с заглавной буквы!")
    private String middleName;

    @NotEmpty(message = "Имя должно быть заполнено!")
    @Size(min = 2, max = 15, message = "Мне не знакомы имена короче 2 или длиннее 15 символов:(")
    @Pattern(regexp = "^[А-Я]{1}[а-я]+", message = "Имя должно состоять только из русских букв " +
            "и начинаться с заглавной буквы!")
    private String firstName;

    @Size(max = 15, message = "Не знаю ни одного отчества длиннее 15 символов:(")
    @Pattern(regexp = "^[А-Я]{0,1}[а-я]*", message = "Отчество должно состоять только из русских букв " +
            "и начинаться с заглавной буквы!")
    private String lastName;

    @NotEmpty(message = "email не должен быть пустым!")
    @Email(message = "email не соответствует формату!")
    private String email;

    @NotEmpty(message = "Телефон не должен быть пустым!")
    @Pattern(regexp = "\\+7[0-9]{10}", message = "Телефон не соответствует формату (+7XXXXXXXXXX)!")
    private String phone;

    @NotEmpty(message = "Имя пользователя не должно быть пустым!")
    private String username;

    @NotEmpty(message = "Пароль не должен быть пустым!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,32}$"
            , message = "Пароль должен содержать цифры, буквы в нижнем и верхнем регистре. Длина пароля 8-32 символов")
    private String password;

    @NotEmpty(message = "Повторный пароль не должен быть пустым!")
    private String passwordConfirm;
}
