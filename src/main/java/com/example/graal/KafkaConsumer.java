package com.example.graal;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    public CountDownLatch getLatch() {
        return latch;
    }

    private CountDownLatch latch = new CountDownLatch(1);
    private String payload;

    @KafkaListener(topics = "${test.topic}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        LOGGER.info("received payload='{}'", consumerRecord.toString());
        payload = consumerRecord.toString();
        latch.countDown();
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    // other getters
}