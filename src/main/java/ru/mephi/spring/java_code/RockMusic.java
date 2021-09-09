package ru.mephi.spring.java_code;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
    @Override
    public void play() {
        System.out.println("Rock is playing");
    }
}
