# Spring Boot Logging and External Tomcat Setup

## 1. SLF4J Logging Implementation

### 1.1 Logging Dependencies
Spring Boot Starter Web already includes SLF4J and Logback by default:
- `spring-boot-starter-logging` (included in `spring-boot-starter-web`)
- SLF4J API
- Logback implementation

### 1.2 Logger Implementation in Classes

#### PostController.java
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        logger.info("Creating new post with title: {}", postDto.getTitle());
        try {
            PostDto postResponse = postService.createPost(postDto);
            logger.info("Successfully created post with ID: {}", postResponse.getId());
            return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating post: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        logger.info("Fetching post with ID: {}", id);
        PostDto postDto = postService.getPostById(id);
        logger.debug("Retrieved post: {}", postDto.getTitle());
        return ResponseEntity.ok(postDto);
    }
}
```

#### PostServiceImpl.java
```java
@Service
public class PostServiceImpl implements PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public PostDto createPost(PostDto postDto) {
        logger.info("Service: Creating post with title: {}", postDto.getTitle());
        
        logger.debug("Validating post fields for title: {}", postDto.getTitle());
        validatePostTitle(postDto.getTitle());
        validatePostDescription(postDto.getDescription());
        validatePostContent(postDto.getContent());
        logger.debug("Validation completed successfully for post: {}", postDto.getTitle());
        
        try {
            Post post = modelMapper.map(postDto, Post.class);
            logger.debug("Mapped DTO to entity for post: {}", postDto.getTitle());

            Post savedPost = postRepository.save(post);
            logger.info("Successfully saved post to database with ID: {}", savedPost.getId());

            PostDto result = modelMapper.map(savedPost, PostDto.class);
            logger.debug("Mapped entity back to DTO for post ID: {}", savedPost.getId());
            return result;
        } catch (Exception e) {
            logger.error("Failed to create post: {}", e.getMessage(), e);
            throw new InternalServerException("Failed to create post: " + e.getMessage());
        }
    }
}
```

#### GlobalExceptionHandler.java
```java
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(ValidationException exception,
                                                                 WebRequest webRequest) {
        logger.warn("Validation error occurred: {}", exception.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
```

## 2. Logging Configuration (application.properties)

```properties
# Logging Configuration
logging.level.root=INFO
logging.level.com.chuwa.redbook=DEBUG
logging.level.com.chuwa.redbook.controller=INFO
logging.level.com.chuwa.redbook.service=DEBUG
logging.level.com.chuwa.redbook.exception=WARN
logging.level.org.springframework=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Logging pattern and file
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
logging.file.name=logs/redbook.log
logging.file.max-size=10MB
logging.file.max-history=30
```

### 2.1 Logging Levels Explained

| Level | Purpose | Package/Class |
|-------|---------|---------------|
| `DEBUG` | Development debugging | `com.chuwa.redbook` (main package) |
| `INFO` | General information | `com.chuwa.redbook.controller` |
| `DEBUG` | Detailed service operations | `com.chuwa.redbook.service` |
| `WARN` | Exception warnings | `com.chuwa.redbook.exception` |
| `DEBUG` | SQL statements | `org.hibernate.SQL` |
| `TRACE` | SQL parameter bindings | `org.hibernate.type.descriptor.sql.BasicBinder` |

### 2.2 Log Output Examples

#### Console Output (INFO level):
```
2025-07-13 17:42:44 - Creating new post with title: Logging Test Post
2025-07-13 17:42:44 - Successfully created post with ID: 1
```

#### File Output (with thread and logger info):
```
2025-07-13 17:42:44 [http-nio-8080-exec-2] WARN  c.c.r.e.GlobalExceptionHandler - Validation error occurred: Title must be 3-100 characters, alphanumeric with basic punctuation
2025-07-13 17:42:44 [http-nio-8080-exec-2] INFO  c.c.r.s.i.PostServiceImpl - Service: Creating post with title: Valid Post
2025-07-13 17:42:44 [http-nio-8080-exec-2] DEBUG c.c.r.s.i.PostServiceImpl - Validation completed successfully for post: Valid Post
```

## 3. External Tomcat Server Setup

### 3.1 Project Configuration Changes

#### 3.1.1 Updated pom.xml
```xml
<packaging>war</packaging>

<!-- Added provided scope for Tomcat -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

#### 3.1.2 ServletInitializer.java
```java
package com.chuwa.redbook;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RedbookApplication.class);
    }
}
```

### 3.2 WAR File Generation

#### Build Command:
```bash
./mvnw clean package
```

#### Generated Files:
- **WAR File**: `target/redbook-0.0.1-SNAPSHOT.war` (45MB)
- **Location**: `/target/redbook-0.0.1-SNAPSHOT.war`

### 3.3 External Tomcat Deployment Steps

#### Step 1: Download and Setup Tomcat
```bash
# Download Tomcat 9.x
wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.82/bin/apache-tomcat-9.0.82.tar.gz

# Extract
tar -xzf apache-tomcat-9.0.82.tar.gz
cd apache-tomcat-9.0.82
```

#### Step 2: Deploy WAR File
```bash
# Copy WAR file to Tomcat webapps directory
cp /path/to/redbook-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/

# Or rename for ROOT deployment
cp /path/to/redbook-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/ROOT.war
```

#### Step 3: Start Tomcat
```bash
# Start Tomcat
$CATALINA_HOME/bin/startup.sh

# Check logs
tail -f $CATALINA_HOME/logs/catalina.out
```

#### Step 4: Access Application
- **Application URL**: `http://localhost:8080/redbook-0.0.1-SNAPSHOT/api/v1/posts`
- **Or ROOT deployment**: `http://localhost:8080/api/v1/posts`

### 3.4 External Tomcat vs Embedded Tomcat

| Aspect | Embedded Tomcat | External Tomcat |
|--------|-----------------|-----------------|
| **Deployment** | JAR file | WAR file |
| **Server Management** | Managed by Spring Boot | Managed separately |
| **Configuration** | application.properties | server.xml + context.xml |
| **Scaling** | Process-based | Server-based |
| **Production Use** | Microservices | Traditional deployment |

### 3.5 Configuration for External Tomcat

#### Tomcat Context Configuration (optional):
Create `META-INF/context.xml` in WAR:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context>
    <!-- JNDI DataSource configuration -->
    <Resource name="jdbc/RedbookDB"
              auth="Container"
              type="javax.sql.DataSource"
              maxTotal="20"
              maxIdle="5"
              maxWaitMillis="10000"
              username="sa"
              password=""
              driverClassName="org.h2.Driver"
              url="jdbc:h2:mem:testdb"/>
</Context>
```

## 4. Testing the Setup

### 4.1 Embedded Tomcat (Development)
```bash
# Start with Maven
./mvnw spring-boot:run

# Test endpoints
curl http://localhost:8080/api/v1/posts
```

### 4.2 External Tomcat (Production)
```bash
# Deploy WAR and start Tomcat
cp target/redbook-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh

# Test endpoints
curl http://localhost:8080/redbook-0.0.1-SNAPSHOT/api/v1/posts
```

### 4.3 Log Verification
```bash
# Check application logs
tail -f logs/redbook.log

# Check Tomcat logs (external deployment)
tail -f $CATALINA_HOME/logs/catalina.out
```

## 5. Key Benefits

### 5.1 Logging Benefits
- **Structured logging** with different levels
- **File and console output** with custom patterns
- **Performance monitoring** with SQL logging
- **Error tracking** with exception logging
- **Debug capabilities** with detailed service logs

### 5.2 External Tomcat Benefits
- **Enterprise deployment** compatibility
- **Centralized server management**
- **Multiple application deployment**
- **Traditional operations** workflow support
- **Security and monitoring** integration

## 6. Screenshots and Evidence

### Build Success:
```
[INFO] BUILD SUCCESS
[INFO] Total time: 4.536 s
[INFO] WAR file: redbook-0.0.1-SNAPSHOT.war (45MB)
```

### Logging Output:
```
2025-07-13 17:42:44 [http-nio-8080-exec-2] WARN c.c.r.e.GlobalExceptionHandler - Validation error occurred: Title must be 3-100 characters
2025-07-13 17:42:44 [http-nio-8080-exec-1] INFO c.c.r.c.PostController - Creating new post with title: Logging Test Post
2025-07-13 17:42:44 [http-nio-8080-exec-1] INFO c.c.r.s.i.PostServiceImpl - Successfully saved post to database with ID: 1
```

This setup provides comprehensive logging for development and debugging, while also preparing the application for enterprise deployment using external Tomcat servers. 