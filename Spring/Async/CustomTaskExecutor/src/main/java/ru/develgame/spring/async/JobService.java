package ru.develgame.spring.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Async("longTermTaskThreadPoolTaskExecutor")
    public void longTermJob() {
        System.out.println("Long term jon start " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println("Long term job stop" + Thread.currentThread().getName());
    }
}
