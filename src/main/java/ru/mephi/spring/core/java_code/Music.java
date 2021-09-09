package ru.mephi.spring.core.java_code;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
