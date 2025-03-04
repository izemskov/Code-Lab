package ru.develgame.codelab.spring.data.mongodb.upsert.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "mongodb")
public record MongoProperties(String user,
                              String pass,
                              @NotNull String host,
                              @NotNull String database,
                              String extraArgs) {
}
