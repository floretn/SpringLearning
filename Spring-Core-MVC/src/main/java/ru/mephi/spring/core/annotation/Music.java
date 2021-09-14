package ru.mephi.spring.core.annotation;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
