package ru.mephi.spring.core.annotation;

import org.springframework.stereotype.Component;

@Component
public class ClassicMusic implements Music {
    @Override
    public void play() {
        System.out.println("Classic is playing");
    }
}
