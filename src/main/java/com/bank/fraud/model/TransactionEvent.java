package com.bank.fraud.model;

import java.time.Instant;
import java.util.Objects;

public class TransactionEvent {
	private String accountId;
	private double amount;
	private String location;
	private Instant timestamp;

	public TransactionEvent() {
	}

	public TransactionEvent(String accountId, double amount, String location, Instant timestamp) {
		this.accountId = accountId;
		this.amount = amount;
		this.location = location;
		this.timestamp = timestamp;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TransactionEvent{" + "accountId='" + accountId + '\'' + ", amount=" + amount + ", location='" + location
				+ '\'' + ", timestamp=" + timestamp + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TransactionEvent))
			return false;
		TransactionEvent that = (TransactionEvent) o;
		return Double.compare(that.amount, amount) == 0 && Objects.equals(accountId, that.accountId)
				&& Objects.equals(location, that.location) && Objects.equals(timestamp, that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, amount, location, timestamp);
	}
}