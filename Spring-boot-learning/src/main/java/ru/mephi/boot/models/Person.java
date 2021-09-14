package ru.mephi.boot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "people", schema = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person", columnDefinition = "serial")
    private long id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 10, message = "Name should be between 2 and 10")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 10, message = "Surname should be between 2 and 10")
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 200, message = "Age should be less than 200")
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;
}
