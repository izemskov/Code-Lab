package ru.develgame.codelab.spring.data.mongodb.upsert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MongoUpsertApp {
    public static void main(String[] args) {
        SpringApplication.run(MongoUpsertApp.class, args);
    }
}
