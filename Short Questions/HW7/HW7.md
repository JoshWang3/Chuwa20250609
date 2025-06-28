# 06/27

1. API is a a contract that allows software systems to communicate with each other. It defines how different software components should interact through method signatures, request formats, and response formats. It’s a bridge that allows two systems to talk to each other safely, efficiently, and in a structured way. 
    
    Reasons why we need APIs:
    
    1. APIs allow different systems or apps to communicate with each other.
    2. APIs can manage the access of users/clients. Users can’t view the internal code.
    3. It’s good for reuse.
2. Developer API VS Application API
    1. Developer APIs is for external developers while application API is for internal teams (including frontend apps).
    2. Developer API usually is viewed by public developer portal while application API is viewed by internal API docs/interface specs.  
3. Types of APIs based on different categories:
    1. Usage Level:
        1. open api
        2. partner api
        3. internal api
        4. composite api
    2. Communication:
        1. rest api
        2. soap api
        3. GraphQL api
        4. WebSocket api
    3. Technical
        1. web api - expose HTTP endpoints to clients
        2. library api - programming languages methods exposed to users
        3. database api
        4. hareware api 
4. Path Variables VS Request Parameters
    1. path variables is part of the URL path while request parameters is shown after `?` in the URL
    2. path variables identifies a specific resource while request parameters is used to filter, sort, or customize the resource.
    3. path variables is defined in `@PathVariable` using URI template while request parameters is defined in `@RequestParam` using query string. 
    4. path variables is good for identifying unique resource/entity while request parameters is good for sending optional parameters, filter, pagination, etc. 
5. RESTful API (REpresentational State Transfer) follows design principles to allow stateless, scalable communication between client and server. 
    1. HTTP Methods (action - verb): `GET`(Retrieve data), `POST`(Create a new resource), `PUT`(Update a full resource), `PATCH`(Update part of a resource), `DELETE` (Remove a resource).
    2. Resources (URIs - nouns) - uniquely identify entities via URLS. `/users`, `/orders`. 
    3. Path Variables - dynamic values to identify a specific resource. `GET /products/123`  - `123` is a path variable.
    4. Query Parameters - used for filtering, sorting or pagination. `GET /products?category=books&page=2` - `category=books&page=2` is query parameters.
    5. Headers - carry metadata such as content type, auth token, etc. `Content-Type`, `Authorization`, `Accept`
    6. Request body - holds data sent with HTTP methods
        
        ```java
        {
          "name": "Alice",
          "email": "alice@example.com"
        }
        ```
        
    7. Response Codes (HTTP Status) - `200`, `400`, `401`, `404`, etc.
6. cURL (Client URL) is a command-line tool used to make HTTP requests to APIs or web servers, which allows developers to test and interact with REST apis by specifying methods, headers, request bodies, and so on. 
    
    ```java
    curl -X GET "https://api.example.com/users/1" -H "Authorization: Bearer token"
    ```
    
    Postman is more productive for manual testing, debugging, and collaboration through GUI-based application. It allows to save, group and reuse requests. It’s easy to set body and headers input. However, cURL must copy or write scripts for request management. It requires complex syntax. 
    
7. HTTP Status Code
    1. 1xx - informational
        1. 100 - continue
        2. 101 - switching protocols
    2. 2xx - success
        1. 200 - ok (request succeeded)
        2. 201 - created (resource successfully created)
        3. 204 - no content (success without body)
    3. 3xx - redirection
        1. 301 - moved permanently
        2. 302 - found (temporary redirect)
        3. 304 - not modified (cached content valid)
    4. 4xx - client error
        1. 400 - bad request (malformed input)
        2. 401 - unauthorized (missing/invalid auth)
        3. 403 - forbidden (access denied)
        4. 404 - not found (resource doesn’t exist)
        5. 409 - conflict (resource conflict like duplicate)
    5. 5xx - server error
        1. 500 - internal server error (generic server failure)
        2. 502 - bad gateway (invalid upstream response)
        3. 503 - service unavailable (server overloaded or down)
        4. 504 - gateway timeout (server didn’t respond in time)
8. HTTP Methods & Status Codes
    1. `GET` - retrieve a resource. 200 OK | 404 Not Found
    2. `POST` - create a new resource. 201 Created | 400 Bad Request | 409 Conflict
    3. `PUT` - update/replace a resource. 200 OK | 204 No Content | 404 Not Found
    4. `PATCH` - partial update to a resource. 200 OK | 204 No Content | 404 Not Found
    5. `DELETE` - remove a resource. 200 OK | 204 No Content | 404 Not Found
    6. `OPTIONS` - get supported HTTP methods. 200 OK | 204 No Content
    7. `HEAD` - retrieve metadata (no body). 200 OK | 404 No Found
9. REST API is stateless because each request from the client to the server must contain all the information needed to understand and process that request. The server doesn’t store any client context between requests. 
10. Best practices
    1. use pagination, filtering, and sorting to prevent over-fetch large datasets.
    2. use http caching via `Cache-Control`, `ETag`, `Last-Modified` to reduces unnecessary repeated calls and speeds up client responses. 
    3. Remove unused fields
    4. use asynchronous processing - use `202 Accepted` with background jobs when processing takes time. 
    5. monitor and rate limit to track performance with tools. We need to apply throttling to prevent abuse.  
11. XSS (Cross-site scripting) allows attackers to inject malicious scripts (usually JavaScript) into webpages viewed by other users. To prevent it, we can use content security policy (CSP). It’s better to avoid `eval()` or `innerHTML` when possible. We need to ensure escape HTML/JS/URL content properly. 
    
    CSRF (Cross-site Request Forgery) tricks users into unknowingly performing actions on authenticated websites (like bank transfer) by sending unauthorized requests. We can use CSRF token (spring security does this automatically), validate the `Origin` and `Referer` headers. It’s better to require re-authentication or CAPTCHA for sensitive actions. 
    

API Practice:

1. 
    
    ![Screenshot 2025-06-27 at 5.04.22 PM.png](06%2027%2021fb9ebb81cb80c7bd4dff4595c39636/Screenshot_2025-06-27_at_5.04.22_PM.png)