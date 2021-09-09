package ru.mephi.spring.annotation;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
