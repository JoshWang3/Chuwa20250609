# hw15 submission

## Q1: Explain monolithic architecture, service oriented architecture and Micro service architecture.

### Answer:
#### Monolithic Architecture:
A monolithic application is built as a single, unified unit. All components—UI, business logic, and data access—are tightly integrated and run as one service. While simple to develop and deploy initially, it becomes harder to scale and maintain as the application grows.

Example: A traditional Spring Boot application with all logic and controllers in one project packaged into a single WAR or JAR file.

#### Service-Oriented Architecture (SOA):
SOA structures an application as a collection of loosely coupled services that communicate over a network, often using protocols like SOAP. These services share common infrastructure like messaging systems and are often governed by an enterprise service bus (ESB).

Example: A legacy enterprise system where user management, payment, and notifications are separate services managed by an ESB.

#### Microservice Architecture:
Microservices break down an application into small, independently deployable services that focus on specific business capabilities. They usually communicate over lightweight protocols like REST or messaging queues and are independently developed, deployed, and scaled.

Example: In a Spring Boot ecosystem, separate microservices for user-service, order-service, and payment-service, each running in its own container (like Docker) and communicating via REST APIs.

## Q2: Document the microservice architeture and core components

### Answer:
Microservice Architecture is a design approach where a system is composed of multiple small, independent services, each responsible for a specific business capability. These services are loosely coupled, independently deployable, and communicate via lightweight protocols like HTTP (REST) or messaging queues (e.g., Kafka, RabbitMQ).

Each microservice in a Spring Boot system typically runs in its own process and maintains its own database (known as database per service pattern), promoting autonomy and scalability.

Core Components of Microservice Architecture: 

1. Service Discovery

Allows microservices to find and communicate with each other dynamically.

Example: Netflix Eureka, Consul, or Spring Cloud Discovery.

2. API Gateway

A single entry point that routes client requests to the appropriate backend services, handles authentication, rate limiting, and load balancing.

Example: Spring Cloud Gateway, Zuul, or Kong.

3. Configuration Management

Centralized management of environment-specific configs across all services.

Example: Spring Cloud Config Server.

4. Load Balancer

Distributes incoming traffic among instances of services to ensure high availability.

Example: Ribbon (client-side), Kubernetes services, or AWS ELB.

5. Inter-Service Communication

Enables services to communicate with each other, either synchronously (REST/gRPC) or asynchronously (Kafka/RabbitMQ).

Example: Spring Cloud OpenFeign for REST, or Spring Cloud Stream for messaging.

6. Centralized Logging

Aggregates logs from all services for easier monitoring and debugging.

Example: ELK Stack (Elasticsearch, Logstash, Kibana), or Fluentd with CloudWatch.

7. Monitoring and Tracing

Tracks service performance and traces requests across services.

Example: Prometheus + Grafana for metrics, and Zipkin or OpenTelemetry for tracing.

8. Security

Manages authentication and authorization across services.

Example: OAuth 2.0 with Spring Security and Keycloak or Okta.

9. CI/CD Pipeline

Automates building, testing, and deploying services independently.

Tools: Jenkins, GitHub Actions, GitLab CI, Docker, Kubernetes.

## Q3: Explain Resilience patterns? Explain circuit breaker with Spring Cloud Hystrix code example.

### Answer:
Resilience patterns are strategies used in microservices to ensure applications remain responsive and reliable even in the face of failures. These patterns help services handle latency, temporary failures, and system overloads gracefully.

#### Common Resilience Patterns:
Circuit Breaker – Prevents a system from repeatedly calling a failing service.

Retry – Automatically re-invokes failed operations after a delay.

Timeout – Limits the time a service waits for a response.

Bulkhead – Isolates failures to prevent cascading issues.

Fallback – Provides a default response or alternative flow when a service fails.

#### Circuit Breaker with Spring Cloud Hystrix
The circuit breaker monitors calls to a remote service. If the service fails repeatedly, the breaker "opens," and further calls are automatically failed (or redirected to fallback) without hitting the remote service. After a cooldown period, it attempts to close again.

Sample Code using Spring Cloud Hystrix
```java
@SpringBootApplication
@EnableCircuitBreaker  // Enable Hystrix Circuit Breaker
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```
```java
@RestController
public class OrderController {

    @Autowired
    private ProductService productService;

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        return productService.getProductInfo(id);
    }
}

```
```java
@Service
public class ProductService {

    @HystrixCommand(fallbackMethod = "fallbackProductInfo")
    public String getProductInfo(String productId) {
        // Simulate a failure
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Product service is down!");
        }
        return "Product info for: " + productId;
    }

    public String fallbackProductInfo(String productId) {
        return "Default product info (fallback) for: " + productId;
    }
}
```

How It Works:

If getProductInfo() throws an error or times out, Hystrix calls the fallbackProductInfo() method.

This protects the system from cascading failures and keeps the user experience stable.

## Q4: Explain load balancing algorithms.

### Answer:
Load balancing algorithms determine how incoming traffic or service requests are distributed across multiple servers or service instances. These algorithms help ensure high availability, scalability, and optimal resource utilization.

#### Common Load Balancing Algorithms:
1. Round Robin

Requests are distributed evenly across all servers in a circular order.

Example: First request to Server A, second to B, third to C, then back to A.

Used in: Spring Cloud LoadBalancer (default strategy), NGINX.

2. Random

Each request is sent to a randomly selected instance.

Simple, but can cause uneven distribution over time.

3. Weighted Round Robin

Similar to Round Robin, but servers with higher capacity get more requests.

Example: If Server A has weight 2 and B has weight 1, A gets 2 out of every 3 requests.

4. Least Connections

Sends requests to the server with the fewest active connections.

Useful when request load varies in processing time.

5. IP Hash / Sticky Sessions

Uses the client’s IP or session ID to consistently route them to the same server.

Helpful for maintaining session state (though not ideal for stateless microservices).

6. Response Time-Based

Directs traffic to the server with the lowest average response time.

## Q5: Explain API Gateway.

### Answer:
An API Gateway is a server that acts as a single entry point for all client requests to a system composed of multiple microservices. It handles request routing, composition, authentication, rate limiting, and other cross-cutting concerns.

Key Responsibilities of an API Gateway:

Request Routing

Forwards client requests to the appropriate backend microservice.

Authentication & Authorization

Verifies user credentials (e.g., JWT tokens) before forwarding requests.

Load Balancing

Distributes requests among service instances to improve availability.

Rate Limiting & Throttling

Controls how many requests a user can make to prevent abuse.

Request/Response Transformation

Modifies request headers, URLs, or payloads as needed.

Centralized Logging & Monitoring

Aggregates and tracks all traffic for observability.

## Q6: Explain service discovery and service registry

### Answer:
Service Discovery is a mechanism used in microservice architecture that allows services to automatically find and communicate with each other without hardcoding network locations.

To enable this, we use a Service Registry, which is a centralized database where all service instances register themselves upon startup and periodically update their status.

Key Concepts:

Service Registry

A central place where service instances (like user-service, order-service) register their host and port so that other services or API gateways can find them.

Example tools: Netflix Eureka, Consul, etcd.

Service Discovery

The process of looking up service locations in the registry. There are two main types:

Client-side discovery: The client queries the registry and decides which instance to call (e.g., Ribbon + Eureka).

Server-side discovery: The API Gateway queries the registry and forwards the request to the appropriate service (e.g., with Spring Cloud Gateway + Eureka).

## Q7: List Spring Cloud Modules that serve as Microservice components (e.g. Euerka for Service Discovery)

### Answer:
#### Spring Cloud Netflix Eureka

Purpose: Service Registry and Discovery

Services register themselves with Eureka; other services discover them dynamically.

#### Spring Cloud Config

Purpose: Centralized Configuration Management

Manages application settings across environments from a central Git-backed config server.

#### Spring Cloud Gateway

Purpose: API Gateway

Acts as a single entry point for all client requests, handling routing, filtering, authentication, etc.

#### Spring Cloud LoadBalancer (Replaces Ribbon)

Purpose: Client-side Load Balancing

Distributes requests among service instances dynamically.

#### Spring Cloud OpenFeign

Purpose: Declarative REST Client

Simplifies HTTP client code and supports load balancing and fallback mechanisms.

#### Spring Cloud Sleuth

Purpose: Distributed Tracing

Adds trace and span IDs to logs for request tracking across microservices.

#### Spring Cloud Zipkin

Purpose: Distributed Tracing Backend

Collects and visualizes traces from Sleuth-enabled services.

#### Spring Cloud Bus

Purpose: Event Propagation

Broadcasts config changes or events across multiple services using a message broker (like RabbitMQ or Kafka).

#### Spring Cloud Circuit Breaker (uses Resilience4j under the hood)

Purpose: Resilience / Fault Tolerance

Provides circuit breaker and fallback mechanisms.

#### Spring Cloud Stream

Purpose: Event-Driven Architecture / Messaging

Connects microservices via messaging platforms like Kafka or RabbitMQ.



