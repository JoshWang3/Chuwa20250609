### 1 spring annotation cheatsheet
    @ExceptionHandler
    Method Level
    used to handle the specific exceptions and sending the custom responses to the client
    @ControllerAdvice
    Class Level
    make this class be a bean


### 2 


### 3 model mapper 
    an api to map entity object to dto object. 
    when need to wrap dto in to response object

### 4 model mapper example
    1. field name mismatch
    class User {
        private String firstName;
    }

    class UserDTO {
        private String givenName;
    }


    2. deep nested object
    class Order {
        private Customer customer;
    }

    class OrderDTO {
        private String customerName;
    }
    3. type incompatibility 
    class Product {
        private BigDecimal price;
    }

    class ProductDTO {
        private String price;
    }

### 5 mapper cast
    1. implicity cast: built-in convert auto mapping 
    2. explicity cast: define converter then add to mapper or use TypeMap
    3. manual cast: custom propertyMap

### 6 example of exception handler

![Screenshot 2025-07-14 at 12.29.01 AM.png](Screenshot%202025-07-14%20at%2012.29.01%E2%80%AFAM.png)


### 7 controller advice mechanism 
    an annotation that centrailzes and manages exception handling across all controllers 
    in a spring MVC app. it catches exceptions thrown from @controler or @RestController methods and 
    allow customize the http response globally.

    @ResponseStatus on exception class: You want minimal configuration and can live with Spring's default JSON error format.

    
    @HandlerExceptionResolver

    ResponseEntityExceptionHandler
    
    filter/interceptor based exception handling

### 8 regular exception vs customized api exception (controller advice code)
    regualr - general without context and harder to differentiate from other run time exception
    
    Feature	            Regular Exception	Custom API Exception
    Semantic clarity	❌ Generic	        ✅ Domain-specific
    Structured fields	❌ Just a message	✅ You define them
    ControllerAdvice routing	✅ Works	✅ Works
    HTTP status customization	❌ Requires global mapping	✅ Can set directly or in handler
    Useful for REST clients	❌ Not informative	✅ Predictable & detailed
    Extendability (i18n, logging)	❌ Limited	✅ Easy to extend

![Screenshot 2025-07-14 at 3.24.14 PM.png](Screenshot%202025-07-14%20at%203.24.14%E2%80%AFPM.png)


### 9 regex and validation 




### 10 spring framework advantage 
    
    spring mvc's core principle like DI, IoC, modular based architecture, SoC. has clean architecture follow oop design patterns like singleton, factory, etc. it given application 
    a strong fundenmental like loose coupling, high cohension with great feasibilty on scalabiltity. it makes app
    easy to test and maintain. spring also come with all kind of components such as security, cloud, etc. which can
    make app implement cutting edge feature in least efforts. 

### 11 types of DI
    1. constructor injection: when main depedencies are component of the injected class. depedencies that require
    insanciate when class created. dependecies are require for the object to function 
    class immutable, and easier unit testing. 

    2. field injection: flexiable field dependencies which one class need another won't. it called 
    after class instanciate. usually quick and dirty writing for prototypes or small demo.

    3. setter injection: dependencies are optional. allow changing dependeices after instantiation like test.
    
    field injection is not recommended because it act like global variable could lead to race condition. also
    it will store in different jvm memory like construtor in heap but feild will be in string pool. risk of 
    memory leak or bad performance. make class tightly coupled to the spring framework. orer of intialization is unclear
    no way to require dependencies at compile time, violates encapsualtion. make unit test harder.


    constructor injection:
    @Component
    public class DependencyInjectionTypesConstructor {

    /**
     * type 1, field Injection
     */
    //    @Autowired
    private JpaICC jpaICC;

    /**
     * type 2, Constructor Injection
     * @Autowired可以省略
     */
    @Autowired
    public DependencyInjectionTypesConstructor(JpaICC jpaICC) {
        this.jpaICC = jpaICC;
    }


    filed injection:
    @Component
    public class DependencyInjectionTypesField {

    /**
     * type 1, field Injection
     */
    @Autowired
    private JpaICC jpaICC;

    setter injection:
    @Component
    public class DependencyInjectionTypesSetter {

    /**
     * type 1, field Injection
     */
    //    @Autowired
    private JpaICC jpaICC;

    /**
     * type 2, Constructor Injection
     * @Autowired可以省略
     */
    //    @Autowired
    //    public DependencyInjectionTypesSetter(JpaICC jpaICC) {
    //        this.jpaICC = jpaICC;
    //    }

    /**
     * type 3, Setter Injection
     * @Autowired可以省略
     */
    @Autowired
    public void setJpaICC(JpaICC jpaICC) {
        this.jpaICC = jpaICC;
    }


### 12 types of application context
    ApplicationContext is the cetral interface for spring IoC container 
    AnnotationConfigApplicationContext
    Used in: Pure Java-based configuration

    Configuration style: @Configuration + @Bean

    Common in: Spring Boot, standalone apps, modern Spring projects

    ClassPathXmlApplicationContext
    Used in: XML-based configuration files loaded from the classpath
    
    Legacy but still valid in some enterprise apps


### 13 @component vs @bean
    @Component: mark class as bean managed object 
    When the class is under component-scanned packages.

    When you want Spring to manage the object automatically.
    @Bean: defin the bean method inside a @Conifguration class
    Gives you full control over how the bean is created.

    Applied at method level.

    Feature	@Component	@Bean
    Target	Class	Method
    Usage	Auto-detected via scanning	Manually defined in @Configuration
    Control	Spring creates object automatically	You fully control how the object is built
    Use case	Your own service/repo/controller	External libraries or custom bean setup

    Use @Component for application classes.

    Use @Bean when you can't modify the class or need full instantiation control.


### 14 bean scope
    Bean scope in Spring defines how long a bean lives and how many instances are created in the container.
    Scope	Description
    singleton	One instance per Spring container (default)
    prototype	A new instance each time it’s requested
    request	One instance per HTTP request (Spring Web only)
    session	One instance per HTTP session (Spring Web only)
    application	One instance per ServletContext (Spring Web only)
    websocket	One instance per WebSocket session


### 15 bean id vs bean class

    Bean ID	The name used to reference the bean in the Spring container. Think of it like a unique key or alias.
    Bean Class	The Java class that defines the actual implementation of the bean (what gets instantiated).
    Property	Bean ID	Bean Class
    What is it	Name (String)	Java class
    Unique	Yes (per container)	No (can have multiple beans of same class)
    Used for	Lookup, wiring	Instantiation, typing


### 16 multiple alternative bean 
    by default. spring will throw exception because it does not know who to use.
    use @Primary annotation to mark one as default
    Use @Qualifier("beanName") – Inject by name
    inject all
    spring bean container will select it by its id or name 
    

    

## Homework 12

### 1 log implement

![Screenshot 2025-07-14 at 8.21.43 PM.png](Screenshot%202025-07-14%20at%208.21.43%E2%80%AFPM.png)


### 4 external tomcat host

![Screenshot 2025-07-14 at 8.07.39 PM.png](Screenshot%202025-07-14%20at%208.07.39%E2%80%AFPM.png)



    
    


    




    

   