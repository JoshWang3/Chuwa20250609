## hw12 submission

### Logging with SLF4J
Added:
private static final Logger logger = LoggerFactory.getLogger(...) to all controllers and services.

Logged important operations like create, update, delete, and exception catch blocks.

Example:
```java
logger.info("Creating post with title: {}", postDto.getTitle());
```

## Logging Configuration
In application.properties:

```java
logging.level.root=INFO
logging.level.com.chuwa=DEBUG
```

This ensures clean output with debugging for your app's own package while avoiding unnecessary noise from dependencies.

## External Tomcat Deployment
Steps Completed:

Updated pom.xml:
```
<packaging>war</packaging>
```

Ran:
```
mvn clean package
```

Output file: redbook-0.0.1-SNAPSHOT.war

Copied WAR to Tomcat webapps:

```
cp target/redbook-0.0.1-SNAPSHOT.war ~/tomcat9/webapps/
```

Started Tomcat:

```
cd ~/tomcat9/bin
./startup.sh
```

Accessed app at:

```
http://localhost:8080/redbook-0.0.1-SNAPSHOT/
```

## Screenshots 
Console with SLF4J logs

Postman test cases (valid/invalid input)

Error messages from API

Tomcat running with deployed .war

File structure (target/, application.properties)

