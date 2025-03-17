package ru.develgame.codelab.spring.kafka.kafkaopensearch.workers;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.develgame.codelab.spring.kafka.kafkaopensearch.dto.CpuMetric;

import java.lang.management.ManagementFactory;

@Component
public class MessageScheduler {
    @Autowired
    private KafkaTemplate<String, CpuMetric> kafkaTemplate;

    private int id;

    public static final String TOPIC_NAME_METRICS = "metrics_topic_opensearch_v2";

    @Scheduled(fixedDelay = 1000L)
    public void sendMessage() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        kafkaTemplate.send(TOPIC_NAME_METRICS, new CpuMetric(id, (int) (osBean.getCpuLoad() * 100)))
                .whenComplete((stringMetricDtoSendResult, throwable) ->
                        System.out.println(stringMetricDtoSendResult.getRecordMetadata().offset()));

        id++;
    }
}
