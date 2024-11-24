package ru.nspk.task3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HomeWork3Application {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork3Application.class);
    }
}
