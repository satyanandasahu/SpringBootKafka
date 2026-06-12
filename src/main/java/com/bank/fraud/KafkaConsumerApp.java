package com.bank.fraud;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.fraud.model.TransactionEvent;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerApp {

    public static void main(String[] args) throws Exception {
        // Kafka consumer configuration
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "fraud-detection-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to topic
        consumer.subscribe(Collections.singletonList("transactions"));

        // Jackson mapper with JavaTimeModule for Instant
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // auto-registers JSR310 module

        System.out.println("Listening for messages on 'transactions'...");

        // Poll loop
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                try {
                    // Deserialize JSON into TransactionEvent
                    TransactionEvent event = mapper.readValue(record.value(), TransactionEvent.class);
                    System.out.println("Received transaction: " + event);
                } catch (Exception e) {
                    System.err.println("Failed to deserialize: " + record.value());
                    e.printStackTrace();
                }
            }
            consumer.commitSync();
        }
    }
}
