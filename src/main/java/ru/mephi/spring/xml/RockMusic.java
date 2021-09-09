package ru.mephi.spring.xml;

public class RockMusic implements Music {
    @Override
    public void play() {
        System.out.println("Rock is playing");
    }
}
