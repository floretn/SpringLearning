package ru.mephi.spring.java_code;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Computer computer = context.getBean("computerBean1", Computer.class);
        computer.playMusic();

        System.out.println(computer.getMusicPlayer().getName());

        computer = context.getBean("computer", Computer.class);
        computer.playMusic();

        context.close();
    }
}
