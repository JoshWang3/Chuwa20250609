# chuwa hw15

# Question 1

## 1. Monolithic Architecture

### Definition

A single unified application where all components (user interface, business logic, and data access) are part of one codebase and deployed as one unit.

### Structure

- One project, one codebase
- Single build artifact (like a `.jar` or `.war`)
- All modules tightly coupled and share memory

### Example

A Spring Boot application that handles users, authentication, products, and payments all in one deployable application.

### Pros

- Simple to develop and deploy at first
- Easy to test in a single environment
- No network communication between components

### Cons

- Difficult to scale specific components
- Changes in one module can affect the entire system
- Long build and deployment times as the app grows
- Difficult to adopt different technologies for different parts

---

## 2. Service-Oriented Architecture (SOA)

### Definition

A system composed of multiple services that provide distinct business functionalities. These services are loosely coupled and communicate through a central messaging layer, often an Enterprise Service Bus (ESB).

### Structure

- Multiple services performing business tasks
- Communication typically over HTTP using SOAP or XML
- Often depends on an ESB for orchestration and routing

### Example

An e-commerce platform with separate services for orders, payments, and shipping, all connected through an ESB like Apache Camel or MuleSoft.

### Pros

- Better separation of concerns than monolithic
- Services can be reused by other applications
- More scalable and maintainable than monolithic architecture

### Cons

- ESB can become a single point of failure or bottleneck
- Communication can be slow due to heavier protocols (SOAP/XML)
- More complexity compared to monolithic

---

## 3. Microservices Architecture

### Definition

A design approach where an application is broken down into many small, independent services. Each service runs in its own process and communicates with others through lightweight protocols like REST or message queues.

### Structure

- Each microservice is self-contained and focused on a single business function
- Has its own database and deployment pipeline
- Services are deployed independently, often containerized

### Example

An online store with:

- User Service (using MongoDB)
- Product Service (using PostgreSQL)
- Order Service (using RabbitMQ and Redis)
    
    Each service runs independently and communicates via HTTP or messaging.
    

### Pros

- Individual services can be scaled and deployed independently
- Teams can work on different services simultaneously
- Faults in one service do not crash the entire system
- Supports different languages and technologies for different services

### Cons

- Distributed system complexity (e.g., latency, service discovery, error handling)
- Requires strong DevOps practices and monitoring
- Testing and data consistency across services is harder

# Question 2

## 1. Definition

Microservice architecture is a design pattern in which a software application is composed of small, independently deployable services. Each service is scoped to a specific business function, runs in its own process, and communicates with other services using lightweight protocols like HTTP/REST or messaging queues.

---

## 2. Key Characteristics

- **Decentralized development**: Each team owns and develops one or more services.
- **Independent deployability**: Services can be deployed and scaled independently.
- **Technology heterogeneity**: Different services can use different tech stacks.
- **Fault isolation**: Failures are contained within individual services.
- **Continuous delivery**: Easier to apply CI/CD pipelines to individual services.

---

## 3. Core Components of Microservices Architecture

### 3.1. **Service Layer**

- Each microservice encapsulates a single business capability.
- Typically includes controller, service, and data access layers within itself.

Example:

- `UserService`: Manages user registration, login, profile
- `ProductService`: Handles product catalog
- `OrderService`: Manages order placement, tracking

---

### 3.2. **API Gateway**

- Acts as a single entry point for external clients.
- Routes requests to appropriate services.
- Handles cross-cutting concerns like:
    - Authentication/Authorization
    - Rate limiting
    - Caching
    - Load balancing

Examples: Netflix Zuul, Spring Cloud Gateway, Kong, NGINX

---

### 3.3. **Service Discovery**

- Enables dynamic location of services without hardcoded URLs.
- Helps services find and communicate with each other.
- Includes service registry and client-side or server-side discovery.

Examples: Eureka, Consul, Zookeeper

---

### 3.4. **Inter-Service Communication**

- **Synchronous**: REST, gRPC
- **Asynchronous**: Message queues (RabbitMQ, Kafka)

Use asynchronous messaging for decoupling and better fault tolerance.

---

### 3.5. **Database per Service**

- Each service owns its database (polyglot persistence).
- Ensures loose coupling and independent scalability.
- Prevents distributed transactions — use eventual consistency where needed.

---

### 3.6. **Centralized Configuration**

- Shared config managed from a central place.
- Helps manage environment-specific variables (dev, test, prod).

Examples: Spring Cloud Config, Consul Key/Value store

---

### 3.7. **Monitoring and Logging**

- Aggregated and centralized logs and metrics are essential.
- Helps diagnose issues in distributed systems.

Tools:

- **Logging**: ELK stack (Elasticsearch, Logstash, Kibana), Fluentd
- **Monitoring**: Prometheus, Grafana, Zipkin, Jaeger (for tracing)

---

### 3.8. **Security**

- Enforced at API Gateway and service levels.
- Often uses OAuth2, JWT for token-based security.

Tools: Keycloak, Spring Security, Okta

---

### 3.9. **CI/CD Pipeline**

- Enables automated build, test, and deploy for each service.
- Promotes fast delivery and rollback.

Tools: Jenkins, GitHub Actions, GitLab CI/CD, ArgoCD, Spinnaker

---

### 3.10. **Containerization and Orchestration**

- Services are containerized (Docker) and managed by orchestration platforms.

Tools:

- **Containerization**: Docker
- **Orchestration**: Kubernetes, Docker Swarm

# Question 3

Resilience patterns are **design strategies** used to build **fault-tolerant microservices** that can recover gracefully from failures such as timeouts, network issues, or downstream service crashes.

---

## 1. Common Resilience Patterns

| Pattern | Purpose |
| --- | --- |
| **Circuit Breaker** | Prevents a service from repeatedly trying to invoke a failing service |
| **Retry** | Automatically retries a failed request |
| **Timeout** | Defines maximum wait time for a response |
| **Bulkhead** | Isolates services/resources to prevent cascading failure |
| **Fallback** | Provides a default response when the main call fails |
| **Rate Limiting** | Prevents overloading by limiting the number of requests |

---

## 2. Circuit Breaker Pattern

### Problem:

When a service (say `UserService`) is down, repeatedly calling it can:

- Waste resources
- Cause cascading failures
- Slow down the system

### Solution:

Use a **Circuit Breaker** to stop trying after repeated failures and allow recovery after a timeout.

```java
@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getUserFallback")
    public String getUser() {
        return restTemplate.getForObject("http://user-service/users/1", String.class);
    }

    public String getUserFallback() {
        return "User service is currently unavailable. Please try again later.";
    }
}

```

# Question 4

Load balancing algorithms distribute traffic among multiple service instances or servers to optimize resource use, improve throughput, reduce latency, and ensure reliability.

| Algorithm | Description |  |
| --- | --- | --- |
| **Round Robin** | Requests are sent to servers in a circular order (1 → 2 → 3 → 1 → ...) |  |
| **Least Connections** | Sends traffic to the server with the fewest active connections |  |
| **Weighted Round Robin** | Like Round Robin, but favors servers with higher weights (capacity/power) |  |
| **Weighted Least Conn.** | Considers both weight and current load (fewer + more powerful = preferred) |  |
| **IP Hash** | Uses client IP hash to always route requests to the same server |  |
| **Random** | Picks a server at random (usually only used in simple or fallback scenarios) |  |
| **Consistent Hashing** | Ensures minimal re-routing of keys when servers are added or removed |  |
| **Resource-Based** | Routes traffic based on CPU, memory, or custom metrics |  |

# Question 5

### API Gateway: Definition and Purpose

An **API Gateway** is a **server that acts as a single entry point** into a system of microservices. It routes client requests to the appropriate backend services and handles **cross-cutting concerns** such as authentication, logging, rate limiting, caching, and load balancing.

---

## Why Use an API Gateway?

Without a gateway:

- Clients must call each microservice directly
- They need to know internal service URLs
- Each microservice must implement authentication, rate limiting, etc.

With an API gateway:

- Clients only call the gateway
- The gateway routes requests to services
- Shared logic is centralized in one place

---

## Core Responsibilities of an API Gateway

| Feature | Description |
| --- | --- |
| **Request Routing** | Routes incoming requests to the appropriate microservice |
| **Authentication & Authorization** | Validates tokens (e.g., JWT, OAuth2) and user access |
| **Rate Limiting** | Prevents overloading by limiting the number of requests per user/IP |
| **Load Balancing** | Distributes requests across service instances |
| **Caching** | Reduces backend load by caching frequent responses |
| **Request/Response Transformation** | Rewrites headers, URLs, or payloads |
| **Logging & Monitoring** | Captures request/response metrics and logs |
| **CORS Handling** | Adds CORS headers to allow cross-origin API access |
| **Static Content Serving** | Can serve static assets or documentation (e.g., Swagger UI) |

## Benefits of an API Gateway

| Benefit | Explanation |
| --- | --- |
| **Simplified Client Access** | Clients only deal with one endpoint |
| **Centralized Security** | Token validation, SSL termination, auth filtering |
| **Improved Observability** | Unified logging, metrics, and monitoring |
| **Service Decoupling** | Backend services can change independently |
| **Resilience** | Can add retries, circuit breakers, timeouts |

---

## Drawbacks / Considerations

- **Single point of failure** (mitigate via clustering)
- **Latency** from extra hop
- **Complexity** if not well designed

---

Let me know if you'd like:

- Hands-on code with Spring Cloud Gateway and JWT
- A comparison between API Gateway and Service Mesh
- How to set up rate limiting or CORS handling with a gateway

# Question 6

## 1. What Is Service Discovery?

**Service discovery** is the process by which services in a microservice architecture **automatically find and connect to each other** — without hardcoding IP addresses or URLs.

---

### ❓ Why is it needed?

In microservices:

- Services are **deployed dynamically** (e.g., in containers or cloud)
- IP addresses and ports **change frequently**
- There are **multiple instances** of the same service (for load balancing or failover)

Manual configuration becomes brittle and error-prone. Service discovery solves this.

---

## 2. What Is a Service Registry?

A **service registry** is the **database** or **central directory** that keeps track of all available service instances and their locations (host, port, status).

It is the **heart** of service discovery.

---

### Example

When a service like `UserService` starts up:

- It **registers** itself with the service registry (e.g., Eureka)
- The registry stores:
    
    ```
    sql
    CopyEdit
    user-service → 10.0.0.5:8081, 10.0.0.6:8081
    
    ```
    

When another service (`OrderService`) needs to call `UserService`, it:

- Asks the **registry** for available instances of `user-service`
- Picks one and calls it

---

## 3. Types of Service Discovery

### A. **Client-Side Discovery**

- The **client** (e.g., OrderService) queries the service registry directly and chooses an instance
- Requires client to implement logic for:
    - Service lookup
    - Load balancing

**Tools:** Eureka, Consul, Zookeeper

**Example:**

```
text
CopyEdit
OrderService → Eureka → user-service instance → HTTP call

```

---

### B. **Server-Side Discovery**

- The **API Gateway or load balancer** queries the service registry and forwards the request
- Clients don’t know or care where services live

**Tools:** Kubernetes + kube-proxy, AWS ALB, Istio

**Example:**

```
text
CopyEdit
Client → API Gateway → Service Registry → user-service instance

```

---

## 4. Popular Service Registry Tools

| Tool | Description |
| --- | --- |
| **Eureka** | Netflix OSS, popular with Spring Cloud |
| **Consul** | Lightweight, supports health checks, DNS |
| **Zookeeper** | Strong consistency, used by Apache systems |
| **etcd** | High-performance key-value store, used in Kubernetes |
| **Kubernetes DNS** | Built-in service discovery via DNS |

---

## 5. Summary

| Concept | Description |
| --- | --- |
| **Service Discovery** | Automatic detection of service locations |
| **Service Registry** | Central registry where services register and query |
| **Client-side discovery** | Client handles lookup and routing |
| **Server-side discovery** | Gateway/load balancer handles lookup and routing |

# Question 7

| Use Case | Module |
| --- | --- |
| Service Discovery | Eureka, Consul, Zookeeper, Kubernetes |
| API Gateway | Spring Cloud Gateway, Zuul (legacy) |
| Load Balancing | Spring Cloud LoadBalancer, Ribbon (legacy) |
| Circuit Breaker | Resilience4j, Hystrix (legacy) |
| Config Management | Spring Cloud Config Server |
| Distributed Tracing | Sleuth, Zipkin |
| Messaging / Events | Spring Cloud Stream (Kafka, Rabbit), Bus |

# Question 8

Already walk through