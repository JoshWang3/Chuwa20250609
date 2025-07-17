# Spring Security HTTPS Application

This is a Spring Boot application that demonstrates HTTPS configuration with a self-signed certificate using Spring Security.

## Features

- **HTTPS only**: All HTTP requests are redirected to HTTPS
- **Self-signed certificate**: Uses a JKS keystore with a self-signed certificate
- **Spring Security**: Configured to enforce secure channels
- **Simple REST endpoints**: Provides basic API endpoints for testing

## Project Structure

```
src/main/java/com/chuwa/springsecurityhttps/
├── HttpsApplication.java              # Main Spring Boot application
├── config/SecurityConfig.java         # Spring Security configuration
└── controller/SecureController.java   # REST controller with test endpoints

src/main/resources/
├── application.properties             # Application configuration
└── myapp-keystore.jks                # Self-signed certificate keystore
```

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Running the Application

1. **Build the application:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR directly:
   ```bash
   java -jar target/spring-security-https-1.0.0.jar
   ```

3. **Access the application:**
   - The application runs on port 8443 (HTTPS only)
   - Home endpoint: https://localhost:8443/
   - Secure endpoint: https://localhost:8443/secure

## Testing the HTTPS Configuration

### 3.2 Testing Without Certificate Import

When you try to access the application without importing the certificate, you'll encounter SSL/TLS verification errors:

```bash
curl https://localhost:8443/
# Result: SSL certificate problem: self signed certificate
```

This happens because:
- The certificate is self-signed (not issued by a trusted CA)
- Your client cannot verify the certificate chain
- The certificate is not in your system's trust store

### 3.3 Making HTTPS Calls Work

**Option 1: Export and Import Certificate**

1. Export the certificate from the keystore:
   ```bash
   keytool -exportcert -alias myapp -keystore src/main/resources/myapp-keystore.jks -storepass changeit -file myapp.crt
   ```

2. Import to system trust store (macOS):
   ```bash
   sudo security add-trusted-cert -d -r trustRoot -k /Library/Keychains/System.keychain myapp.crt
   ```

3. Or import to Java cacerts:
   ```bash
   sudo keytool -import -trustcacerts -file myapp.crt -alias myapp -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit
   ```

**Option 2: Accept Certificate in Browser**

1. Visit https://localhost:8443/ in your browser
2. Click "Advanced" when you see the security warning
3. Click "Proceed to localhost (unsafe)"
4. The browser will remember your choice for this session

**Option 3: Configure HTTP Client**

For testing with tools like curl, you can specify the certificate:
```bash
# Extract certificate
keytool -exportcert -alias myapp -keystore src/main/resources/myapp-keystore.jks -storepass changeit -rfc -file myapp.crt

# Use with curl
curl --cacert myapp.crt https://localhost:8443/
```

## Certificate Details

- **Algorithm**: RSA 2048-bit
- **Validity**: 365 days
- **Subject**: CN=localhost, OU=IT Department, O=Chuwa, L=New York, ST=NY, C=US
- **Keystore Format**: JKS (Java KeyStore)
- **Keystore Password**: changeit

## Security Configuration

The application is configured to:
- Require HTTPS for all requests
- Reject HTTP connections
- Use the self-signed certificate for TLS handshake
- Allow all requests (no authentication required for testing)

## Testing Endpoints

Once the certificate is trusted, you can test:

```bash
# Home endpoint
curl https://localhost:8443/
# Response: "HTTPS Application is running!"

# Secure endpoint (empty response as required)
curl https://localhost:8443/secure
# Response: (empty)
```

## Notes

- The JKS format is considered legacy; PKCS#12 is recommended for production
- Self-signed certificates should only be used for development/testing
- In production, use certificates from trusted Certificate Authorities 