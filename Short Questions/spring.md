1. Dependency Injection (DI) / Inversion of Control (IoC):
   1. Objects are not responsible for creating their dependencies; instead, dependencies are injected by the framework.
   2. Promotes loose coupling, easier testing, and modular code.

2. Aspect-Oriented Programming (AOP):
   1. Separates logging, security, and transactions from the business logic.
   2. Improves code modularity and reusability.

3. Annotations and XML Configuration:
   1. Configuration are declared using annotations (e.g., @Component, @Service, @Autowired) or XML.


Dependency Injection (DI) is a design pattern used to implement Inversion of Control (IoC)
1. Constructor Injection: Dependencies are passed via the class constructor.
   1. When dependencies are required for the object to function correctly.
   2. For immutable dependencies (final fields).
```
@Component
public class OrderService {
    private final PaymentService paymentService;

    @Autowired
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
2. Setter Injection: Dependencies are provided through setter methods after the object is constructed.
   1. When dependencies are optional.
   2. For reconfigurable or replaceable components 
```
@Component
public class OrderService {
    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

3. Field Injection: Dependencies are injected directly into fields using the @Autowired annotation.

Why not recommended?:
1. Poor testability: Fields are private and final mocks cannot be injected easily in unit tests.
2. Hidden dependencies: Dependencies are not obvious in the constructor or public API.
3. Immutability: Fields are usually not final, making the class mutable.
4. Reflection dependency: Requires Spring's internal mechanisms to inject via reflection.

```
@Component
public class OrderService {
    @Autowired
    private PaymentService paymentService;
}
```