package com.bank.fraud.service;

import com.bank.fraud.model.TransactionEvent;
import reactor.core.publisher.Mono;

public class RiskScoringService {
    public Mono<Double> score(TransactionEvent tx) {
        double score = 0.0;
        if (tx.getAmount() > 50000) score += 0.5;
        if (!"HOME_COUNTRY".equals(tx.getLocation())) score += 0.5;
        return Mono.just(score);
    }
}