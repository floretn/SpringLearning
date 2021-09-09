package ru.mephi.spring.xml;

public class Jazz implements Music{
    @Override
    public void play() {
        System.out.println("Jazz is playing");
    }

    public void destroy() {
        play();
        play();
        play();
        play();
    }
}
