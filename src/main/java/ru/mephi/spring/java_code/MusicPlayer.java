package ru.mephi.spring.java_code;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Autowired
    @Qualifier("rockMusic")
    public void setMusicNextNext(Music musicNextNext) {
        this.musicNextNext = musicNextNext;
    }

    public void playMusic() {
        music.play();
        musicNext.play();
        musicNextNext.play();
    }

    @Override
    public String toString() {
        return "MusicPlayer{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}
