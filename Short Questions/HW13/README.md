1. List all of the annotations you learned from class and homework to annotaitons.md

2. Explain TLS, PKI, certificate, public key, private key, and signature.

**TLS (Transport Layer Security)** : TLS is a cryptographic protocol that provides secure communication over the internet. It ensures:

* Confidentiality (encryption so no one can read your data)

* Integrity (data isn’t tampered with)

* Authentication (you know who you’re talking to)

It's used in HTTPS, email, messaging, etc.

**PKI (Public Key Infrastructure):** PKI is a framework for managing public-key encryption. It includes:

* Certificates (digital IDs)

* Certificate Authorities (CAs) (trusted organizations that issue certificates)

* Processes and policies for secure key and certificate management

It like a system that ensures people and websites are who they say they are.

**Certificate:** A certificate is like a digital passport. It contains:

* The owner’s public key

* Information about the owner (e.g. domain name, company)

* The issuer (the CA)

* A digital signature from the CA

It proves the binding between a public key and an entity (e.g., a website).

**Public Key & Private Key:** These are the two parts of asymmetric encryption:

Public Key:

* Can be shared with anyone

* Used to encrypt data or verify signatures

Private Key:

* Must be kept secret

* Used to decrypt data or sign messages

They are mathematically linked — what one encrypts, the other can decrypt.

**Signature (Digital Signature):** A digital signature is a way to prove authenticity and integrity:

* It’s created by hashing the message and encrypting the hash with the private key

* Anyone with the public key can verify the signature

It proves:

* The sender had the private key (authenticity)

* The message hasn’t changed (integrity)

A complete process:

* You visit https://example.com.

* The server sends its certificate.

* Your browser:

	* Verifies the certificate with a trusted CA (via PKI)

	* Extracts the public key from the certificate

* A TLS session is established, encrypting all data between you and the server.

* All data exchanged is secure and trustworthy.

3. Write a Spring security based application, which provides https APIs (one simple get controller with empty response is good enough )instead of http, please generate a self-signed certificate to make your https TLS verfication work.

[SpringApplication](../../Coding/HW13/demo/src/main/java/com/example/demo/DemoApplication.java)

* Pack your self-signed certificate in the form of jks file, as part of your application, name it properly

* Test if you can verify your HTTPs api without importing the self-signed certificate to your local certificate chain, if not, explain why.

![](withoutcertification.png)

I get a warning because my browser doesn't trust the self-signed cert. When using self-signed certs, there's no Certificate Authority (CA) to vouch for the certificate. So clients reject it unless the cert is explicitly trusted. This is the expected behavior of TLS.

* Explain what did you do to make https call work, do NOT bypass TLS/SSL verfication in Postman (this is cheating)!

First, export the cert:

```
keytool -exportcert \
  -keystore keystore.jks \
  -alias my-https-cert \
  -file cert.cer \
  -storepass changeit
```

Then, import it into a truststore for client:

```
keytool -importcert \
  -file cert.cer \
  -alias my-https-cert \
  -keystore truststore.jks \
  -storepass changeit \
  -noprompt
```

Use this truststore in Java client:

```
System.setProperty("javax.net.ssl.trustStore", "truststore.jks");
System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
```

4. list all http status codes that related to authentication and authorization failures.

401 - Unauthorized: Client failed to authenticate. Token missing, invalid, or expired.

407	- Proxy Authentication Required: Similar to 401, but for proxies. The client must authenticate with a proxy.

403	- Forbidden: Authenticated, but not allowed to access the resource (permission denied).

5. Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization

| Aspect                | Authentication                        | Authorization                           |
| --------------------- | ------------------------------------- | --------------------------------------- |
| **Meaning**           | Verifies **who** the user is          | Determines **what** the user can access |
| **Goal**              | Confirm identity (login)              | Check permissions (access control)      |
| **Occurs**            | First                                 | After authentication                    |
| **Example**           | Logging in with username and password | Allowing only admins to delete users    |
| **HTTP Code on Fail** | `401 Unauthorized`                    | `403 Forbidden`                         |

Authentication Components:

* SecurityFilterChain: Intercepts HTTP requests and applies security filters

* AuthenticationManager: Central component that processes authentication requests

* AuthenticationProvider: Validates user credentials (e.g. against a DB, LDAP, JWT)

* UserDetailsService: Loads user-specific data from a custom source (DB, etc.)

* UserDetails: Represents a user (username, password, roles, etc.)

* UsernamePasswordAuthenticationToken: Carries username/password credentials during authentication

The typical flow:
SecurityFilterChain → AuthenticationManager → AuthenticationProvider → UserDetailsService

Authorization Components:

* AccessDecisionManager: Decides if the authenticated user has access to a secured resource

* GrantedAuthority: Represents a permission or role (e.g. ROLE_ADMIN, READ_PRIVILEGE)

* @PreAuthorize, @Secured, @RolesAllowed: Annotations for method-level security

* SecurityContext: Holds the Authentication object for the current request/session

Authentication + Authorization Example Flow:

* A user makes a request to a secured endpoint.

* SecurityFilterChain intercepts it and sends credentials to AuthenticationManager.

* AuthenticationProvider uses UserDetailsService to load the user.

* If credentials are valid, an Authentication object is stored in SecurityContext.

6. Explain HTTP Session?

An HTTP session is a way to maintain state (user-specific data) across multiple HTTP requests in a web application.

How Session Works:

* Client sends first request (e.g. logging in)

* Server creates a session object and assigns it a unique Session ID

* Session ID is sent to the client (usually via a Set-Cookie: JSESSIONID=abc123)

* On subsequent requests, the client sends the session ID back (via Cookie: JSESSIONID=abc123)

* The server uses this ID to look up the session and restore user-specific data

7. Explain Cookie?

A cookie is a small piece of data stored on the client's browser and sent to the server with each HTTP request to maintain state across otherwise stateless HTTP connections.

How Cookies Work:

Server sends a cookie:

```
Set-Cookie: username=claire; Max-Age=3600; Path=/; HttpOnly; Secure
```

Browser stores it, and then sends it with every request to that domain:

```
Cookie: username=chenchenchen99
```

8. Compare Session and Cookie?

| Feature                | **Session**                                  | **Cookie**                                              |
| ---------------------- | -------------------------------------------- | ------------------------------------------------------- |
| **Storage Location**   | Stored on the **server**                     | Stored on the **client (browser)**                      |
| **Data Stored**        | Complex user data (objects, etc.)            | Small key-value pairs (usually < 4KB)                   |
| **Data Size Limit**    | Large (limited by server memory)             | Small (typically max 4KB total per domain)              |
| **Security**           | More secure (data not exposed to user)       | Less secure unless flags like `HttpOnly`, `Secure` used |
| **Lifespan**           | Ends when user logs out or session times out | Can be persistent or session-only (based on expiration) |
| **Use Case**           | Login state, cart contents, user objects     | User preferences, language, tracking ID, auth token     |
| **Sent with request?** | Only session ID (e.g. `JSESSIONID`) is sent  | Entire cookie is sent with **every HTTP request**       |
| **Server Load**        | Uses server memory/storage                   | No load on server to store data                         |
| **Tamper Risk**        | Low – data stays server-side                 | Higher – unless encrypted or signed                     |

Session:

The server creates a session (with a session ID).

Stores data like login status, cart, etc. on the server.

Cookie:

The server sends a cookie with the session ID (e.g. JSESSIONID).

Browser stores and sends the cookie on future requests.

Server uses the session ID to retrieve the user’s session.

**Cookie stores the session ID; the session stores the actual data.**

9. Find at least TWO websites who can be logged in using your Google Account, explain in detail on how Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool:

![](interviewbit.png)

![](medium.png)

10. How do we use session and cookie to keep user information across the the application?

* User logs in with credentials (e.g., /login).

* Server authenticates the user and creates a HttpSession.

* Server stores user info in the session (session.setAttribute(...)).

* Server sends back a cookie (e.g. Set-Cookie: JSESSIONID=abc123).

* On every future request, browser sends this cookie (Cookie: JSESSIONID=abc123).

* Server uses the session ID to fetch the associated session and user info.

11. What is the spring security filter?

In Spring Security, a filter is a component that intercepts HTTP requests and applies security logic (e.g., authentication, authorization, CSRF, session checks) before the request reaches your controller. It’s part of the Servlet Filter Chain and runs before your application logic.

12. Explain bearer token and how JWT works.

A Bearer token is an access token sent by the client to the server in the Authorization header of an HTTP request.

A JWT is a compact, self-contained token format that’s often used as a Bearer token. It contains information (claims) that the server can verify and trust.

JWT Flow (Stateless Authentication)
* User logs in with username/password.

* Server authenticates and creates a JWT, signed with a secret key.

* JWT is returned to client (as a Bearer token).

* On future requests, client includes the token: Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...

* Server verifies:

	* The signature is valid

	* The token isn’t expired

	* Extracts user info (e.g. roles)

13. Explain how do we store sensitive user information such as password and credit card number in DB?

Storing Passwords: Hashing with Salt. Use a strong one-way hashing algorithm with a unique salt for each user.

Storing Credit Card Numbers: Use a third-party payment processor (e.g., Stripe, PayPal) to avoid storing card data directly.

Use Strong Encryption (Not Hashing):

* Encryption is reversible — needed to charge cards again.

* Use AES-256 or a secure encryption library.

* Use per-record IV (Initialization Vector) for each encryption.

14. Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?(把这⼏个名字看熟悉也⾏)

| Component                | Role                                                            | When It’s Used                                | Implements/Extends                                                                            |
| ------------------------ | --------------------------------------------------------------- | --------------------------------------------- | --------------------------------------------------------------------------------------------- |
| `UserDetailsService`     | Loads user data (username, password, roles) from DB or memory   | During authentication                         | Interface you implement (`loadUserByUsername`)                                                |
| `AuthenticationProvider` | Validates credentials (e.g., password check)                    | Core of authentication                        | You can customize by implementing `AuthenticationProvider`                                    |
| `AuthenticationManager`  | Delegates to one or more `AuthenticationProvider`s              | Orchestrates the whole authentication process | Interface, default: `ProviderManager`                                                         |
| `AuthenticationFilter`   | Captures login requests and triggers the authentication process | Front-facing filter for login endpoints       | Usually subclass of `UsernamePasswordAuthenticationFilter`, or `OncePerRequestFilter` for JWT |

15. What is the disadvantage of Session? how to overcome the disadvantage?

**Server Memory Usage**

* Each active session is stored in memory on the server.

* As the number of users grows, so does memory usage, potentially leading to performance issues or crashes.

Solution:

* Use stateless authentication (e.g., JWT) to offload session state from server memory.

* Or, distribute session storage using a centralized store like Redis or Hazelcast in distributed environments.

**Not Scalable for Distributed Systems**

* Session data is tied to a single server (by default).

* In load-balanced or microservices architecture, if the user's request hits a different server, the session may be lost.

Solution:

* Use a shared session store (e.g., Redis, Memcached) across all instances.

* Or better: switch to stateless auth (JWT) so each request is self-contained and doesn’t rely on server state.

**Session Fixation and Hijacking Vulnerability**

* If not handled properly, attackers can hijack or reuse valid session IDs.

Solution:

* Regenerate session IDs upon login.

* Use HttpOnly, Secure, and SameSite attributes in cookies.

* Implement session expiration and IP/user-agent validation.

**Session Timeout and User Experience**

* If a session expires too quickly, users are logged out and lose progress.

* If too long, it’s a security risk.

Solution:

* Balance session timeout with user expectations.

* Consider refresh tokens or re-authentication flows.

**Stateful Nature**

* Each user session is tied to a specific context — not RESTful.

* Violates the statelessness principle of REST APIs.

Solution:

* Use token-based authentication (JWT) for REST APIs.

* Keep server-side state minimal or eliminate it entirely for APIs.

**Hard to Scale with CDNs or Edge Networks**

* Session-based auth doesn’t work well when requests are routed through CDNs, which expect stateless requests.

Solution:

* Again, switch to stateless JWT stored in an Authorization: Bearer header or HttpOnly cookie.

16. how to get value from application.properties in Spring security?

In Spring Security (and Spring in general), can retrieve values from application.properties using the @Value annotation or by using @ConfigurationProperties

Using @Value

```
# application.properties
security.jwt.secret=mySuperSecretKey


@Component
public class JwtConfig {
    
    @Value("${security.jwt.secret}")
    private String jwtSecret;

    public String getJwtSecret() {
        return jwtSecret;
    }
}

@Configuration
public class SecurityConfig {
    
    private final JwtConfig jwtConfig;

    public SecurityConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // Use jwtConfig.getJwtSecret() wherever needed
}
```

Using @ConfigurationProperties

```
# application.properties
security.jwt.secret=mySuperSecretKey
security.jwt.expiration=3600000

@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    private String secret;
    private long expiration;

    // Getters and setters
}

@EnableConfigurationProperties(JwtProperties.class)
@Configuration
public class SecurityConfig {
    
    private final JwtProperties jwtProperties;

    public SecurityConfig(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // Now use jwtProperties.getSecret(), jwtProperties.getExpiration()
}
```

17. What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?

configure(HttpSecurity http): Authorization Configuration

This method configures how HTTP requests are secured, i.e., which resources require authentication/authorization, what type of login mechanism to use, session handling, CSRF, CORS, etc.

Roles:

* Define URL access rules (who can access what)

* Configure login form or HTTP Basic auth

* Configure logout

* Configure CSRF, CORS

* Add filters (e.g. JWT filters)

configure(AuthenticationManagerBuilder auth): Authentication Configuration

This method sets up how users are authenticated: using in-memory users, a database, LDAP, or custom UserDetailsService.

Roles:

* Configure in-memory users (for quick testing)

* Set up a custom UserDetailsService

* Define AuthenticationProviders

* Attach password encoders (like BCrypt)

18. Reading, 泛读⼀下即可，⾃⼰觉得是重点的，可以多看两眼。https://www.interviewbit.com/spring-security-interview-questions/#is-security-a-cross-cutting-concern

	1. 1-12
	2. 17 - 30

19. Explain best practices to securely store secrets in applications.

	1. Avoid Hardcoding Secrets in Code

	* Hardcoded secrets in source code can be easily leaked (e.g., via GitHub).

	* Use environment variables, configuration files outside version control, or secret managers.

	2. Use Environment Variables (with Caution)

	* Set secrets as environment variables during deployment (e.g., in Docker, Kubernetes, etc.).

	* Be sure not to log or print env values.

	* Be aware: environment variables can still be accessed by other processes if not isolated.

	3. Use a Secret Management System

	These tools:

	* Provide fine-grained access control

	* Enable auditing

	* Support automatic rotation of secrets

	4. Encrypt Secrets at Rest

	* If secrets are stored in files (e.g., application.properties, YAML, etc.), encrypt them using strong algorithms like AES-256.

	* Use a secure keystore (e.g., JKS or PKCS12) for symmetric keys.

	5. Limit Access to Secrets

	* Follow the principle of least privilege.

	* Only specific services or users should have access to particular secrets.

	* Use RBAC (Role-Based Access Control) in cloud or secret manager systems.

	6. Audit and Rotate Secrets Regularly

	* Rotate passwords, API keys, and tokens frequently to reduce impact of potential leaks.

	* Enable logging and monitoring of secret access.

	7. Use .gitignore and Git Hooks

	* Exclude secret files (.jks, .env, etc.) from Git using .gitignore.

	* Use pre-commit hooks (e.g., GitLeaks) to scan for secrets before committing code.

	8. Secure Your Build and Deployment Pipeline

	* Use secret management integrations in CI/CD tools (e.g., GitHub Actions Secrets, GitLab CI Variables).

	* Ensure secrets are not exposed in logs or UI.

	9. Avoid Using Shared Secrets

	* Use unique secrets per environment, per user, or per app.

	* Never reuse credentials across systems or services.

	10. Don't Disable SSL/TLS to "Simplify" Secrets Use

	* Always send secrets (e.g., during login or API requests) over HTTPS.

	* Avoid --insecure or disabling cert validation unless absolutely necessary and never in production.







