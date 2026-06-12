package com.bank.fraud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigValues {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${fraud.kafka.topic}")
	private String topic;

	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public String getTopic() {
		return topic;
	}

	public String getGroupId() {
		return groupId;
	}
}