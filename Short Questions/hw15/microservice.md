# Microservice Architecture Documentation

## Overview

**Microservice architecture** is a design style where an application is composed of **small, independent services**, each responsible for a specific business capability.

Each microservice:

* Runs in its own **process**
* Has its own **database**
* Communicates with others over **lightweight protocols** (typically HTTP/REST or gRPC)
* Is developed, deployed, and scaled **independently**

---

## Core Components

### 1. Microservices (Business Services)

* Small, focused services that each perform a **specific business function**
* Example: `User Service`, `Order Service`, `Payment Service`, `Notification Service`

### 2. API Gateway

* **Single entry point** for all external clients
* Handles:

  * Request routing to correct service
  * Authentication & authorization
  * Load balancing
  * Rate limiting
  * Logging
* Example tools: **Kong**, **AWS API Gateway**, **NGINX**, **Apigee**

### 3. Service Discovery

* Allows services to **find and communicate** with each other dynamically
* Useful in **containerized environments** where service IPs change frequently
* Two types:

  * **Client-side discovery** (e.g., Netflix Eureka)
  * **Server-side discovery** (e.g., AWS ALB, Consul)

### 4. Database per Service

* Each microservice owns its **own database**
* Prevents tight coupling via shared schema
* Allows services to choose **best-fit database technology**
* Requires **eventual consistency** for cross-service operations

### 5. Inter-Service Communication

* **Synchronous**: RESTful APIs, gRPC
* **Asynchronous**: Messaging queues (RabbitMQ, Kafka), Pub/Sub systems
* Enables services to **decouple** and stay resilient to failures

### 6. Centralized Configuration

* Externalizes service configs (e.g., DB credentials, feature toggles)
* Example tools: **Spring Cloud Config**, **Consul**, **AWS Parameter Store**

### 7. Security

* API Gateway handles **authentication (AuthN)** and **authorization (AuthZ)**
* Token-based security (e.g., **JWT**, OAuth2)
* Each service should **verify tokens** for internal calls (Zero Trust)

### 8. Monitoring & Logging

* Centralized logging (e.g., **ELK Stack**, **Fluentd**)
* Monitoring & alerting (e.g., **Prometheus**, **Grafana**, **Datadog**)
* Distributed tracing (e.g., **Jaeger**, **Zipkin**) for tracking cross-service requests

### 9. CI/CD Pipeline

* Automates **building, testing, and deploying** each service independently
* Supports **containerization** (Docker) and **orchestration** (Kubernetes)


