package com.inmotion.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by takumiera on 2017/07/31.
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.inmotion.security")
public class Application {

    public static void main(String[] args) {
        log.info("start application");
        SpringApplication.run(Application.class, args);
    }

}
