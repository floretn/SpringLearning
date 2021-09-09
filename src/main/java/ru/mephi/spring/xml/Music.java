package ru.mephi.spring.xml;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
