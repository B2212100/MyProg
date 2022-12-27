package com.example.linebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// 追加 (botからの話しかけを自動化)
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //追加
public class LinebotApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinebotApplication.class, args);
    }

}
