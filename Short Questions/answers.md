1. Explain monolithic architecture, service oriented architecture and microservice architecture.
    - Monolithic Architecture is a single-tiered tightly coupled application where all components are part of one codebase and deployed as a single unit.
      It is simple to develop and test for small applications. It is easy to deploy and have lower latency overhead.
      But it is difficult to scale individual parts. As it grows, it becomes more and more difficult to maintain and even understand. One change in one part requires rebuilding and deploying the entire application. And it is not resilient.
      
    - Service-Oriented Architecture is an architecture where applications are composed of multiple services, each responsible for a specific business function, often communicating bia enterprise service bus(ESB). SOA promotes reuse of services across different applications, and services are loosely coupled, so SOA is more scalable and maintainable than monoliths. However, ESB can become a bottleneck and single point of failure. Since SOA relies on SOAP and XML, the architect is still relatively heavy. It is also less granular than microservices and more complex in governance and service orchestration.

    - Microservices Architecture is an evolution of SOA where applications are built as independent, small, single-responsibility services. Services communicate typically via lightweight protocols like REST/HTTP or gRPC. Each microservice can be indepentdently deployed, scaled and maintained. It has higher scalalbility and flexibility. It is easier to deploy and update individual features. Each service can use different tech stack, since the communication protocol is same. It also have fault isolation. However, all these advantages come with cost. It has higher complexity. It requires robust DevOps, and data consistency is harder to maintain, And it is more difficult to test and debug. 

2. Document microservice architecture and core components.
    - API Gateway:
        Entry point for all client requests. It provide routing service to appropriate services. Some API Gateways also serve for authentication and authorization. It also provides rate limiting and monitoring.
    - Load Balancer:
        Balances cilent or service-to-service traffic across multiple instance of a microservice. It also increases availability. If one instance fails, the load balance routes traffic to healthy instances, thus provides fault tolerance and high availability.
    - Service Registry:
        Registering Service instances when they start, also keeps service metadata.
    - Service Discovery:
        Keeps track of availble microservices and their locations. Allos services to find and communicate with each other dynamically. 
    - Configuration Management:
        Centralized service to manage configuration for all microservices across environments. It also supports dynamic updates without redeploying. 
    - Inter-service Communication:
        microservices communicate using:
            Synchronous: HTTP/REST, gRPC
            Asynchronous: Messaging systems like Kafka, RabbitMQ
    - Database per Service:
        Each microservice owns its onw database or schema.
        Encourages loose coupling and data encapsulation. But this architect leads to challenges like eventual consistency and distributed transactions.
    - Logging and Monitoring
        Centralized logging, metrics and tracing to observe system behavior.
    - Security:
        Authentication and authorization at both the gateway and service level.
        Use token-base security(JWT), Oauth 2.0
    - CI/CD pipline:
        Enables frequent deployment of individual microservices. 
        Automates build, test, and deploy processes.
    - Containerization and Orchestration
        Containerized services for portability and isolation
        Orchestration for managing deployments, scaling and health.

3. Explain resilience patterns, Explain circuit breaker with spring cloud hystrix code example.
    - Circuit Breaker
        Prevents calling a service that is already failing.
        Like a real circuit breaker: trips after repeated failures and gives time to recover.
        Helps avoid cascading failures.
        '''java
        CircuitBreaker cb = CircuitBreaker.ofDefaults("userService");
        Supplier<String> result = CircuitBreaker.decorateSupplier(cb, ()->userService.getUserInfo());
        '''
    - Retry Pattern
        Automatically retries failed requests a fixed number of times before failing.
        Handles transient faults, Should be used with exponential backoff to avoid flooding the service.
        '''java
        Retry retry = Retry.ofDefault("backednService");
        Supplier<String> retryableCall = Retry.decorateSupplier(retry, ()->backendService.call());
        '''
    - Timeout Pattern
        Sets a maximum time to wait for a response from another service.
        Prevents hanging calls and resource locking.
        Common in REST/gRPC client configurations.
        '''yaml
        webclient:
            connect-timeout:2000
            read-timeout:1000
        '''
    - Bulkhead Pattern
        Isolates resources between services
        Prevents one failing service from consuming all resources.
        Protects the system from resource exhaustion.
        
    - Failover/Fallback
        Provides a backup logic or service when the primary one fails.
        Could be static data, cached data, or an alternate service.
        Ensures degraded but acceptable service.

    - Rate Limiting/Throttling
        Limits how many requests a service or client can make in a time window.
        Protects services from overload, especially from sudden spikes or abuse.

    - Load Shedding
        Proactively drops requests when system is under heavy load to maintain performance
        Keeps core services healthy during traffic surges
    
    - Health Checks and Monitoring
        Actively monitor service health and make routing decisions or automated restarts.
        Used by orchestrators(Kubernetes, ECS) to restart failing containers.

4. Explain load balancing algorythms. 
    - Round Robin:
        Requests are distributed sequentially to each server in a loop.
        Each server gets an equal number of requests.

    - Least Connections
        New reqeuest is send to the server with the fewest active connections.

    - IP Hash
        Hashes the clent IP address to always route to the same server.
        Useful for session persistence without external state.

    - Random 
        requests are routed to a randomly chosen instance.

    - Least Response Time
        routes traffic to the server with the lowest reponse time.
        Highly effective for low-latency systems.

5. Explain API Gateway
    An API Gateway is a server that acts as a single entry point for all client requests to a microservices system. It sits between the clients and backend services and manages routing, security, rate limiting, logging and more. 
    In a microservices architecture, clients would otherwise have to know the addresses of all services, or handle autehntication, retries, failures, or mke multiple calls for a single screen or featrue. The API Gateway simplifies this and adds many helpful capabilities.
    Key Responsibilities of an API Gateways are:
        - Request Routing
        - Authentication and Authorization
        - Rate Limiting and Throttling
        - Load balancing
        - Circuit Breaking and retries
        - Logging and monitoring
        - REsponse Aggregation
        - Cross-Origin Resource Sharing

6. Explain service discovery and service registry
    Service Registry is a central database or server that keeps track of:
        Service names,
        IP addresses and ports,
        Health status
    Every service instacne registers itself with the registry when it starts and deregisters when it stops or fails.
    Service Discovery is the mechanism that lets a service find another service by querying the service registry.
    There are two main types:
        Client-side discovery:
            The client(or API Gateway) queries the registry directly to find service instances.
            It selects one and makes request.
        Server-Side Discovery:
            The client sends a generic request to a load balancer or API Gateway.
            the gateway/load balancer queries the registry, picks a healtht instance, and forwards the request.

7. List Spring Cloud Modules that serve as Microservice components.
    - Spring Clound Config:
        Centralized configuration management
        Stores configuration for all services in a Git repo or file system.
        Supports dynamic refresh using @RefreshScope

    - Spring Cloud Netflix Eureka:
        Service Registry and discovery
        Service register themselves and discover others dynamically
        Support client-side load balancing

    - Spring Cloud Gateway
        API Gateway
        Routes, filters, authenticates, and logs all incoming traffic.
        Replaces Netflix Zuul

    - Spirng Cloud LoadBalancer
        Client-side load balancing for service to service communication
        Replaces Netflix Ribbon
    
    - Spring Clound OpenFeign
        Declarative REST client
        Simplifies HTTP communication between microservices
        Integrates with Ribbon or Spring Cloud LoadBalancer
    
    - Spring Cloud Circuit Breaker
        Resilience patterns
        Supports multiple backends.
        Handles failure gracefully in downstream services.

    - Spring Cloud Sleuth
        Distributed tracing. 
        Adds tracing IDs to logs for request tracking

    - Spring Cloud Bus
        Broadcasts config changes and event across distributed services
        Works with messaging systems like RabbitMQ or Kafka.

    - Spring Cloud Stream
        Messaging abstraction over brokers like Kafka, RabbitMQ
        Simplifies event-driven architecture and asynchronous communication

    - Spring Cloud Contract
        Consumer-driven contract testing
        Verifies that service providers and consumers agree on API behavior

    - Spring Cloud Security
        Integrates OAuth2, JWT and other security patterns.
        Workds well with Spring Security and Spring Authorization Server.
    
    - Spring Cloud Kubernetes
        Integrates Spring Cloud with Kubernetes-native features:
            ConfigMaps and Secrets
            Kubernetes Service Discovery
            Health checks and probes