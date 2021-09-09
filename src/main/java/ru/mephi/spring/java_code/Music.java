package ru.mephi.spring.java_code;

public interface Music {
    default void play() {
        System.out.println("Music is playing");
    };
}
