package ru.mephi.spring.annotation;

import org.springframework.stereotype.Component;

@Component
public class Jazz implements Music {
    @Override
    public void play() {
        System.out.println("Jazz is playing");
    }
}
