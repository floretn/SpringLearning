package ru.nspk.task2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HomeWork2Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork2Application.class);
    }
}
