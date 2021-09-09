package ru.mephi.spring.core.xml;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
