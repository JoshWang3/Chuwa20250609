# Short Questions

1. **What is an API and why do we need it?**  
An API is like a waiter between your app and another service—it takes your request, talks to the kitchen (the service), and brings back what you asked for. We use APIs to keep our apps organized: each part does its own job without revealing all the details.

2. **Developer API vs Application API**  
- **Developer API**: A code library or SDK you include directly in your project.  
- **Application API**: A set of network endpoints (like REST or gRPC) you call over HTTP.

3. **Different types of APIs**  
- **By protocol**: REST, SOAP, gRPC, GraphQL  
- **By access**: Public, Private, Partner  
- **By domain**: Payment, Geolocation, Social Media, etc.

4. **Path Variables vs Request Parameters**  
- **Path Variables** go in the URL path (e.g., `/users/123`) and identify a specific resource.  
- **Request Parameters** appear after `?` (e.g., `?sort=asc&page=2`) and tweak how the request works.

5. **Key parts of a RESTful API**  
- **Endpoint (URL)**: Where you send your request (e.g., `/orders/{id}`).  
- **HTTP Method**: What you want to do (GET, POST, PUT, DELETE).  
- **Headers**: Extra info (like `Content-Type` or auth tokens).  
- **Body**: Data you send (for POST/PUT).  
- **Response**: What the server returns, including status code and data.

6. **cURL vs Postman**  
- **cURL**: Command-line, scriptable, lightweight.  
- **Postman**: GUI, easy to test and organize, supports environments and tests.

7. **Common HTTP status codes**  
- 200 OK, 201 Created, 204 No Content  
- 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found  
- 500 Internal Server Error, 503 Service Unavailable

8. **HTTP methods & expected responses**  
- **GET** → 200, 404  
- **POST** → 201, 400  
- **PUT** → 200, 204, 400  
- **PATCH** → 200, 204, 400  
- **DELETE** → 204, 404  
- **OPTIONS** → 200

9. **Why REST APIs are stateless**  
Each request carries all the info the server needs. The server doesn’t remember past requests, making it easier to scale and more fault-tolerant.

10. **Performance tips for REST APIs**  
- Paginate and filter data.  
- Enable gzip compression.  
- Use caching (Cache-Control, ETag).  
- Load balance and use CDNs.  
- Optimize database (indexes, read/write splitting).  
- Batch multiple operations when possible.

11. **XSS vs CSRF and how to stop them**  
- **XSS** lets attackers inject scripts into pages. Prevent it by sanitizing inputs, escaping output, and using a strict Content Security Policy.  
- **CSRF** tricks logged-in users into making unwanted requests. Defend with CSRF tokens, checking origin/referrer, and requiring re-authentication for critical actions.
