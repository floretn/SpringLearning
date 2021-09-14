package ru.mephi.spring.mvc.java_code.rest.models;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10")
    private String name;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 10, message = "Surname should be between 2 and 10")
    private String surname;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 200, message = "Age should be less than 200")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Email should be valid")
    private String email;
}
