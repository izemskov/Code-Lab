package ru.develgame.spring.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class CustomThreadExecutor {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CustomThreadExecutor.class, args);
        context.getBean(JobService.class).longTermJob();
        System.out.println("Finish");
    }
}
