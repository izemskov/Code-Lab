package ru.develgame.codelab.spring.data.mongodb.ttl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import ru.develgame.codelab.spring.data.mongodb.ttl.service.MongoTTLIndexService;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MongoTTLApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MongoTTLApp.class, args);
        context.getBean(MongoTTLIndexService.class).createIndex();
    }
}
