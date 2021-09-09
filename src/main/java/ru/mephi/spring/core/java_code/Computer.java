package ru.mephi.spring.core.java_code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Computer {

    @Autowired
    private MusicPlayer musicPlayer;

    public MusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public void playMusic() {
        musicPlayer.playMusic();
    }
}
