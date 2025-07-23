Monolithic Architecture:

Definition:

A monolithic architecture is a traditional software design pattern where all components of an application (UI, business logic, data access, etc.) are built as a single, tightly integrated unit.

Characteristics:
1. A single codebase. 
2. All features and functionalities run in one process. 
3. Deployment happens as one package (e.g., a JAR file).


Service-Oriented Architecture (SOA):

Definition:

SOA is an architectural style that structures an application as a collection of loosely coupled services, which communicate over a network (usually using protocols like SOAP or REST).

Characteristics:
1. Services are reusable and expose well-defined interfaces. 
2. Often relies on a central Enterprise Service Bus (ESB) for communication and orchestration. 
3. Designed for enterprise-scale applications.

Microservice Architecture:

Definition:

A microservice architecture breaks down an application into independent, small, and focused services, each responsible for a specific business capability. These services communicate via lightweight protocols (usually HTTP/REST or messaging queues).

Characteristics:
1. Each microservice is independently deployable and scalable. 
2. Typically uses its own database (decentralized data management). 
3. Strong focus on automation, CI/CD, and DevOps.

Resilience Patterns:

Resilience patterns are design strategies used to build fault-tolerant and robust microservices that can gracefully handle failures. In a distributed system, failures are inevitable due to network issues, service crashes, or high latency. These patterns help avoid cascading failures and improve system stability.

Patterns:
1. Circuit Breaker

    Prevents repeated calls to a failing service. It "trips" after failures and allows fallback logic or short-circuiting.

2. Retry 

   Automatically retries a failed request a fixed number of times before failing.

3. Timeout

    Defines a time limit for a response from a service. If the service doesn't respond within this time, it fails.

4. Fallback

    Provides an alternative path or default value when a service call fails.

5. Bulkhead

    Isolates resources so that failure in one part doesn't bring down the whole system.

6. Rate Limiting

    Prevents overloading services by limiting the number of requests per client or per time window.

7. Load Shedding

    Rejects excessive requests to maintain system performance during overload.

Circuit Breaker:

A Circuit Breaker monitors service calls and stops calling a downstream service if failures exceed a threshold. It transitions between three states:
1. Closed: All requests go through. If failures occur, it starts counting.
2. Open: Calls are blocked for a cooldown period.
3. Half-Open: After the wait time, a few requests are allowed through to test if the service has recovered.
```
@Service
public class ProductService {

    @HystrixCommand(fallbackMethod = "getProductFallback")
    public String getProduct() {
        // Simulate delay or failure
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Product service failed");
        }
        return "Product details from remote service";
    }

    // Fallback method
    public String getProductFallback() {
        return "Fallback: default product info";
    }
}
```

Load Balancing:

Load balancing algorithms are strategies used by load balancers to distribute incoming traffic (e.g., client requests, network packets) across multiple servers or service instances. The goal is to optimize resource utilization, minimize response time, and ensure high availability and fault tolerance.

1. Round Robin

   Requests are distributed sequentially and cyclically across the server pool.

2. Least Connections

   Routes the request to the server with the fewest active connections.

3. IP Hash

   Applies a hash function to the client’s IP address to determine which server to use. Ensures that a given client is always directed to the same server.

API Gateway:

An API Gateway abstracts calls to different services, each with its own address and logic, by providing a unified interface.

Core Responsibilities:
1. Request Routing
   1. Directs incoming requests to the appropriate microservice. 
   2. Can route based on path (/user, /order), headers, or other metadata.

2. Aggregation
   1. Combines results from multiple services into a single response. 
   2. Reduces the number of client-side calls.

3. Authentication and Authorization
   1. Verifies credentials (e.g., OAuth2, JWT) before forwarding to backend services. 
   2. Offloads security logic from individual services.

4. Rate Limiting and Throttling
   
    Controls how many requests a user or app can make within a time window.

5. Load Balancing
   
    Distributes requests across service instances (sometimes done in coordination with a service mesh or registry).

6. Caching

   Stores frequently accessed responses to reduce load on backend services.

7. Monitoring and Logging
   
    Tracks metrics like latency, error rates, and traffic volume for observability.

Service Discovery:

Service Discovery is the process by which microservices automatically find and communicate with each other without hardcoding network locations (e.g., IPs, ports).

1. Client-Side Discovery: The client queries the service registry and determines which instance to call. 
2. Server-Side Discovery: The client sends a request to a load balancer or gateway, which queries the registry and routes the request.

Service Registry:

A Service Registry is a database of services and their locations (IP address, port, metadata). It keeps track of which service instances are available and healthy.

Key Functions:
1. Register services when they start. 
2. Deregister services when they shut down. 
3. Heartbeat/Health Checks to detect failures. 
4. Query to retrieve a list of available services.

Modules:

Spring Cloud Eureka:

Service Discovery and Registration (Netflix Eureka)

Spring Cloud Config:

Centralized configuration management for all services

Spring Cloud Gateway:

API Gateway for routing, filtering, and request handling

Spring Cloud OpenFeign:

Declarative REST client for inter-service communication

Spring Cloud Circuit Breaker:

Circuit breaker abstraction (supports Resilience4j, Sentinel, etc.)

Spring Cloud LoadBalancer:

Client-side load balancing (replaces Netflix Ribbon)

Spring Cloud Bus:

Broadcasts config changes via messaging (e.g., Kafka, RabbitMQ)

Spring Cloud Stream:

Event-driven microservices via messaging platforms

Spring Cloud Security:

OAuth2 and token-based security for microservices

Spring Cloud Consul:

Service Discovery and Configuration using Consul

Spring Cloud Kubernetes:

Integrates Spring Cloud apps with Kubernetes discovery/config
