### Short Questions

question1：
1. Monolithic Architecture
Definition:
A monolithic architecture is a single, unified application where all components (UI, business logic, database access, etc.) are tightly coupled and run as one process.

 Characteristics:
One codebase, one deployable unit (e.g., a single .jar, .war, or .exe)

Shared memory and resources

Easy to develop and test at early stages

 2. Service-Oriented Architecture (SOA)
 Definition:
SOA is an architectural style where business functionalities are broken into loosely coupled services that communicate over a network using standard protocols (like SOAP, WSDL, XML).

3. Microservices Architecture
Definition:
Microservices break an application into small, independently deployable services that:

Are organized around business capabilities

Communicate via lightweight protocols (usually REST or gRPC)

Have their own database and runtime

question2：
Microservices Architecture is a design approach in which a large application is broken down into independent, self-contained services, each responsible for a specific business functionality. These services communicate over lightweight protocols (e.g., HTTP/REST, gRPC) and can be developed, deployed, and scaled independently.

question3：
Resilience patterns are design strategies that help microservices systems:

Survive partial failures

Avoid cascading failures

Recover quickly from errors

A Circuit Breaker is a resilience pattern that prevents a failing service from being called repeatedly, allowing it time to recover.

Think of it like an electric circuit breaker: if a component fails, the breaker "trips" and stops the current (calls) from going through until it's safe again.

question4：
A load balancing algorithm determines how to choose which backend server should handle a given request.
1. Round Robin
Principle: Assign requests to servers in a rotating sequential order.
2. Weighted Round Robin
Principle: Similar to Round Robin, but gives priority to servers with higher capacity (weight).
3. Least Connections
 Principle: Assign request to the server with fewest active connections.
4. Least Response Time
 Principle: Choose the server with the lowest average response time.

question5：
An API Gateway is a single entry point for all client requests to a microservices system. It acts as a reverse proxy that routes requests, handles cross-cutting concerns, and simplifies client interaction.

question6：
Service Discovery allows microservices to find each other dynamically at runtime, without hardcoding IP addresses or URLs.
A Service Registry is a central database that keeps track of all running service instances, their IP addresses, ports, health status, and metadata.

question7：
Spring Cloud Module	Role / Purpose
Spring Cloud Config	Centralized configuration server for all services (supports Git, Vault, etc.)
Spring Cloud Netflix Eureka	Service registry and discovery (register and discover services dynamically)
Spring Cloud Gateway	API Gateway (routing, filters, authentication, etc.)
Spring Cloud LoadBalancer	Client-side load balancing (replacement for Netflix Ribbon)
Spring Cloud Circuit Breaker	Fault tolerance using circuit breaker abstraction (Resilience4j, etc.)
Spring Cloud OpenFeign	Declarative REST client (inter-service communication made easy)
Spring Cloud Bus	Broadcast config changes and events via messaging systems (e.g., RabbitMQ)
Spring Cloud Sleuth	Distributed tracing with correlation IDs (integrates with Zipkin/Jaeger)
Spring Cloud Stream	Messaging abstraction over Kafka, RabbitMQ, etc. for event-driven services
Spring Cloud Vault	Secure storage of secrets and credentials using HashiCorp Vault
Spring Cloud Zookeeper	Service discovery and configuration using Apache Zookeeper
Spring Cloud Consul	Service discovery and key-value configuration using Consul
Spring Cloud Kubernetes	Integrates Spring Cloud with Kubernetes service discovery and config maps
Spring Cloud Contract	Consumer-driven contract testing for service APIs
Spring Cloud Security	Secure microservices using OAuth2, JWT, etc.
Spring Cloud Function	Serverless and function-as-a-service (FaaS) model integration
