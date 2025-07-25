1. Explain monolithic architecture, service oriented architecture and Micro service architecture.

**Monolithic Architecture:** Monolithic architecture is a single unified codebase where all components of an application (UI, business logic, data access, etc.) are tightly coupled and run as one unit.

**Characteristics:**

* All functions are in one executable or deployable file.

* Changes in one part often require redeploying the entire application.

* Typically used in early stages of development due to simplicity.

**Pros:**

* Easy to develop and test initially.

* Simple deployment.

* Suitable for small applications.

**Cons:**

* Poor scalability (horizontal scaling is harder).

* Difficult to maintain as codebase grows.

* One bug can affect the entire system.

* Longer development and deployment cycles for changes.

**Service-Oriented Architecture (SOA):** SOA is an architectural pattern where the application is composed of loosely coupled services, which communicate over a network using standardized protocols (like SOAP or REST).

**Characteristics:**

* Each service represents a business function.

* Uses Enterprise Service Bus (ESB) for communication.

* Services may share a common database.

**Pros:**

* Better modularity than monoliths.

* Can reuse services across applications.

* Centralized governance and security.

**Cons:**

* Heavier communication overhead (especially due to ESB).

* Complex to set up and maintain.

* Tightly coupled to specific protocols (e.g., XML/SOAP).

**Microservices Architecture:** Microservices architecture breaks down an application into small, independently deployable services that communicate over lightweight protocols (typically HTTP/REST or messaging queues).

**Characteristics:**

* Each microservice owns its own codebase, database, and deployment.

* Services are organized around business capabilities.

* Independent scaling, deployment, and development.

**Pros:**

* High scalability and flexibility.

* Easier to deploy and update individual services.

* Teams can work independently on different services.

* Fault isolation – failure in one service doesn’t crash the whole system.

**Cons:**

* Operational complexity (monitoring, logging, deployment).

* Requires DevOps maturity.

* Distributed system issues: network latency, consistency, etc.

* More complex testing.

2. Document the microservice architeture and core components

**Microservices Architecture:** Microservices Architecture is a software design pattern where a large application is composed of small, independent services, each responsible for a specific business functionality. These services are loosely coupled, independently deployable, and communicate via lightweight protocols (like HTTP/REST, gRPC, or message queues).

**Key components:**

**Microservices:** Independently deployable services that encapsulate specific business logic.

**API Gateway:** A single entry point for clients to access various microservices.

**Service Registry and Discovery:** Keeps track of all active service instances and their locations.

**Load Balancer:** Distributes network traffic across multiple instances of a service.

**Configuration Server:** Centralized service for externalizing application configurations.

**Database per Service:** Each service manages its own database to avoid tight coupling.

**Messaging System / Event Bus:** Enables asynchronous communication between services.

**Service Mesh (Optional, for large scale):** Infrastructure layer for managing service-to-service communication.

**Monitoring and Logging:** Track the health and performance of microservices.

**CI/CD Pipeline:** Automate build, test, and deployment of each service.

**Security:** Authentication (e.g., JWT, OAuth 2.0), Authorization (Role-based, Attribute-based), API Gateway security (rate limiting, IP whitelisting)

3. Explain Resilience patterns? Explain circuit breaker with Spring Cloud Hystrix code example.

**Resilience Patterns:** In microservices architecture, resilience patterns are design strategies used to handle failures gracefully, ensuring the system remains responsive and robust even when parts of it fail. Common Resilience Patterns include circuit breaker, retry, timeout, bulkhead,fallback.

**Circuit Breaker with Spring Cloud Netflix Hystrix:**

```
@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultBookList")
    public List<String> getBooks() {
        // Assume this service might fail or timeout
        return restTemplate.getForObject("http://book-service/books", List.class);
    }

    public List<String> defaultBookList() {
        return Arrays.asList("Default Book 1", "Default Book 2");
    }
}
```

4. Explain load balancing algorithms.

Load balancing is the process of distributing incoming network traffic or service requests across multiple servers or instances to ensure: High availability, Scalability, Optimal resource usage, Fault tolerance.

**Common Load Balancing Algorithms:**

Round Robin: Requests are distributed evenly across servers in a circular order.

Weighted Round Robin: Similar to Round Robin, but each server is assigned a weight based on its capacity. More powerful servers get more requests.

Least Connections: Routes traffic to the server with the fewest active connections.

Weighted Least Connections: Similar to Least Connections, but considers weights and active connections.

IP Hash: Uses a hash of the client's IP address to consistently route requests to the same server.

Random: Chooses a server randomly.

Least Response Time: Routes to the server with the lowest response time.

Resource-Based / Custom: Uses real-time CPU, memory, or custom metrics to make decisions.

5. Explain API Gateway.

An API Gateway is a server that acts as a single entry point into a microservices system. It handles incoming client requests, routes them to the appropriate backend service, and often performs additional responsibilities like authentication, rate limiting, logging, etc. It is a key component in microservices architecture to manage and control access to internal services.

6. Explain service discovery and service registry.

Service Discovery is a mechanism that enables services in a distributed system (like microservices) to find and communicate with each other automatically, without hardcoding IP addresses or hostnames. In a microservices system, services are often deployed dynamically (e.g., via containers or Kubernetes). Their network locations (IP, port) can change frequently. Hardcoding service endpoints becomes impractical and brittle. Service discovery solves this by providing dynamic lookup and registration of services.

Service Registry: A central database or server that keeps track of all running service instances and their network details (e.g., hostname, IP, port). Examples: Eureka (Netflix), Consul, etcd, Zookeeper


7. List Spring Cloud Modules that serve as Microservice components (e.g. Euerka for Service Discovery)

| Microservice Component              | Spring Cloud Module                                                                            | Purpose                                                                |
| ----------------------------------- | ---------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| **Service Discovery**               | `spring-cloud-starter-netflix-eureka-server` <br> `spring-cloud-starter-netflix-eureka-client` | Registers and discovers microservices (Eureka registry)                |
| **API Gateway**                     | `spring-cloud-starter-gateway`                                                                 | Routes requests to microservices, filters, and transforms              |
| **Configuration Management**        | `spring-cloud-starter-config`                                                                  | Centralized external configuration server                              |
| **Client Load Balancing**           | `spring-cloud-starter-loadbalancer`                                                            | Client-side load balancing (replacement for Ribbon)                    |
| **Resilience / Circuit Breaker**    | `spring-cloud-starter-circuitbreaker-resilience4j`                                             | Handles failures gracefully using circuit breakers, retries, fallbacks |
| **Distributed Tracing**             | `spring-cloud-starter-sleuth`                                                                  | Adds trace and span IDs for distributed tracing                        |
| **Log Aggregation / Visualization** | `spring-cloud-starter-zipkin`                                                                  | Sends trace data to Zipkin for distributed tracing UI                  |
| **Service Bus / Messaging**         | `spring-cloud-starter-bus-amqp` <br> `spring-cloud-starter-bus-kafka`                          | Broadcast config changes or events across services                     |
| **OAuth2 / Authentication**         | `spring-cloud-starter-oauth2`                                                                  | Secures APIs with OAuth2                                               |
| **Contract Testing**                | `spring-cloud-starter-contract-verifier`                                                       | Consumer-driven contract testing                                       |
| **Function as a Service**           | `spring-cloud-starter-function-web`                                                            | Build cloud-agnostic, function-based microservices                     |
| **Kubernetes Integration**          | `spring-cloud-starter-kubernetes`                                                              | Integrates with Kubernetes service discovery and config                |
| **Stream Processing**               | `spring-cloud-starter-stream-kafka` <br> `spring-cloud-starter-stream-rabbit`                  | Event-driven microservices using Kafka or RabbitMQ                     |
| **Security**                        | `spring-cloud-starter-security`                                                                | Adds security support (often used with OAuth2 module)                  |

8. Walk through https://microservices.io/patterns/index.html


