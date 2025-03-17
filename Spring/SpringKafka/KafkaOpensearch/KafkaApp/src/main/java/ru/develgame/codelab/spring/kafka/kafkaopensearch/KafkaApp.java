package ru.develgame.codelab.spring.kafka.kafkaopensearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.develgame.codelab.spring.kafka.kafkaopensearch.workers.MessageScheduler;

@EnableScheduling
@SpringBootApplication
public class KafkaApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApp.class, args);
        try {
            context.getBean(MessageScheduler.class).sendMessage();
        } catch (JsonProcessingException e) {


        }
    }
}
