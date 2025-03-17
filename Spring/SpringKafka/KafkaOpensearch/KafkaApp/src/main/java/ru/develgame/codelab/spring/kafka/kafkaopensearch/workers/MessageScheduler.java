package ru.develgame.codelab.spring.kafka.kafkaopensearch.workers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.develgame.codelab.spring.kafka.kafkaopensearch.dto.MyMessage;

import java.lang.management.ManagementFactory;

@Component
public class MessageScheduler {
    @Autowired
    private KafkaTemplate<String, MyMessage> kafkaTemplate;

    public static final String TOPIC_NAME_METRICS = "metrics_topic_opensearch_v1";

   // @Scheduled(fixedDelay = 1000L)
    public void sendMessage() throws JsonProcessingException {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        kafkaTemplate.send(TOPIC_NAME_METRICS, new MyMessage(1, "value1"))
                .whenComplete((stringMetricDtoSendResult, throwable) ->
                        System.out.println(stringMetricDtoSendResult.getRecordMetadata().offset()));
    }
}
