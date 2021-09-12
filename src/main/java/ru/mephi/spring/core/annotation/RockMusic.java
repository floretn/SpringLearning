package ru.mephi.spring.core.annotation;

import org.springframework.stereotype.Component;

@Component
public class RockMusic implements Music {
    @Override
    public void play() {
        System.out.println("Rock is playing");
    }
}