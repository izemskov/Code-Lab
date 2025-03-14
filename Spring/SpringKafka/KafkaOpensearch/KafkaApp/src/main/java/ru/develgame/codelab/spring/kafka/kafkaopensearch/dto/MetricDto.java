package ru.develgame.codelab.spring.kafka.kafkaopensearch.dto;

import java.util.Date;

public record MetricDto(Date currentDate, int cpuUsage) {
}
