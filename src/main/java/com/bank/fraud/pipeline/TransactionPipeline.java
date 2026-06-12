package com.bank.fraud.pipeline;

import com.bank.fraud.model.TransactionEvent;
import com.bank.fraud.service.AlertService;
import com.bank.fraud.service.RiskScoringService;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.List;

public class TransactionPipeline {

    private final RiskScoringService riskScoringService = new RiskScoringService();
    private final AlertService alertService = new AlertService();

    public void startPipeline() {
        List<TransactionEvent> events = List.of(
            new TransactionEvent("ACC123", 60000, "USA", Instant.now()),
            new TransactionEvent("ACC456", 2000, "HOME_COUNTRY", Instant.now())
        );

        Flux.fromIterable(events)
            .flatMap(tx -> riskScoringService.score(tx)
                .map(score -> new Object[]{tx, score}))
            .filter(arr -> (double) arr[1] > 0.8)
            .doOnNext(arr -> alertService.sendAlert((TransactionEvent) arr[0], (double) arr[1]))
            .subscribe();
    }
}