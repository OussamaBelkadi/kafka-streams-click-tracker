# 🖱️ Kafka Streams Click Tracker

This project is a real-time event-driven architecture using **Spring Boot**, **Apache Kafka**, and **Kafka Streams** to track and analyze user clicks.

---

## 🧩 Project Modules

1. **click-tracker-producer** – Web REST API that sends click events to Kafka (`clicks` topic)
2. **click-stream-processor** – Kafka Streams application that counts user clicks in real time
3. **click-count-rest-api** – REST API that consumes the counts from Kafka (`click-counts` topic)

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3.x
- Apache Kafka
- Kafka Streams
- Maven

---

## 📦 Topics

- `clicks` → Events produced on button click
- `click-counts` → Aggregated click counts per user

---

## 🚀 Example Usage

```bash
# Send a click
curl -X POST http://localhost:8080/api/click/user123

# Get total clicks
curl http://localhost:8082/clicks/count

# Get clicks for a user
curl http://localhost:8082/clicks/count/user123
