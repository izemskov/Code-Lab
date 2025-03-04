package ru.develgame.codelab.spring.data.mongodb.ttl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TemporaryDto(@JsonProperty("id") String id,
                           @JsonProperty("name") String name) {
}
