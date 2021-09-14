package ru.mephi.spring.core.xml;

public class ClassicMusic implements Music{

    private ClassicMusic(){}

    @Override
    public void play() {
        System.out.println("Classic is playing");
    }

    public static ClassicMusic factory() {
        System.out.println("--------------------------------------------");
        System.out.println("Factory method is working");
        System.out.println("--------------------------------------------");
        return new ClassicMusic();
    }
}
