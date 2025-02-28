package ru.develgame.spring.async.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import ru.develgame.spring.async.bean.LongTermTask;

import java.util.UUID;

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

    public LongTermTask executeTask(Runnable task) {
        LongTermTask longTermTask = new LongTermTask(UUID.randomUUID().toString(), 0);
        super.execute(wrapTask(task, longTermTask));
        return longTermTask;
    }

    private Runnable wrapTask(Runnable task, LongTermTask longTermTask) {
        return () -> {
            try {
                task.run();
            } finally {
                longTermTask.setStatus(1);
            }
        };
    }
}
