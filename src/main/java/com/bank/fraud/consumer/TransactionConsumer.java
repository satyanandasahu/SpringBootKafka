package com.bank.fraud.consumer;

import com.bank.fraud.model.TransactionEvent;
import com.bank.fraud.service.AlertService;
import com.bank.fraud.service.RiskScoringService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

    private final RiskScoringService riskScoringService = new RiskScoringService();
    private final AlertService alertService = new AlertService();

    @KafkaListener(topics = "${fraud.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(TransactionEvent event) {
        riskScoringService.score(event)
            .filter(score -> score > 0.8)
            .doOnNext(score -> alertService.sendAlert(event, score))
            .subscribe();
    }
}