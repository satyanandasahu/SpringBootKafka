package com.bank.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.bank.fraud.model.TransactionEvent;

@SpringBootApplication
public class FraudDetectionApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(FraudDetectionApplication.class, args);
	}
	
	@Service
	public class TransactionConsumer {
		@KafkaListener(topics = "transactions", groupId = "fraud-detection-group")
		public void consume(TransactionEvent event) {
			System.out.println("Received transaction: " + event);
		}
	}
	
}
