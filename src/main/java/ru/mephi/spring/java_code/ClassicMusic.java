package ru.mephi.spring.java_code;

import org.springframework.stereotype.Component;

@Component
public class ClassicMusic implements Music {
    @Override
    public void play() {
        System.out.println("Classic is playing");
    }
}
