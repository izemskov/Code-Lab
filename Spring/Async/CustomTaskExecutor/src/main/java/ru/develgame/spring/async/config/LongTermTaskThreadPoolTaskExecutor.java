package ru.develgame.spring.async.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Component
public class LongTermTaskThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    public LongTermTaskThreadPoolTaskExecutor() {
        this.setCorePoolSize(10);
        this.setMaxPoolSize(100);
        this.setQueueCapacity(1000);
        this.setThreadNamePrefix("long-term-task-thread-");
        this.setAwaitTerminationSeconds(300);
        this.initialize();
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        String uuid = UUID.randomUUID().toString();

        Future<T> future = super.submit(wrapTask(task));

        return future;
    }

    private <T> Callable<T> wrapTask(Callable<T> task) {
        return () -> {

            T call = task.call();

            return call;
        };
    }
}
