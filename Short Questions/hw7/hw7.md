## Question 1
An Application Programming Interface (API) is a set of rules and protocols that allows different software applications to communicate with each other. 

We need APIs to enable integration between different systems, allowing them to share data and functionality, which accelerates development and innovation.

## Question 2

- Developer API: A set of tools, documentation, and protocols provided for third-party developers to build applications that interact with a specific service
- Application API: An interface that allows applications to communicate with each other, often used to access web services or databases

## Question 3
Types of APIs:
REST APIs, SOAP APIs, GraphQL APIs, WebSocket APIs, and gRPC APIs.

## Question 4
Path Variables are part of the URL path used to identify a specific resource.
Request Parameters are key-value pairs appended to the end of a URL after a ? and are used to filter or sort a collection of resources.

## Question 5
Components of a REST API:
- Endpoint URL: The specific address where the API can be accessed. It defines the resource being requested.
- HTTP Method: The verb that indicates the desired action to be performed on the resource.
- Headers: Additional information sent with the request, such as authentication credentials or the expected content type of the response.
- Body: The data sent to the server, typically in JSON format, used when creating or updating a resource.

## Question 6
cURL is a command-line tool used for transferring data with URLs. 
Postman is often preferred because of the user-friendly interface, features of organization and collaboration, automatic testing and documentation capabilities.

## Question 7
- 200 OK: The request was successful.
- 201 Created: The request was successful, and a new resource was created.
- 204 No Content: The request was successful, but there is no additional information to send back.
- 400 Bad Request: The server could not understand the request due to invalid syntax.
- 401 Unauthorized: The client must authenticate itself to get the requested response.
- 403 Forbidden: The client does not have access rights to the content.
- 404 Not Found: The server cannot find the requested resource.
- 500 Internal Server Error: The server has encountered a situation it doesn't know how to handle.

## Question 8
- GET method: Retrieves a representation of a resource.
Expected status code: 200 OK.
- POST method: Creates a new resource.
Expected status code: 201 Created.
- PUT method: Replaces an existing resource with new data.
Expected status code: 200 OK or 204 No Content.
- DELETE method: Removes a resource. 
Expected status code: 204 No Content or 200 OK.

## Question 9
A REST API is stateless, meaning that the server does not store any information about the client's previous requests. 
Each request from a client to the server must contain all the information needed to understand and process the request. 

## Question 10
Best practices for designing REST APIs:
- Implement caching to reduce the number of requests to the server for frequently accessed data.
- For large datasets, return data in smaller chunks (pages) rather than all at once.
- Allow clients to request only the specific data they need through filtering and sorting parameters.
- Employ ETags in headers to enable conditional requests, avoiding the re-transfer of unchanged data.
- Avoid sending unnecessary data in the response body.

## Question 11
- XSS: An attack where a malicious script is injected into a trusted website. When an unsuspecting user visits the site, the script executes in their browser, potentially stealing their data or performing actions on their behalf.
Prevention: Prevention: Sanitize and validate all user input to escape or remove malicious characters. Use a Content Security Policy (CSP) to restrict the sources from which scripts can be loaded.

- CSRF: An attack that tricks a logged-in user into submitting a malicious request to a web application they are authenticated with. 
Prevention: Use anti-CSRF tokens. These are unique, unpredictable tokens that are included in each state-changing request. 

# API Practices:
## 1. GitHub REST API
### Good Practices
- Use clear and consistent naming conventions for endpoints.
- Implement versioning in the API to manage changes without breaking existing clients.

### Bad Practices
- For certain endpoints, the default response includes a large amount of data, not all of which may be relevant to the client's needs.
```bash 
curl -L \
  -H "Accept: application/vnd.github+json" \
  -H "X-GitHub-Api-Version: 2022-11-28" \
  https://api.github.com/users/octocat
```
![img_1.png](img_1.png)

### Request Headers
- Accept: application/vnd.github+json: Tells the server that the client expects a response in the GitHub JSON format.
- X-GitHub-Api-Version: 2022-11-28: Specifies the version of the GitHub API that the client is using, ensuring compatibility with the server's response format.

### Response Headers
- content-type: application/json; charset=utf-8: Indicates that the response body is in JSON format with UTF-8 encoding.
- x-ratelimit-limit: 60: The maximum number of requests the client can make in a 60-minute window.
- x-ratelimit-reset: 1672531200: The Unix timestamp when the rate limit will reset.

## 2. Google Maps API
### Good Practices
- Query Parameters for Input: The API correctly uses query parameters (origins, destinations, etc.) to supply the inputs for a GET request.
- Clear Response Format in URL: Including /json in the URL explicitly tells the server to return a JSON object. While effective, the more conventional REST practice is to use content negotiation via the Accept header (e.g., Accept: application/json).

### Bad Practices
- API Key in URL: The use of an API key as a query parameter (key=...) is the most significant deviation from modern security best practices. 

```bash
curl "https://maps.googleapis.com/maps/api/distancematrix/json?origins=95113&destinations=95050&key=AIzaSyB3enBaecXlHPOvC6ChMwth2T0ypKzTMKQ"
```
![img.png](img.png)

### Request Headers
- Host: maps.googleapis.com: Specifies the server to which the request is being sent.
- Accept: */*: Indicates that the client will accept any media type in the response.

### Response Headers
- Content-Type: application/json; charset=UTF-8: Correctly indicates that the response body is a JSON object with UTF-8 character encoding.
- Date: Fri, 27 Jun 2025 22:15:00 GMT: The timestamp when the server generated the response.
- Cache-Control: public, max-age=86400: Instructs caches that this response is public and can be stored for up to 24 hours (86400 seconds).
- Content-Length: ...: The size of the response body in bytes.
- X-XSS-Protection: 0: A security header that disables the browser's cross-site scripting filter.
- X-Frame-Options: SAMEORIGIN: A security header that prevents the page from being displayed in an iframe on a different domain.

## 3. JSON Placeholder API
### Good Practices
- Follows RESTful Principles: It uses resource-based, plural noun URLs (e.g., /posts, /users, /comments).
- Correct HTTP Method Usage: It supports GET, POST, PUT, PATCH, and DELETE for interacting with the fake data.
- Clear and Simple: The API is straightforward and easy to understand, making it ideal for its purpose as a testing tool.

### Suggested Better Design
More complex relationships between resources can add functionality.

![img_2.png](img_2.png)
### Request Headers
- Host: jsonplaceholder.typicode.com
- User-Agent: PostmanRuntime/7.28.4: Identifies the client making the request, which can be useful for debugging or analytics.

### Response Headers
- content-type: application/json; charset=utf-8: The response is JSON.
- cache-control: public, max-age=14400: The response can be cached for 4 hours.
- etag: W/"...": An identifier for the resource version.
- x-ratelimit-limit: 1000: Rate limiting information.

## 4. The Rick and Morty API
### Good Practices
- Single Endpoint: It uses a single endpoint (https://rickandmortyapi.com/graphql) for all data queries, as is standard for GraphQL.
- Rich Schema: It has a well-defined and documented schema that clearly outlines the available data types and queries.
- Intuitive and Discoverable: The nature of GraphQL makes the API highly discoverable. You can query the schema itself to understand what data is available.

### Suggested Better Design
Consider adding more data or features to the API.

```bash
curl 'https://rickandmortyapi.com/graphql' \
  -H 'Content-Type: application/json' \
  --data-raw '{"query":"query { characters(filter: { name: \"Rick Sanchez\" }) { results { id name status species episode { name } } } }"}'
```
![img_3.png](img_3.png)

### Request Headers:
Content-Type: application/json: Indicates that the request body is in JSON format.

### Response Headers:
content-type: application/json: The response is JSON.
cache-control: public, max-age=300: The response can be cached for 5 minutes.
access-control-allow-origin: *: Allows cross-origin requests.

## 5. PokéAPI
### Good Practices
- Resource-Oriented and Hierarchical URLs: The API uses a clear, noun-based, hierarchical structure that is easy to understand. 
- Correct Use of HTTP Methods: As a consumption-only API, it correctly uses the GET verb for all data retrieval operations.

### Suggested Better Design
The API could benefit from more detailed documentation, especially for complex data structures like abilities and moves.

```bash
curl -X GET "https://pokeapi.co/api/v2/pokemon/ditto"
```

### Request Headers (Implicit via cURL):
- Host: pokeapi.co: Specifies the domain name of the server where the request is being sent.
- User-Agent: curl/x.xx.x: Identifies the client software making the request.
- Accept: */*: A standard cURL header indicating that the client is willing to accept any media type in the response.

### Response Headers:
- Content-Type: application/json; charset=utf-8: Confirms that the response body is a JSON object with UTF-8 encoding.
- Date: Fri, 27 Jun 2025 23:12:00 GMT: The server timestamp for when the response was generated.
- Server: cloudflare: Indicates that the API is served via the Cloudflare network.
- Cache-Control: public, max-age=86400: It tells any client or intermediary cache (like a browser or CDN) that this response can be saved and reused for up to 24 hours (86400 seconds).
- ETag: W/"...": Provides an "entity tag," which is a unique identifier for the specific version of the resource. 