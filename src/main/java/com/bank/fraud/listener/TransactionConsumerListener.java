package com.bank.fraud.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.bank.fraud.model.TransactionEvent;

@Service
public class TransactionConsumerListener {

    @KafkaListener(topics = "transactions", groupId = "fraud-detection-group")
    public void consume(TransactionEvent event) {
        System.out.println("Received transaction:==================== " + event);
        // Add fraud detection logic here
    }
}