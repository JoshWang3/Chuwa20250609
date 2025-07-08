API is a set of rules and protocols that allows different software applications to communicate and interact with each other.

Benefits:
1. Allow working at high level without worrying about low-level implementations.
2. Provide pre-built modules that can be reused to communicate between microservices.
3. Provide public interface that avoid changes to sensitive data fields.

A developer API is a library or framework interface that you link into your own application. It is more used on classes, methods, data types, etc. that can be reused by others.
An application API is designed around remote interaction between discrete applications or services (often based on HTTP). It is more used in cases where we need to exchange data and the format is usually in JSON, XML, etc.

Some other APIs are:
1. REST API
2. GraphQL API
3. gPRC API
4. Streaming API (websocket)
5. Java Collections framework
6. Database API(JDBC)
7. Cloud API (AWS S3)

Path Variable:
1. Identify specific resources (/user/{userId})
2. Usually mandatory
3. Maps to request parameters with matching name

Request Parameter:
1. Modify the request behavior or provide additional context. (/user?page=2)
2. Can be optional
3. Maps to URI template variables

Components of Restful API:
1. Resources
2. URI/Endpoint
3. HTTP methods
4. Path variables and request parameters
5. Status code

cURL is a lightweight command-line utility that allows data transfer
While cURL is great for quick, simple API tests, and debugging, Postman provides a graphical UI for easier handling, grouping of API calls and built-in debugging.

HTTP status code:
1. 2xx(Success)
2. 3xx(Redirect)
3. 4xx(Client Error)
4. 5xx(Server Error)

HTTP methods:
1. GET: Used to retrieve data from the server. 200 OK, 404 Not Found
2. POST: Used to create a new resource with data provided. 201 Created, 400 Bad Request, 404 Not Found, 409 Conflict
3. PUT: Used to update or replace an existing resource with data provided. 200 OK, 204 No Content, 400 Bad Request, 404 Not Found
4. DELETE: Used to remove a resource from the server. 200 OK, 204 No Content, 404 Not Found

Stateless means each HTTP request from client to server must contain all the information needed to fulfill the request. The server does not store any context or state between requests.

Best practices in API design:
1. Optimize query: only retrieve necessary information.
2. Optimize payload size: Use Pagination to split up large data.
3. Caching
4. Asynchronous processing if needed
5. Optimize network: Load-Balancing, CDN

XSS is a type of injection attack where an attacker injects malicious scripts into a legitimate website.
CSRF is an attack that tricks a user into performing unwanted actions on a website where they are currently authenticated.

Avoid XSS:
1. Implementing Content Security Policy (CSP) 
2. Setting HttpOnly
3. Validate and sanitize all user input (encoding before displaying)

Avoid CSRF:
1. Issue session-based token
2. Use SameSite
3. Use CORS
4. Check header