### 2 TLS, PKI, cert, public key, private key, and signature
    transport layer security:
        crypt protocol that secures communication over network 
        encrypting data setn over https, email, etc
            data encryption 
            data integrity
            authentication

    public key infra
        a system for creating, managing, ditributing and revking digital cert
        it enables secure encrypted communication and digital signature using public/private keys
        its components:
            CA certificate authorities
            cert like SSL certs
            public/private key pairs
            CRLs certifcate revocation lists


    public key:
        a key to verify legit client which shared publicly for: 
            encrpted data that only mathcing private key can decrypt
            verify digit signature
        when visit a website (http address) browser gets the public key from the site's cert


    private key:
        a secret key kept secure by the onwer like id of the owner.never shared. and for:
            decrypt data encrypted by the owner
            sign data to prove auth and integrity

    signature:
        crypto hash of data encrypted with a private key. for
            prove that the data from expected user
            ensure the data has not be altered
        anyone with the public key can verify the signature is valid 


    Example Flow (TLS Handshake):
    Client connects to server via HTTPS.
    
    Server sends its certificate (includes public key).
    
    Client verifies it using PKI and a trusted CA.
    
    Client generates a session key, encrypts it with server’s public key, sends it.
    
    Server decrypts with its private key.
    
    Now both use the same session key for secure (symmetric) encryption.


### 3 https set up

    Can You Call HTTPS Without Trusting the Self-Signed Cert?
    No. You will get a "certificate not trusted" or Error: self signed certificate
    error unless:
    
    You import the certificate to your local trusted certificate store, OR
    
    You disable verification (DON'T do this in production)
    
    💡 Why?
    Self-signed certs are not issued by a trusted Certificate Authority (CA). Browsers/Postman won't trust it unless explicitly told to.

    extract crt from keytool and add to postman ca certificate
    now postman will trust my self signed cert without disabling ssl verification

![Screenshot 2025-07-16 at 3.32.31 PM.png](Screenshot%202025-07-16%20at%203.32.31%E2%80%AFPM.png)

![Screenshot 2025-07-16 at 3.31.51 PM.png](Screenshot%202025-07-16%20at%203.31.51%E2%80%AFPM.png)


### 4 all http code related auth and author
    401 unauthrorized 
    403 foribidden 


### 5 authentication vs authorization
    authentication: is to verify if whot is the user (identity is legit)
    authorization: is to verify if what the user is allowed to do

    AuthenticationProvider: an interface for validating user credentials.
        UserDetailsService. create custom one for api tokens, OAUth.

    AuthenticationManager: central engine for performing authentication
        inject by spring framework

### 6 http session 
    is a server-side mechanism that maintains stateful information across multiple
    stateless http reequests from the same client, typically by a session ID stored in a cookie.


### 7 cookie
    is a key value data stored on the client side by the browser that contains a session ID, which the
    server uses to identify, retrieve, and maintain the associated server-side http session state across 
    multiple stateless http requests.

### 8 session vs cookie 
    cookie is a key value data mechanism on client-side hold session ID to allow server to associate the 
    request with the corresponding session.
    session is on server-side mechanism for storing user specic data across multiple stateless http
    requests. 


### 9 google sso

    google sso (OAuth 2.9 + openID connect) 

    User clicks "Sign in with Google"

    The app redirects the user to https://accounts.google.com/o/oauth2/v2/auth
    
    This request includes:
    
    client_id
    
    redirect_uri
    
    scope (like openid, email, profile)
    
    state (for CSRF protection)
    
    response_type=code
    
    User authenticates on Google
    
    If the user is already logged in, they may not need to re-enter credentials.
    
    Google may show a consent screen asking for permission.
    
    Google redirects back to your app
    
    To a URL like:
    
    bash
    Copy
    Edit
    https://yourapp.com/oauth2/callback?code=AUTH_CODE&state=STATE
    This is where your backend receives the authorization code.
    
    Backend exchanges code for tokens
    
    Your app sends a POST request to:
    
    bash
    Copy
    Edit
    https://oauth2.googleapis.com/token
    Includes:
    
    code (from above)
    
    client_id, client_secret
    
    redirect_uri
    
    grant_type=authorization_code
    
    Google responds with tokens
    
    id_token (JWT with user info — OpenID Connect)
    
    access_token (used to call Google APIs)
    
    refresh_token (optional)
    
    Your app validates the ID token and authenticates the user

![Screenshot 2025-07-16 at 4.54.51 PM.png](Screenshot%202025-07-16%20at%204.54.51%E2%80%AFPM.png)



### 10 how do spring use session and cookie to keep user information across the application

    Spring Security uses a session to store the authenticated user's Authentication object on the server, 
    and a cookie (usually named JSESSIONID) on the client to reference that session in subsequent
    HTTP requests.


### 11 spring security filter
    A Spring Security filter is a Java servlet filter that intercepts HTTP requests and responses to 
    apply security logic, such as authentication, authorization, CSRF protection, 
    session handling, and more — before the request reaches your controller.

### 12 bearer token and JWT

    bearer (whoerver bears it gets access) token is a string give to client in authorization header for 
    offline authentication works like passport to
    get access to server 

    JWT is jason web token is specific kind of bearer token that contains self-contained, signed json
    data, such as uer identity and roles, encoded in base64 string.

    xxxxx.yyyyy.zzzzz
    |      |      |
    Header Payload Signature

### 13 explain how to store sensitive data like password/credit card number in DB
    no actual sensitive content should be stored. but a secure reference to secure server which host
    the content. method like tokenization for credit card, password hashing on valuts or secrete server.
    application holds token, server has the number, access path send to strip to charge. 

### 14 AuthenticationProvider, AuthenticationManager, UserDetailsService, AuthenticationFilter

    Component	Purpose	Role in Authentication Flow	Customizable	Interface/Type	When It Runs
    UserDetailsService	Loads user-specific data (e.g., username, password, roles)	Provides user info to AuthenticationProvider	✅ Yes	UserDetailsService	During user lookup
    AuthenticationProvider	Verifies credentials and produces an Authentication object	Authenticates a user with data from UserDetailsService	✅ Yes	AuthenticationProvider	After credentials submitted
    AuthenticationManager	Delegates authentication to one or more AuthenticationProviders	Coordinates the authentication process	⚠️ Usually uses defaults	AuthenticationManager	Top-level entry point
    AuthenticationFilter	Captures login requests from HTTP and sends them to AuthenticationManager	Entry point for incoming auth (e.g., login request)	✅ Yes	OncePerRequestFilter / AbstractAuthenticationProcessingFilter	Before reaching controllers


### 15 session disadvantage and how to overcome 
    1. statefulness: use stateless auth like JWT and token or Redis for distribute session store
    2. memory overhead: store in external in-memory store like redis
    3. session stickiness: JWT
    4. security risk: encrypted https
    5. difficult to invalidate sessions globally: JWT
    6. limited API/mobile compatibility: JWT / OAuth
    



### 16 how to get value from application.property in spring security 
    use @Value or Envoirnment class
    


### 17 role of configure (HttpSecurity http) and  configure(AuthenticationManagerBuilder auth)
    config security values and strategies at http level 
    like which URLs are secured, what authentication method to use, 
    and what additional filters or protections to 
    enable (e.g., CSRF, CORS, login/logout handling, session management).
    RL-based access rules

    Login and logout strategy
    
    HTTP Basic or Form login
    
    CSRF protection
    
    Session management
    
    Custom filters or exception handling


    build authenticationManager object with given parameters 
    Defines how to authenticate users — i.e., 
    where and how to retrieve user credentials and authorities (e.g., in-memory, from a database, via LDAP, etc.).
    


### 19. best practice to securely store secrets in apps
    1. never hard coded sensitive secrets 
    2. use enviornment variable 
    3. use secret management tools
    4. encrypt config files
    5. restrict access from IAM
    6. rotate secret content like password 
    7. audit and monitor secrete usage 
    
    

    








    
    