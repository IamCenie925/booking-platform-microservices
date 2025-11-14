# 🚀 Sports Facility Booking Platform - Microservices 

<p align="left">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5"/>
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3"/>
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript"/>
  <img src="https://img.shields.io/badge/AJAX-0071BC?style=for-the-badge&logo=ajax&logoColor=white" alt="AJAX"/>
  
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git"/>
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>
  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman"/>
  </p>

---

🗓️ **Timeline:** November 2025 – Present 
👤 **Author:** Nguyen Thi Xuan Nhi (aka Cenie)

This is a complete end-to-end microservices project for a facility booking system. It is built using Spring Boot & Spring Cloud, with Docker for infrastructure and RabbitMQ for asynchronous messaging.

This project demonstrates core microservice patterns:
* **Service Discovery** (Eureka)
* **API Gateway** (Spring Cloud Gateway)
* **Centralized Config** (via Gateway)
* **Synchronous** Service-to-Service calls (REST via `WebClient`)
* **Asynchronous** Communication (RabbitMQ for notifications)

---

## 🏛️ Architecture & Services

The system consists of 6 microservices and 4 infrastructure components.

### Infrastructure (via `docker-compose.yml`):
* **`pg_user` (Postgres):** Port `5433` - Database for User Service
* **`pg_facility` (Postgres):** Port `5434` - Database for Facility Service
* **`pg_booking` (Postgres):** Port `5435` - Database for Booking Service
* **`rabbitmq` (RabbitMQ):** Port `5672` (AMQP) & `15672` (Management UI)

### Application Services:
| Service | Port | Description |
| :--- | :---: | :--- |
| **`discovery-service`** | `8761` | **Eureka Server** for service registration and discovery. |
| **`gateway-service`** | `8080` | **Spring Cloud Gateway**. The single entry point for all client requests. |
| **`user-service`** | `8081` | Manages user accounts and authentication (demo token). |
| **`facility-service`** | `8082` | Manages facilities and available time slots. (Includes sample data seeder). |
| **`booking-service`** | `8083` | Handles the core booking logic, calling `user-service` and `facility-service`. |
| **`notification-service`**| `8084` | Listens to RabbitMQ for "booking created" events and simulates sending an email. |

---

## 🛠️ Prerequisites

To run this project, you will need:
* Java 17 (or 21)
* Maven 3.9+
* Docker & Docker Compose
* Git
* A `curl` client (like Postman or a terminal)

---

## 🏃‍♂️ How to Run
### Step 1: Start the Infrastructure
Make sure your Docker Desktop is running.
From the project root (booking-platform/):
docker compose up -d

Verify RabbitMQ: Open http://localhost:15672 (Login: guest / guest)

Verify Databases: Use a DB tool (DBeaver, pgAdmin) to connect to ports 5433, 5434, and 5435. (User: user, Pass: pass)

### Step 2: Run the Microservices
You must run all 6 Spring Boot applications (from your IDE or using ./mvnw spring-boot:run).

Recommended startup order:
1. discovery-service

2. gateway-service

3. user-service

4. facility-service (Console log will show Seeding data...)

5. booking-service

6. notification-service

### Step 3: Verify All Services
Open the Eureka Dashboard: http://localhost:8761

Wait until you see all 6 services (GATEWAY, USER-SERVICE, FACILITY-SERVICE, etc.) listed as UP.

