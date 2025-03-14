package ru.develgame.springkafka.producer.workers;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.develgame.springkafka.producer.dto.MetricDto;

import java.lang.management.ManagementFactory;
import java.util.Date;

@Component
public class MessageScheduler {
    @Autowired
    private KafkaTemplate<String, MetricDto> kafkaTemplate;

    public static final String TOPIC_NAME_METRICS = "metricsTopic";

    @Scheduled(fixedDelay = 1000L)
    public void sendMessage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        MetricDto metricDto = new MetricDto(new Date(), (int) (osBean.getCpuLoad() * 100));

        kafkaTemplate.send(TOPIC_NAME_METRICS, metricDto).whenComplete((stringMetricDtoSendResult, throwable) ->
                System.out.println(stringMetricDtoSendResult.getRecordMetadata().offset()));
    }
}
