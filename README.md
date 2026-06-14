# 🚀 SpringBootKafka

A sample application demonstrating how to **publish and consume messages** using **Spring Boot 4**, **Project Reactor**, and **Apache Kafka (KRaft mode)**.


## ⚙️ Prerequisites

- **Apache Kafka** installed and configured (KRaft mode).  
- Minimum Java version: Java 17, I have build it with Java 23+  
- Maven 4
- Spring Boot 4

---

## 🔧 Configuration

Update your `application.properties` with the required Kafka settings:

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=fraud-detection-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.value.default.type=com.bank.fraud.model.TransactionEvent
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer

fraud.kafka.topic=transactions
```

---

## 🛠️ Build & Run

1. **Build the JAR**  
   ```bash
   mvn clean install
   ```

2. **Locate deliverables**  
   The JAR file will be available in the `target/` folder.

3. **Start the application**  
   ```bash
   java -jar target/<appname>.jar
   ```

4. **Send a test request**  
   ```bash
   curl -X POST "http://localhost:8080/transactions/simulate?accountId=ACC80&amount=150000&location=US"
   ```

5. **Verify Kafka queue**  
   Check your Kafka topic to confirm the message was delivered.

---

## 📜 Useful Kafka Commands

Run these commands from the Kafka `bin/` directory (or add it to your PATH):

- **Create a Topic**  
  ```bash
  kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
  ```

- **List Topics**  
  ```bash
  kafka-topics.sh --list --bootstrap-server localhost:9092
  ```

- **Describe a Topic**  
  ```bash
  kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092
  ```

- **Produce Messages: Use the console producer to publish messages to a topic:**  
  ```bash
  kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092
  ```
  Type messages and press **Enter** to send.

- **Consume Messages: Use the console consumer to read messages from a topic:**  
  ```bash
  kafka-console-consumer.sh --topic my-topic --from-beginning --bootstrap-server localhost:9092
  ```

---

## ✅ Key Notes
- This project uses **Kafka KRaft mode** (no ZooKeeper).  
---
