package ru.mephi.spring.core.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("newApplicationContext.xml");

        Computer computer = context.getBean("computer", Computer.class);
        computer.playMusic();

        System.out.println(computer.getMusicPlayer());

        System.out.println(computer == context.getBean("computer", Computer.class));

        context.close();
    }
}
