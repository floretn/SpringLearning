package ru.mephi.spring.core.annotation;

import org.springframework.stereotype.Component;

@Component
public class Jazz implements Music {
    @Override
    public void play() {
        System.out.println("Jazz is playing");
    }
}