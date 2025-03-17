package ru.develgame.codelab.spring.kafka.kafkaopensearch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CpuMetric(@JsonProperty("id") int id, @JsonProperty("cpu") int cpu) {
}
