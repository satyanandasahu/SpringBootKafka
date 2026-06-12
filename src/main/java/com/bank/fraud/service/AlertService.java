package com.bank.fraud.service;

import com.bank.fraud.model.TransactionEvent;

public class AlertService {
    public void sendAlert(TransactionEvent tx, double score) {
        System.out.println("ALERT: Fraud suspected for account " 
            + tx.getAccountId() + " with score " + score);
    }
}