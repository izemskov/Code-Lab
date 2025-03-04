package ru.develgame.codelab.spring.data.mongodb.upsert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TestDto(@JsonProperty("name") String name) {
}
