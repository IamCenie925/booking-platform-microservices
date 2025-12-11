# 🚀 Sports Facility Booking Platform - Microservices 

<p align="left">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
  
  ![Microservices](https://img.shields.io/badge/Microservices-FF6A00?style=for-the-badge&logo=googlecloud&logoColor=white) 
  ![System-Oriented Design](https://img.shields.io/badge/System%20Design-333333?style=for-the-badge&logo=buffer&logoColor=white)
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git"/>
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub"/>

---

🗓️ **Timeline:** November 2025

👤 **Author:** Nguyen Thi Xuan Nhi (aka Cenie)

---
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



