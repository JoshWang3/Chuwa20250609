# hw13 submission

## Q2: Explain TLS, PKI, certificate, public key, private key, and signature.

### Answer:
#### TLS (Transport Layer Security)
TLS is a cryptographic protocol that ensures secure communication over a network. It provides:

Encryption (prevents eavesdropping)

Integrity (prevents tampering)

Authentication (verifies the identity of the server/client)

In Java/Spring Boot, TLS is commonly used when you:

Access HTTPS endpoints

Secure REST APIs (via HTTPS)

Configure SSL for embedded Tomcat (server.ssl.* properties in application.properties)

#### PKI (Public Key Infrastructure)
PKI is a system that enables secure communication using asymmetric cryptography. It includes:

Certificate Authorities (CAs)

Digital Certificates

Key pairs (public/private)

Trust chains

Spring Boot apps use PKI when:

Validating a certificate presented by a client/server

Setting up mutual TLS (mTLS)

Public Key & Private Key

Private Key: Kept secret, used to sign data or decrypt data encrypted with the public key.

Public Key: Shared openly, used to verify signatures or encrypt data for the private key holder.

#### Certificate
A certificate (typically X.509 format) binds a public key to an identity, signed by a CA. It includes:

Public key

Subject (identity)

Issuer

Validity period

Signature from the CA

In Spring Boot:

Use .jks or .p12 keystore to hold certificates

Configure with server.ssl.key-store, server.ssl.trust-store

#### Signature
A digital signature is a hash of the data encrypted with a private key. It ensures:

Authenticity: Confirms who sent the data

Integrity: Confirms data was not modified

Used in:

Signing certificates (by CA)

Verifying signed JWT tokens (e.g., in OAuth2)

Validating signed requests (e.g., in SAML or webhooks)

## Q3

### Answer:
#### Can You Verify HTTPS Without Importing the Cert?
No, because:

My self-signed certificate is not trusted by the system.

Browsers and tools like Postman will reject it unless:

I import the certificate into your system trust store.

Or disable SSL verification (which are not allowed to do here).

#### What Did You Do to Make HTTPS Work?
I generated a self-signed certificate using keytool and saved it as keystore.jks inside src/main/resources/.

I configured Spring Boot to use HTTPS (TLS) on port 8443 by referencing the keystore.jks file and providing the alias and password.

I created a basic GET API at /api/secure to test secure communication.

I tested the API using curl and Postman. As expected, HTTPS requests fail verification because the self-signed certificate is not trusted by default.

I did not bypass TLS verification in Postman. Instead, I analyzed why the request was rejected and explained the certificate trust mechanism.

## Q4: list all http status codes that related to authentication and authorization failures.

### Answer:
#### Authentication Failures (identity verification problems)
| Status Code                           | Meaning                                 | Use Case                                                                           |
| ------------------------------------- | --------------------------------------- | ---------------------------------------------------------------------------------- |
| **401 Unauthorized**                  | The user is not authenticated           | - No or invalid token<br>- Invalid credentials<br>- Missing `Authorization` header |
| **407 Proxy Authentication Required** | Authentication with a proxy is required | Rare; mostly in enterprise/proxy scenarios                                         |


####  Authorization Failures (permission problems)
| Status Code                                     | Meaning                                              | Use Case                                                                                   |
| ----------------------------------------------- | ---------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **403 Forbidden**                               | Authenticated but not allowed to access the resource | - User lacks required role or scope<br>- JWT is valid, but not authorized for the endpoint |
| **419 Authentication Timeout** *(non-standard)* | Session expired                                      | - Custom front-end/backend implementation<br>- Used in some frameworks (e.g., Laravel)     |


####  Other Related 
| Status Code                  | Meaning                                  | Use Case                                           |
| ---------------------------- | ---------------------------------------- | -------------------------------------------------- |
| **400 Bad Request**          | Malformed authentication input           | - Missing or invalid parameters in login request   |
| **422 Unprocessable Entity** | Correct format, but semantically invalid | - Valid JSON but incorrect email/password          |
| **429 Too Many Requests**    | Rate limit exceeded                      | - Too many login attempts (brute-force protection) |


#### Spring Boot Example
In Spring Security:

A 401 is usually returned by the AuthenticationEntryPoint

A 403 is returned by the AccessDeniedHandler

You can customize them like this:

```
http
.exceptionHandling()
.authenticationEntryPoint((req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
.accessDeniedHandler((req, res, ex) -> res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden"));
```

## Q5: Compare authentication and authorization? Name and explain important components in Spring security that undertake authentication and authorization

### Answer:
#### Compare Authentication and Authorization
| Concept           | Authentication                          | Authorization                                      |
| ----------------- | --------------------------------------- | -------------------------------------------------- |
| **Definition**    | Verifying *who* the user is (identity)  | Verifying *what* the user can do (access)          |
| **When**          | Happens **before** authorization        | Happens **after** authentication                   |
| **Example**       | Logging in with username/password       | Checking if the user has `ROLE_ADMIN`              |
| **Spring Result** | Returns a valid `Authentication` object | Applies access rules using `AccessDecisionManager` |

#### Authentication-related components
AuthenticationManager

Central interface for authentication

It receives the credentials and returns an Authentication object if successful.

```
Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
```

AuthenticationProvider

Validates the authentication request.

You can create custom providers (e.g., for JWT or OAuth2).

UserDetailsService

Loads user-specific data from DB or memory.

Returns a UserDetails object with username, password, roles, etc.

```
@Service
public class CustomUserDetailsService implements UserDetailsService {
@Override
public UserDetails loadUserByUsername(String username) {
return new User(username, password, authorities);
}
}
```

SecurityContext + SecurityContextHolder

Stores the authenticated user's Authentication object for the current session/thread.

#### Authorization-related components
AccessDecisionManager

Makes final decisions whether access should be granted, based on roles/permissions.

AccessDecisionVoter

Votes on whether access should be granted, based on annotations like @PreAuthorize("hasRole('ADMIN')")

@PreAuthorize, @Secured, @RolesAllowed

Method-level security annotations.

Example:

```
@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }
```

FilterSecurityInterceptor

Intercepts web requests and enforces access control using security metadata and roles.

## Q6: Explain HTTP Session?

### Answer:
An HTTP session is a server-side mechanism that allows web applications to store user-specific state across multiple HTTP requests.

Since HTTP is stateless by design, sessions help maintain continuity (e.g., after login).

How It Works:

Client logs in → Server creates a new session object.

Server generates a unique Session ID and sends it back to the client via a cookie (usually JSESSIONID).

For every subsequent request, the client sends this session ID in the cookie header.

Server retrieves the session object using this ID to maintain user state (e.g., login status, cart items).

## Q7: Explain Cookie?

### Answer:
A cookie is a small piece of data stored on the client-side (browser) and sent to the server with every HTTP request to the same domain.

How Cookies Work:

Server sends a Set-Cookie header in the HTTP response.

Browser stores the cookie.

On future requests, the browser sends the cookie back using the Cookie header.

## Q8: Compare Session and Cookie?

### Answer:
| Feature              | **Session**                                                      | **Cookie**                                                |
| -------------------- | ---------------------------------------------------------------- | --------------------------------------------------------- |
| **Storage Location** | Stored on the **server**                                         | Stored on the **client (browser)**                        |
| **Size Limit**       | No strict limit (depends on server memory)                       | \~4KB per cookie                                          |
| **Security**         | More secure – data not exposed to client                         | Less secure – data visible on client                      |
| **Use Case**         | Store **sensitive or large** user state (e.g., login info, cart) | Store **lightweight** info (e.g., language, theme)        |
| **Speed**            | Slightly slower (requires server read)                           | Faster (stored on client side)                            |
| **Scalability**      | Needs server memory or external store (Redis, DB)                | Scales easily – no server memory usage                    |
| **Dependency**       | Requires **session ID in cookie** or URL                         | Self-contained – client sends cookie each time            |
| **Expires**          | Automatically after timeout or logout                            | Controlled by `Expires` or `Max-Age`                      |
| **Tamper Risk**      | Low – data stays on server                                       | High – client can tamper unless encrypted/signed          |
| **Access via JS?**   | Not accessible in JavaScript                                     | Can be accessed via `document.cookie` (unless `HttpOnly`) |


## Q9: Find at least TWO websites who can be logged in using your Google Account, explain in detail on how Google SSO works with screenshots like below, find SSO-related Rest calls in Chrome developer tool:

### Answer:
#### Example 1: Medium (https://medium.com/)
Key SSO-related Calls:

https://accounts.google.com/o/oauth2/v2/auth

→ Redirect to Google for authentication

https://www.googleapis.com/oauth2/v3/token

→ Exchange authorization code for access token

https://medium.com/_/api/users/google

→ Medium receives Google token and logs you in

#### Example 2: Trello (https://trello.com/)
SSO-Related REST Calls:

After filtering network requests:

https://accounts.google.com/o/oauth2/v2/auth

→ Authorization Request

https://oauth2.googleapis.com/token

→ Token exchange (Auth Code → Access Token)

https://trello.com/1/login/google

→ Trello sends the token to authenticate the user

## Q10: How do we use session and cookie to keep user information across the the application?

### Answer:
#### Approach 1: Using Session
User logs in

Server authenticates and creates a session (e.g., HttpSession)

Server stores user info (e.g., username or User object) in the session

Server sends a session ID to the browser via a Set-Cookie header (e.g., JSESSIONID=xyz)

On every request, browser sends this session ID automatically in the Cookie header

Server retrieves the session using the ID and gets user info

```java
@PostMapping("/login")
public String login(@RequestParam String username, HttpSession session) {
// Validate user...
session.setAttribute("username", username);
return "Login successful";
}

@GetMapping("/profile")
public String profile(HttpSession session) {
String user = (String) session.getAttribute("username");
return "Welcome, " + user;
}
```

#### Approach 2: Using Cookie Directly
User logs in

Server sets a cookie (e.g., user=shaobo)

On every request, browser sends the cookie

Server reads the cookie and uses it to identify the user

Security Consideration: Don't store sensitive data (e.g., passwords) directly in cookies. Instead, store a token or session ID.

```java
@PostMapping("/login")
public void login(@RequestParam String username, HttpServletResponse response) {
Cookie cookie = new Cookie("user", username);
cookie.setHttpOnly(true);
cookie.setMaxAge(3600); // 1 hour
response.addCookie(cookie);
}

@GetMapping("/profile")
public String profile(@CookieValue("user") String username) {
return "Welcome, " + username;
}
```

## Q11: What is the spring security filter?

### Answer:
The Spring Security filter is a key component in the security filter chain that intercepts HTTP requests before they reach your controller. It applies authentication, authorization, CSRF protection, and other security logic.

Spring Security configures a chain of servlet filters, each performing a specific role in securing the app.

## Q12: Explain bearer token and how JWT works.

### Answer:
#### Bearer Token
A Bearer token is a type of access token sent in the HTTP Authorization header to prove the client's identity to the server.

The word "Bearer" means: "The person holding this token is authorized."

No username/password is needed — just the token.

Bearer tokens are commonly used with OAuth2 and JWT (JSON Web Token).

#### JWT (JSON Web Token)
A JWT is a compact, self-contained, and signed token used to securely transmit information between parties.

Structure:
A JWT has three parts, separated by dots:

HEADER.PAYLOAD.SIGNATURE

## Q13: Explain how do we store sensitive user information such as password and credit card number in DB?

### Answer:
#### Storing Passwords: Never Store Plain Text
Best Practice: Hash the password before storing

Use strong one-way hashing algorithms

Never store raw passwords, even encrypted

Include salt to prevent rainbow table attacks

#### Storing Credit Card Numbers: Avoid if Possible
Preferred Approach:

Never store full credit card numbers unless you’re PCI DSS compliant

Instead, use payment gateways like Stripe, PayPal, or Square

Store only last 4 digits, expiration date, and a payment token returned by the provider

## Q14: Compare UserDetailService, AuthenticationProvider, AuthenticationManager, AuthenticationFilter?

### Answer:
| Component                    | Role                                                              | Who Calls It?                                | Customizable?                                                                   | Example                                              |
| ---------------------------- | ----------------------------------------------------------------- | -------------------------------------------- | ------------------------------------------------------------------------------- | ---------------------------------------------------- |
| **`UserDetailsService`**     | Loads user details (e.g., from DB) by username                    | Called by `AuthenticationProvider`           | ✅ Yes – implement `loadUserByUsername`                                          | Fetch user from DB and return a `UserDetails` object |
| **`AuthenticationProvider`** | Verifies user credentials (auth logic)                            | Called by `AuthenticationManager`            | ✅ Yes – implement `authenticate()`                                              | Compares raw password with hashed one                |
| **`AuthenticationManager`**  | Delegates authentication to one or more `AuthenticationProvider`s | Called by `AuthenticationFilter`             | ✅ Yes – inject providers                                                        | Chooses which provider to use (e.g., DAO, JWT)       |
| **`AuthenticationFilter`**   | Intercepts HTTP requests to extract credentials                   | Called automatically in the **filter chain** | ✅ Yes – extend `UsernamePasswordAuthenticationFilter` or `OncePerRequestFilter` | Extract username/password or token from the request  |

## Q15: What is the disadvantage of Session? how to overcome the disadvantage?

### Answer:
#### Scalability Issues
Session data is stored in memory on the server by default.

In a distributed (multi-server) environment, user sessions don’t automatically sync between servers.

Solution:

Use a centralized session store (e.g., Redis, JDBC, or Hazelcast) with Spring Session.

Or use stateless authentication (like JWT), where no session is stored at all.

#### Memory Overhead
Each user session consumes server memory.

For high-traffic apps, this can cause OutOfMemoryErrors or performance degradation.

Solution:

Set session timeouts (e.g., 15–30 mins)

Store only minimal data in session (e.g., user ID, not full objects)

Use stateless tokens (JWT) where possible

#### Session Fixation Attacks
Attacker sets a known session ID before login, then hijacks the session after the victim logs in.

Solution:

Spring Security automatically changes the session ID after login:

#### Hard to Debug or Reproduce
Session data is stored server-side, making it hard to trace or replicate in debugging or testing.

Solution:

Log session creation and invalidation events.

Use token-based auth (like JWT) where data is client-visible and portable.

#### Doesn’t Work Well with Mobile or APIs
Sessions require cookie support, which may not be ideal or consistent in mobile apps or cross-domain API clients.

Solution:

Use stateless authentication with Bearer Tokens (JWT) for APIs and mobile clients.

## Q16: how to get value from application.properties in Spring security?

### Answer:
```java
```
#### Method 1: Use @Value in a Security Configuration Class
```
# application.properties
app.security.jwt.secret=MySuperSecretKey
```
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("JWT Secret: " + jwtSecret); // Use it here
        return http.build();
    }
}
```

#### Method 2: Use @ConfigurationProperties to Group Properties
```
# application.properties
app.security.jwt.secret=MySuperSecretKey
app.security.jwt.expiration=3600
```
```java
@Component
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {
private String secret;
private int expiration;

    // Getters and Setters
}
```

Then inject this into your Security config:
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProperties jwtProperties;

    public SecurityConfig(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String secret = jwtProperties.getSecret(); // Use it here
        return http.build();
    }
}
```

#### Method 3: Use Environment Object
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Environment env;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String jwtSecret = env.getProperty("app.security.jwt.secret");
        return http.build();
    }
}
```

## Q17: What is the role of configure(HttpSecurity http) and configure(AuthenticationManagerBuilder auth)?

### Answer:
```java
```
#### configure(HttpSecurity http)
This method is used to define security rules for HTTP requests — mainly authorization, form login, CSRF, CORS, and filter chain behavior.

Key Role: What access is allowed to whom
```java
@Override
protected void configure(HttpSecurity http) throws Exception {
http
.authorizeRequests()
.antMatchers("/public/**").permitAll()       // Public access
.antMatchers("/admin/**").hasRole("ADMIN")   // Role-based access
.anyRequest().authenticated()                // All other requests need login
.and()
.formLogin()
.loginPage("/login")                         // Custom login page
.and()
.logout()
.logoutUrl("/logout")                        // Logout config
.and()
.csrf().disable();                               // (if using APIs)
}
```

#### configure(AuthenticationManagerBuilder auth)
This method is used to define how authentication works — i.e., who the users are and how to validate credentials.

Key Role: Where and how to fetch users and passwords
```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication()
.withUser("user").password("{noop}password").roles("USER")
.and()
.withUser("admin").password("{noop}admin123").roles("ADMIN");
}
```

## Q19: Explain best practices to securely store secrets in applications.

### Answer:
```java
```
#### Never Hardcode Secrets in Source Code
Don’t do:
```
String dbPassword = "mySecretPassword";
```
Instead, externalize secrets using properties files or environment variables.

#### Use Environment Variables for Local Dev and CI/CD
Store secrets as environment variables, not in code or Git.

Example:
```
export DB_PASSWORD=super_secret_pass
```
In application.properties:
```
spring.datasource.password=${DB_PASSWORD}
```

#### Use a Secrets Manager in Production
Use cloud-native secret storage tools that provide:

Encryption at rest

Access control (IAM)

Automatic rotation

Example: AWS Secrets Manager with Spring Boot

#### Use Encrypted Configuration Files (Optional)
Tools like Jasypt allow encrypting values inside application.properties.
```
db.password=ENC(xYZEncryptedString)
```
You provide the decryption key via an environment variable:
```
export JASYPT_ENCRYPTOR_PASSWORD=someMasterKey
```

#### Use Fine-Grained Access Control
Only grant access to secrets on a need-to-know basis

Use least privilege principle

Use IAM roles in cloud environments

#### Enable Logging Controls
Avoid logging secrets by accident (e.g., token headers, passwords)

Use log masking and audit sensitive operations

#### Rotate Secrets Regularly
Change passwords, tokens, and keys periodically

Automate rotation if possible (e.g., AWS automatic secret rotation)
