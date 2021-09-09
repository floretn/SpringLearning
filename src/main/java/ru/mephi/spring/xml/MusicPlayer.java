package ru.mephi.spring.xml;

import java.util.List;

public class MusicPlayer {

    private Music music;
    private Music musicNext;
    private List<Music> musicList;

    private String name;
    private int volume;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public Music getMusicNext() {
        return musicNext;
    }

    public void setMusicNext(Music musicNext) {
        this.musicNext = musicNext;
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

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public List<Music> getMusicList() {
        return musicList;
    }

    public void playMusic() {
        music.play();
    }

    public void playAll() {
        for (Music m : musicList) {
            m.play();
        }
    }

    public void init() {
        playMusic();
    }

    public void destroy() {
        playMusic();
        musicNext.play();
        playAll();
    }
}
