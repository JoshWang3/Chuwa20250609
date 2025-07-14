# Spring IOC and Dependency Injection Examples

## 1. Dependency Injection by Type: Using @Autowired with specific class types

```java
@Component
public class DependencyInjectionByTypeByName {
    /**
     * Match by type - HibernateChuwa is a specific class
     * Variable name can be anything
     */
    @Autowired
    private HibernateChuwa hibernate;

    public void printFirstMessage() {
        System.out.print("By Type(HibernateChuwa hibernate): ");
        hibernate.printMessage();
    }
}
```

**Key Points:**
- When injecting by type, Spring looks for a bean of the exact class type
- Variable name doesn't matter when using specific class types
- Works when there's only one bean of that specific type

## 2. Dependency Injection by Name: Using @Autowired with interface types

```java
@Component
public class DependencyInjectionByTypeByName {
    /**
     * Match by Name - JpaChuwa is an interface
     * Variable name MUST match the bean name (class name with first letter lowercase)
     */
    @Autowired
    private JpaChuwa hibernateChuwa;  // Matches HibernateChuwa class

    @Autowired
    private JpaChuwa eclipseLinkChuwa;  // Matches EclipseLinkChuwa class

    @Autowired
    private JpaChuwa myDataNucleus;  // Matches @Bean method name

    public void printFirstMessage() {
        System.out.print("By Name(JpaChuwa hibernateChuwa): ");
        hibernateChuwa.printMessage();
        
        System.out.print("By Name(JpaChuwa eclipseLinkChuwa): ");
        eclipseLinkChuwa.printMessage();
        
        System.out.print("By Name and @Bean:(myDataNucleus()): ");
        myDataNucleus.printMessage();
    }
}
```

**Key Points:**
- When using interface types, Spring matches by variable name
- Variable name must match bean name (class name with lowercase first letter)
- For @Bean methods, variable name must match method name

## 3. Dependency Injection by Qualifier: Using @Autowired with @Qualifier

```java
@Component
public class DependencyInjectionByTypeByName {
    /**
     * Match by Qualifier - explicit bean selection
     * Variable name can be anything
     */
    @Autowired
    @Qualifier("hibernateChuwa")
    private JpaChuwa jpaChuwaQualifier;

    public void printFirstMessage() {
        System.out.print("By @Qualifier(\"hibernateChuwa\"): ");
        jpaChuwaQualifier.printMessage();
    }
}
```

**Key Points:**
- `@Qualifier` explicitly specifies which bean to inject
- Takes precedence over naming and type matching
- Variable name can be anything when using `@Qualifier`

## 4. Bean Definition via @Bean: Creating beans programmatically

```java
@Configuration
@ComponentScan(basePackages = {"com.chuwa.springbasic"})
public class BeanConfig {
    /**
     * Bean name is the method name
     * This creates a bean programmatically
     */
    @Bean
    @Primary
    @Scope("prototype")
    public JpaChuwa myDataNucleus() {
        return new DataNucleusChuwaNoComponent(); // new Object(), builder pattern
    }
}
```

**Key Points:**
- `@Bean` methods create beans programmatically
- Method name becomes the bean name
- Useful for third-party libraries or complex object creation
- Can combine with `@Primary` and `@Scope`

## 5. Component Scanning: Automatic detection of @Component annotated classes

### Base Interface
```java
public interface JpaChuwa {
    void printMessage();
}
```

### Component Classes
```java
@Component
public class HibernateChuwa implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}

@Component
public class EclipseLinkChuwa implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}

@Component("mybatis")  // Custom bean name
public class Mybatis implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}
```

### Configuration with Component Scanning
```java
@Configuration
@ComponentScan(basePackages = {"com.chuwa.springbasic"})
public class BeanConfig {
    // Component scanning automatically detects @Component classes
}
```

**Key Points:**
- `@ComponentScan` tells Spring where to look for components
- Default bean name is class name with lowercase first letter
- Can specify custom bean name: `@Component("customName")`

## 6. Bean Scope Management: Controlling bean lifecycle

```java
@Configuration
public class BeanConfig {
    @Bean
    @Scope("prototype")  // Creates new instance each time
    public JpaChuwa myDataNucleus() {
        return new DataNucleusChuwaNoComponent();
    }
}

@Component
public class DemoBeanScope {
    @Autowired
    private JpaChuwa jpaChuwa1;
    @Autowired
    private JpaChuwa jpaChuwa2;
    @Autowired
    private JpaChuwa jpaChuwa3;

    public void print() {
        System.out.println("jpaChuwa1.toString: " + jpaChuwa1.toString());
        System.out.println("jpaChuwa2.toString: " + jpaChuwa2.toString());
        System.out.println("jpaChuwa3.toString: " + jpaChuwa3.toString());
        // With prototype scope, these will be different instances
    }
}
```

**Key Points:**
- `@Scope("singleton")` - Single instance (default)
- `@Scope("prototype")` - New instance each time
- Other scopes: request, session, application (web contexts)

## 7. Primary Bean Selection: Using @Primary to resolve ambiguity

```java
@Component
@Primary  // This bean will be preferred when multiple implementations exist
public class HibernateChuwa implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}

@Component
public class EclipseLinkChuwa implements JpaChuwa {
    @Override
    public void printMessage() {
        System.out.println("Message from " + getClass().getName());
    }
}

// In configuration
@Configuration
public class BeanConfig {
    @Bean
    @Primary  // This @Bean method bean will be preferred
    public JpaChuwa myDataNucleus() {
        return new DataNucleusChuwaNoComponent();
    }
}
```

**Key Points:**
- `@Primary` marks a bean as the preferred choice when multiple candidates exist
- Helps resolve `NoUniqueBeanDefinitionException`
- Can be used on both `@Component` classes and `@Bean` methods

## Bean Resolution Priority

Spring resolves bean dependencies in this order:

1. **Match by Type** - If only one implementation exists
2. **Check @Qualifier** - If present, use specified bean
3. **Check @Primary** - If present, use primary bean
4. **Match by Name** - Variable name matches bean name
5. **Error** - If still ambiguous, throw `NoUniqueBeanDefinitionException`

