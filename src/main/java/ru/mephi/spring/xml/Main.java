package ru.mephi.spring.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        MusicPlayer musicPlayer = new MusicPlayer(context.getBean("jazzBean", Jazz.class));
        musicPlayer.playMusic();

        musicPlayer = new MusicPlayer(context.getBean("classicMusicBean", ClassicMusic.class));
        musicPlayer.playMusic();

        System.out.println("****************************");
        musicPlayer = context.getBean("musicPlayerBean", MusicPlayer.class);
        System.out.println("****************************");
        musicPlayer.playMusic();
        musicPlayer.getMusicNext().play();

        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        System.out.println("****************************");
        MusicPlayer musicPlayer1 = context.getBean("musicPlayerBean", MusicPlayer.class);
        System.out.println("****************************");

        musicPlayer1.getMusicList().remove(1);

        musicPlayer.playAll();
        System.out.println("*****");
        musicPlayer1.playAll();

        System.out.println("****************************");

        context.close();
    }
}
