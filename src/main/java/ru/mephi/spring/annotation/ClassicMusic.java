package ru.mephi.spring.annotation;

import org.springframework.stereotype.Component;

@Component
public class ClassicMusic implements Music {
    @Override
    public void play() {
        System.out.println("Classic is playing");
    }
}
