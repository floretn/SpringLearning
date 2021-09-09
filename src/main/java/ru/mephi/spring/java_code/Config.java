package ru.mephi.spring.java_code;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("ru.mephi.spring.java_code")
@PropertySource("classpath:musicPlayer.properties")
public class Config {

    @Bean
    public ClassicMusic classicMusicBean1() {
        return new ClassicMusic();
    }

    @Bean
    public MusicPlayer musicPlayer1() {
        return new MusicPlayer(classicMusicBean1());
    }

    @Bean
    @Scope("prototype")
    public Computer computerBean1() {
        return new Computer();
    }
}
