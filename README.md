<div align="center">

# 🎟️ TicketSphere

**A Microservices-based Ticket Booking System built with Spring Boot, Kafka, and Docker**

![Java](https://img.shields.io/badge/Java-21-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=spring-boot&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Event%20Driven-black?logo=apache-kafka&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized-blue?logo=docker&logoColor=white)

![Microservices](https://img.shields.io/badge/Microservices-Architecture-success)
![REST APIs](https://img.shields.io/badge/REST-APIs-blue)
![Flyway](https://img.shields.io/badge/Flyway-DB%20Migrations-red)
![OpenAPI](https://img.shields.io/badge/OpenAPI-Swagger-green)

</div>

---

## 🚀 Overview

**TicketSphere** is a backend-focused microservices application that simulates a real-world event ticket booking platform. It demonstrates clean architecture principles, event-driven design, and modern Java best practices.

This project showcases:
- Microservice separation of concerns
- Event-driven communication using Apache Kafka
- Synchronous & asynchronous service interaction
- Database versioning with Flyway
- API documentation with Swagger/OpenAPI
- Containerized infrastructure using Docker Compose

Perfect for learning, technical interviews, and portfolio demonstration.

---

## 🧱 Architecture Overview

```
┌─────────────┐
│   Client    │
└──────┬──────┘
       │
       ▼
┌─────────────────────────┐
│  Booking Service (8081) │
└──────┬──────────────────┘
       │ (Sync REST Call)
       ▼
┌──────────────────────────┐
│ Inventory Service (8080) │
└──────┬───────────────────┘
       │ (Publish Event)
       ▼
┌──────────────────────────┐
│   Kafka Topic            │
└──────┬───────────────────┘
       │ (Consume Event)
       ▼
┌──────────────────────────┐
│  Order Service           │
│  (Internal, Async)       │
└──────────────────────────┘
```

### Core Design Principles

- **Booking Service** handles user requests and orchestrates the booking flow
- **Inventory Service** manages events, venues, and ticket capacity
- **Order Service** consumes events asynchronously and persists order data
- **Kafka** ensures decoupling, scalability, and eventual consistency

---

## 🧩 Microservices Breakdown

### Booking Service

**Port:** `8081`

Responsibilities:
- Accept booking requests from clients
- Validate customer and inventory availability
- Calculate total price
- Publish booking events to Kafka for async processing

**API Documentation:** [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

**Endpoints:**
```http
POST /api/v1/booking
```

---

### Inventory Service

**Port:** `8080`

Responsibilities:
- Manage venues and events
- Track ticket capacity and availability
- Expose inventory read/write APIs
- Update ticket capacity after successful bookings

**API Documentation:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

**Endpoints:**
```http
GET  /api/v1/inventory/events
GET  /api/v1/inventory/event/{eventId}
GET  /api/v1/inventory/venue/{venueId}
PUT  /api/v1/inventory/event/{eventId}/capacity/{capacity}
```

---

### Order Service

**Role:** Internal, Event-Driven

Responsibilities:
- Consume booking events from Kafka
- Persist orders to the database
- Trigger inventory updates
- Handle failed order processing with retry logic

**Note:** This service has no public REST APIs—it operates fully asynchronously through Kafka events.

---

## 📦 Technology Stack

| Layer        | Technology                 |
|--------------|---------------------------|
| Language     | Java 21                   |
| Framework    | Spring Boot 3.x           |
| Messaging    | Apache Kafka              |
| Database     | MySQL 8.0                 |
| Migrations   | Flyway                    |
| API Docs     | Springdoc OpenAPI/Swagger |
| Containers   | Docker & Docker Compose   |
| Build Tool   | Maven                     |

---

## 🗄️ Database & Migrations

The project uses **MySQL** for data persistence and **Flyway** for schema versioning, ensuring consistency and reproducibility.

### Migration Files

```
V1__create_venue_and_event_tables.sql
V2__add_ticket_price.sql
V3__create_customer_table.sql
V4__create_order_table.sql
```

**Benefits:**
- ✔ Version-controlled schema changes
- ✔ Repeatable and consistent deployments
- ✔ Automatic migration on application startup
- ✔ Easy rollback capabilities

---

## 🔄 Kafka Event Flow

**Booking Event Message (JSON):**
```json
{
  "userId": 1,
  "eventId": 2,
  "ticketCount": 3,
  "totalPrice": 150.00,
  "timestamp": "2025-12-24T10:30:00Z"
}
```

**Why Kafka?**
- High throughput and low latency
- Loose coupling between services
- Guaranteed event ordering
- Natural fit for event-driven architecture
- Scales independently under load

---

## 🐳 Docker Setup

All infrastructure runs locally using **Docker Compose**, eliminating dependency installation hassles.

**Services:**
- MySQL 8.0
- Apache Kafka
- Zookeeper
- Kafka UI (optional monitoring)

### Start Infrastructure

```bash
docker-compose up -d
```

### Verify Services

```bash
docker-compose ps
```

### Stop All Services

```bash
docker-compose down
```

---

## 🧪 API Testing via Swagger

Each microservice exposes interactive API documentation:

| Service   | Swagger UI                                        |
|-----------|--------------------------------------------------|
| Booking   | http://localhost:8081/swagger-ui.html            |
| Inventory | http://localhost:8080/swagger-ui.html            |

Test endpoints directly from the browser without external tools like Postman.

---

## 📁 Project Structure

```
TicketSphere/
├── booking-service/
│   ├── src/main/java/...
│   ├── src/main/resources/
│   └── pom.xml
├── inventory-service/
│   ├── src/main/java/...
│   ├── src/main/resources/
│   └── pom.xml
├── order-service/
│   ├── src/main/java/...
│   ├── src/main/resources/
│   └── pom.xml
├── docker-compose.yml
└── README.md
```

---

## ⚙️ How to Run Locally

### Prerequisites

- Java 21+
- Docker & Docker Compose
- Maven 3.8+
- Git

### Setup Steps

**1. Clone the repository:**
```bash
git clone https://github.com/khan-sk-dev/TicketSphere.git
cd TicketSphere
```

**2. Start infrastructure:**
```bash
docker-compose up -d
```

**3. Build and run services:**
```bash
# Terminal 1: Inventory Service
cd inventory-service
mvn clean install
mvn spring-boot:run

# Terminal 2: Booking Service
cd booking-service
mvn clean install
mvn spring-boot:run

# Terminal 3: Order Service
cd order-service
mvn clean install
mvn spring-boot:run
```

**4. Verify Services:**
- Inventory: http://localhost:8080/swagger-ui.html
- Booking: http://localhost:8081/swagger-ui.html

---

## 🧠 Learning Outcomes

After exploring this project, you'll understand:

- ✅ Microservices design patterns and principles
- ✅ Synchronous vs asynchronous communication
- ✅ Kafka producer/consumer patterns
- ✅ Database migration strategies
- ✅ RESTful API design best practices
- ✅ OpenAPI/Swagger documentation
- ✅ Docker containerization and orchestration
- ✅ Separation of concerns and modular architecture

---

## 🚧 Potential Enhancements

- **API Gateway** for unified service access
- **Authentication & Authorization** (JWT/OAuth2)
- **Inventory Locking** to prevent overbooking
- **Idempotency Handling** for duplicate requests
- **Kafka Dead Letter Queue** for failed events
- **Shared Event Contracts** for Kafka messages
- **Observability** (Prometheus/Grafana/ELK Stack)
- **Circuit Breaker** pattern for fault tolerance
- **Caching Layer** (Redis) for performance
- **Unit & Integration Tests** with high coverage

---

## 🤝 Contributing

Contributions are welcome! Feel free to:
- Report bugs
- Suggest improvements
- Submit pull requests
- Share feedback

---

## ⭐ Summary

**TicketSphere** is a clean, scalable, and well-structured backend project that demonstrates real-world microservices architecture and modern Java best practices. It's an excellent reference for learning distributed systems design and preparing for technical interviews.

---

<div align="center">

**Made with ❤️ for backend engineering enthusiasts**

</div>
