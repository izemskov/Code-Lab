package ru.develgame.spring.async.service;

import org.springframework.stereotype.Service;

@Service
public class JobService {
    public void longTermJob() {
        System.out.println("Long term jon start " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println("Long term job stop" + Thread.currentThread().getName());
    }
}
