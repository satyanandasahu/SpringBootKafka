package com.bank.fraud.controller;

import com.bank.fraud.model.TransactionEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionController(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Endpoint to simulate sending a transaction to Kafka
    @PostMapping("/simulate")
    public String simulateTransaction(@RequestParam String accountId,
                                      @RequestParam double amount,
                                      @RequestParam(defaultValue = "HOME_COUNTRY") String location) {
        TransactionEvent tx = new TransactionEvent(accountId, amount, location, Instant.now());
        kafkaTemplate.send("transactions", tx);
        return "Transaction sent: " + tx.toString();
    }
}