package ru.develgame.codelab.spring.kafka.kafkaopensearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MyMessage(@JsonProperty("id") int id, @JsonProperty("field1") String field1) {
}
