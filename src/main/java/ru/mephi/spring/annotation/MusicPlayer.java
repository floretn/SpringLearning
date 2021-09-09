package ru.mephi.spring.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MusicPlayer {

    private Music music;

    @Autowired
    @Qualifier("jazz")
    private Music musicNext;

    private Music musicNextNext;

    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    @Autowired
    public MusicPlayer(@Qualifier("classicMusic") Music music) {
        this.music = music;
    }

    public Music getMusicNext() {
        return musicNext;
    }

    public void setMusicNext(Music musicNext) {
        this.musicNext = musicNext;
    }

    public Music getMusicNextNext() {
        return musicNextNext;
    }

    @Autowired
    @Qualifier("rockMusic")
    public void setMusicNextNext(Music musicNextNext) {
        this.musicNextNext = musicNextNext;
    }

    @PostConstruct
    @PreDestroy
    public void playMusic() {
        music.play();
        musicNext.play();
        musicNextNext.play();
    }

    @PostConstruct
    private void init() {
        System.out.println("Init");
    }

    @PostConstruct
    private void zinit() {
        System.out.println("Zinit");
    }

    @PreDestroy
    void destroy() {
        System.out.println("Destroy");
    }

    @Override
    public String toString() {
        return "MusicPlayer{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}
