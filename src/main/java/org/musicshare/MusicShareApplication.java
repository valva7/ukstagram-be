package org.musicshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MusicShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicShareApplication.class, args);
    }

}
