package ru.develgame.spring.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.develgame.spring.async.bean.LongTermTask;
import ru.develgame.spring.async.config.LongTermTaskThreadPoolTaskExecutor;
import ru.develgame.spring.async.service.JobService;

@SpringBootApplication
public class CustomThreadExecutor {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CustomThreadExecutor.class, args);
        LongTermTaskThreadPoolTaskExecutor taskExecutor = context.getBean(LongTermTaskThreadPoolTaskExecutor.class);
        JobService jobService = context.getBean(JobService.class);
        LongTermTask longTermTask = taskExecutor.executeTask(() -> jobService.longTermJob());
        System.out.println("Started");
        while (longTermTask.getStatus() != 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Finished");
    }
}
