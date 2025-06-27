# hw7 submission

## Q1: Explain the concept of API (Application Programming Interface), why do we need APIs.

### Answer:
An API (Application Programming Interface) is a set of rules and protocols that allows one piece of software to communicate with another. APIs define the methods and data formats that programs can use to request and exchange information.

Why Do We Need APIs:

APIs hide the complex internal logic and expose only what’s necessary for the user or developer.

Allow different systems (built in different languages or platforms) to work together.

Developers can reuse existing functionality without building from scratch.

APIs allow controlled access to services or data, with permissions and rate limits.

APIs make it easy to scale and extend systems by connecting services modularly.

## Q2: Compare developer API vs application API (normal APIs)

### Answer:
A Developer API is a broad term that refers to any API intended for use by software developers. These APIs provide tools, services, or access to functionalities that developers can integrate into their own applications. Developer APIs are designed to be easy to use, well-documented, and consistent, helping developers build software more efficiently. For example, the Stripe API allows developers to integrate payment processing into their websites or apps.

An Application API is a more specific type of API provided by a particular software application. It allows developers to access or extend the core features of that application. For instance, the Google Docs API lets developers read and write documents stored in Google Docs, and the Salesforce API lets users interact with Salesforce data and services. These APIs are tightly tied to the functionality of the application they represent.

## Q3: Name some different types of APIs

### Answer:
1. REST API (Representational State Transfer)

REST APIs are the most widely used web APIs. They rely on standard HTTP methods like GET, POST, PUT, and DELETE, and typically return data in JSON format. REST is stateless and resource-based, making it simple and scalable.

2. GraphQL API

GraphQL is a query language developed by Facebook that allows clients to request exactly the data they need, and nothing more. Unlike REST, where multiple endpoints are used, GraphQL uses a single endpoint and gives clients more control over the response structure.

3. gRPC (Google Remote Procedure Call)

gRPC is a high-performance, open-source RPC framework developed by Google. It uses HTTP/2 and Protocol Buffers for compact and fast communication. It’s ideal for microservices and internal system communication.

4. WebSocket API

WebSocket APIs provide full-duplex, real-time communication between client and server over a single, long-lived connection. They are commonly used in applications like chat systems, gaming, or live data feeds.

5. MQTT (Message Queuing Telemetry Transport)

MQTT is a lightweight messaging protocol ideal for low-bandwidth, high-latency environments like IoT (Internet of Things). It uses a publish/subscribe model to enable efficient and reliable message exchange between devices.

6. MCP (Media Control Protocol)

MCP is used primarily in the context of multimedia communication systems. It allows control over media streams such as starting, stopping, or switching between audio and video sources. It is commonly found in VoIP and video conferencing systems.

7. SOAP API (Simple Object Access Protocol)

SOAP is a protocol that uses XML to encode messages and typically operates over HTTP or SMTP. It’s more strict and standardized than REST and is often used in enterprise systems that require high reliability and security.

8. Web API

This is a general term that refers to any API accessible over the web via HTTP/HTTPS. It can include REST, GraphQL, and even SOAP APIs, depending on how they are implemented.

## Q4: Compare path variables vs request parameters in REST API.

### Answer:
1. Path Variables

These are part of the URL path itself.

Used to identify a specific resource.

Defined in the URL pattern with curly braces (e.g., /users/{id}).

Common for resource lookup.

Example: GET /api/users/123

Here, 123 is a path variable representing the user ID.

2. Request Parameters

These are added to the end of the URL after a ?.

Used to filter, sort, or provide optional input.

Often used for searches, pagination, or sorting.

Example: GET /api/users?role=admin&sort=asc

Here, role=admin and sort=asc are request parameters.

## Q5: Explain the different components that make up a RESTful API and what does each part do?

### Answer:
1. HTTP Method (GET, POST, PUT, DELETE)

These methods define the type of action the client wants to perform on the server.

GET: Retrieve data (e.g., get a list of users)

POST: Create a new resource (e.g., register a new user)

PUT: Update an existing resource (e.g., change user info)

DELETE: Remove a resource (e.g., delete a user account)

2. URL (Uniform Resource Locator)

The URL specifies what resource is being acted upon. It may include: Path Variables, Request Parameters

3. HTTP Headers

Headers contain metadata about the request. Common examples include:

Content-Type: Specifies the format of the data (e.g., application/json)

Authorization: Contains tokens or credentials for authentication

Accept: Tells the server what data format the client expects (e.g., application/json)

4. Request Body

The request body contains data sent from the client to the server, usually in POST or PUT requests.

5. HTTP Response Status Code

These codes indicate the result of the request. Common codes include:

200 OK: Request succeeded

201 Created: Resource successfully created

400 Bad Request: Invalid input from the client

401 Unauthorized: Client must authenticate

404 Not Found: Resource doesn’t exist

500 Internal Server Error: Something went wrong on the server

6. Response Headers
These are similar to request headers but are sent from the server to the client. They may include:

Content-Type: Format of the response (e.g., application/json)

Cache-Control: Caching behavior

Set-Cookie: Instructions to store cookies

7. Response Body
The response body contains data returned by the server. It’s typically in JSON or XML format.

## Q6: Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL.

### Answer:
cURL (short for Client URL) is a command-line tool used to transfer data to or from a server using protocols like HTTP, HTTPS, FTP, etc. It is widely used to test REST APIs by making raw HTTP requests directly from the terminal.

Postman has a graphical interface, making it easier to construct, send, and view API requests without writing long command-line syntax.

Postman displays responses (JSON, XML, HTML) in a formatted, readable way, which is much easier to understand than plain terminal output.

Postman allows you to store variables (e.g., API keys, URLs) in environments for quick switching and reuse.

You can save and organize requests in collections, making it easy to test APIs repeatedly or share with teams.

Postman supports scripting and automated testing (via pre-request scripts and tests using JavaScript).

Postman provides built-in support for OAuth, Bearer tokens, and API keys without manually setting headers.

## Q7: List common HTTP status codes and their meanings.

### Answer:
1. 1xx – Informational Responses

100 Continue: The server has received the request headers and the client should proceed with the request body.

2. 2xx – Success

200 OK: The request was successful and the server responded with the requested data.

201 Created: The request was successful and a new resource was created (usually after a POST request).

204 No Content: The request was successful, but there is no content to return (often used with DELETE operations).

3. 3xx – Redirection

301 Moved Permanently: The requested resource has been moved to a new URL permanently.

302 Found: The resource is temporarily located at a different URL.

304 Not Modified: The cached version of the resource is still valid; no need to download again.

4. 4xx – Client Errors

400 Bad Request: The request is malformed or has invalid parameters.

401 Unauthorized: The client must authenticate before accessing the resource.

403 Forbidden: The server understood the request but refuses to authorize it.

404 Not Found: The requested resource could not be found on the server.

405 Method Not Allowed: The HTTP method used is not allowed for this resource (e.g., using POST on a read-only endpoint).

409 Conflict: The request could not be completed due to a conflict with the current state of the resource (e.g., duplicate data).

5. 5xx – Server Errors

500 Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.

502 Bad Gateway: The server received an invalid response from an upstream server.

503 Service Unavailable: The server is temporarily unable to handle the request (e.g., due to maintenance or overload).

504 Gateway Timeout: The server didn’t receive a timely response from another server it was accessing.

## Q8: List HTTP methods and their meanings, and their expected HTTP status codes.

### Answer:
1. GET

Purpose: Retrieve data from the server (read-only).

Request Body: Not used.

Typical Use Case: Fetch a list of users or details of one user.

Expected Status Codes:

200 OK: Successful response with data.

404 Not Found: Resource does not exist.

2. POST

Purpose: Create a new resource on the server.

Request Body: Contains the data to create.

Typical Use Case: Register a new user, add a product.

Expected Status Codes:

201 Created: Resource was successfully created.

400 Bad Request: Invalid input data.

409 Conflict: Resource already exists.

3. PUT

Purpose: Update an existing resource entirely.

Request Body: Contains full replacement data.

Typical Use Case: Update all fields of a user profile.

Expected Status Codes:

200 OK: Resource updated successfully.

204 No Content: Update succeeded, no content returned.

400 Bad Request: Invalid data.

404 Not Found: Resource to update does not exist.

4. PATCH

Purpose: Update part of an existing resource (partial update).

Request Body: Contains only the fields to be updated.

Typical Use Case: Update only the email or password of a user.

Expected Status Codes:

200 OK: Partial update successful.

204 No Content: Update succeeded with no content.

400 Bad Request: Malformed request or data.

404 Not Found: Resource does not exist.

5. DELETE

Purpose: Delete a resource from the server.

Request Body: Usually not required.

Typical Use Case: Remove a user or product.

Expected Status Codes:

200 OK: Resource deleted, response includes confirmation.

204 No Content: Resource deleted, no content returned.

404 Not Found: Resource does not exist.

## Q9: Explain why REST API is stateless.

### Answer:
A REST API is stateless because each request from the client to the server must contain all the information necessary to understand and process the request. The server does not store any context (like session data) between requests.

The server does not remember anything about the client between different API calls.

Each API call is independent, and no client context is stored on the server after the request ends.

## Q10: Discuss about best practices for REST API design, from performance perspective.

### Answer:
1. Use Proper HTTP Methods and Status Codes

Follow REST conventions (GET, POST, PUT, DELETE) to allow efficient routing and caching.

Return only appropriate status codes (e.g., 204 No Content for successful DELETE without body).

2. Enable Caching

Use HTTP headers like Cache-Control, ETag, and Last-Modified to avoid unnecessary re-fetching of unchanged data.

Allow client-side and CDN caching where appropriate to reduce load on servers.

3. Pagination for Large Responses

Avoid sending large datasets in a single response.

Use pagination (limit, offset, or page parameters) to send data in manageable chunks.

Example: GET /api/products?limit=20&page=2

4. Use JSON (or lightweight formats)

Prefer JSON over XML for smaller, faster responses.

Consider using even more compact formats like Protocol Buffers for internal APIs if extreme speed is required.

5. Implement Filtering and Sorting

Allow clients to request only the data they need by adding query parameters for filtering and sorting.

Example: GET /api/users?role=admin&sort=name

This reduces unnecessary data processing and transmission.

6. Support Partial Responses (Field Selection)

Let clients specify which fields they want using a fields parameter.

Example: GET /api/users/123?fields=id,name,email

This reduces response size and improves speed.

7. Use Nouns, Not Verbs in Endpoints

Design URLs to represent resources, not actions.

Use: POST /api/users

Not: /api/createUser

HTTP methods already define the action: GET (read), POST (create), PUT/PATCH (update), DELETE (delete).

8. Use Hyphens (-) Instead of Underscores (_)

Hyphens improve readability in URLs.

Use: /managed-devices

Not: /managed_devices

9. Avoid File Extensions in URIs

Keep the resource path clean and decoupled from content type.

Use: /devices

Not: /devices.xml

10. Use Compression
Enable gzip or Brotli compression for responses to reduce payload size.

11. Implement Versioning

Version your APIs to support backward compatibility and evolution.

Example:
GET /v1/users

GET /v2/users

## Q11: Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to avoid them.

### Answer:
1. XSS: Cross-Site Scripting

XSS is a security vulnerability that allows an attacker to inject malicious JavaScript code into a web page viewed by other users. This code runs in the victim’s browser and can steal data, hijack sessions, or modify the webpage.

How to Prevent XSS:

Escape user input before rendering it in HTML (use HTML encoding).

Sanitize input using libraries (e.g., DOMPurify in JavaScript).

Use Content Security Policy (CSP) headers to block inline scripts.

Never trust data from users—validate and encode it properly before output.

2. CSRF: Cross-Site Request Forgery

CSRF tricks a logged-in user’s browser into sending an unintended request to a web app on their behalf—without their knowledge.

How to Prevent CSRF:

Use CSRF tokens in forms that the server verifies on each POST/PUT/DELETE.

Implement SameSite cookie attributes to restrict cookies from being sent on cross-site requests.

Require re-authentication for critical actions (e.g., money transfers).

Use CORS (Cross-Origin Resource Sharing) policies to limit which domains can make requests.

## Q12: API Practice

### Answer:
1. GitHub REST API

Type: REST

Base URL: https://api.github.com

Example Endpoint: GET https://api.github.com/users/ChenniXu19

Purpose: Get public profile data of GitHub users

Auth: Optional (API token for higher rate limits)

✅ Nouns in Endpoints: /users, /repos

✅ HTTP Methods: Correct usage of GET, POST, PUT, DELETE

✅ Status Codes: Uses 200, 201, 404, 403, 401

✅ JSON Format: Both input and output use JSON

✅ Path Variables: /users/{username}, /repos/{owner}/{repo}

✅ Query Parameters: Pagination, filtering supported

✅ Versioning: Versioning via custom media types (e.g., application/vnd.github.v3+json)

✅ Stateless: Yes

🟢 Verdict: Well-designed, follows REST and best practices fully.

```
curl --location 'https://api.github.com/users/ChenniXu19'
```

| Header                                   | Purpose                                          |
| ---------------------------------------- | ------------------------------------------------ |
| `Accept: application/vnd.github.v3+json` | Tells GitHub we want version 3 responses in JSON |
| `User-Agent: PostmanRuntime/7.x`         | Identifies Postman as the client                 |
| Header                                          | Purpose                                      |
| ----------------------------------------------- | -------------------------------------------- |
| `Content-Type: application/json; charset=utf-8` | Specifies the format of the response         |
| `X-RateLimit-Limit: 60`                         | API rate limit for unauthenticated requests  |
| `X-RateLimit-Remaining: 59`                     | Remaining allowed requests in current window |
| `ETag`                                          | Used for caching (conditional requests)      |

2. REST Countries API

Type: REST

Base URL: https://restcountries.com

Example Endpoint: GET https://restcountries.com/v3.1/name/usa

Purpose: Get information about countries (population, capital, currency, etc.)

Auth: No authentication required

✅ Nouns in Endpoints: /all, /name/{country}, /region/{region}

✅ HTTP Methods: Only uses GET (read-only API)

✅ Status Codes: Uses 200, 404

✅ JSON Format: JSON only

✅ Path Variables: Country name, alpha code

✅ Query Parameters: Basic filters (e.g., ?fullText=true)

✅ Versioning: Version in path (e.g., /v3.1), but not very flexible

✅ Stateless: Yes

🟢 Verdict: Clean and readable; very simple, mostly best practice-compliant.

```
curl --location 'https://restcountries.com/v3.1/name/usa'
```

Request Headers: None needed

Response Headers:

Content-Type: application/json

Cache-Control: How long the response can be cached

3. SpaceX Launch Data API
   
Type: REST

Base URL: https://api.spacexdata.com/v4

Example Endpoint: GET https://api.spacexdata.com/v4/launches/latest

Purpose: Retrieve data about SpaceX launches

Auth: No authentication required

✅ Nouns in Endpoints: /launches, /rockets, /crew

✅ HTTP Methods: Mostly GET

✅ Status Codes: Proper 200, 404 handling

✅ JSON Format: Responses in JSON

✅ Path Variables: Resource-specific IDs

❌ Query Parameters: Only basic; complex filtering requires POST (non-RESTful)

✅ Versioning: Versioned in path (e.g., /v4)

✅ Stateless: Yes

🟡 Verdict: Mostly good. Could improve filtering via GET and enhance field selection.

```
curl --location 'https://api.spacexdata.com/v4/launches/latest'
```

Content-Type: application/json; charset=utf-8

Content-Length: Size of the response body

Access-Control-Allow-Origin: Allows cross-origin requests

4. OpenWeatherMap API

Type: REST

Base URL: https://api.openweathermap.org/data/2.5

Example Endpoint: GET https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY

Purpose: Get real-time weather data for a location

Auth: Requires free API key (register at https://openweathermap.org/)

✅ Nouns in Endpoints: /weather, /forecast

✅ HTTP Methods: GET used properly

✅ Status Codes: 200, 401, 404, etc.

✅ JSON Format: JSON response

✅ Path Variables / Query Params: Uses ?q=London, ?lat=..&lon=..

✅ Versioning: Done via path (/data/2.5)

✅ Stateless: Yes

🟢 Verdict: Simple, reliable RESTful API with good parameter support and security.

```
curl --location 'https://api.openweathermap.org/data/2.5/weather?q=London'
```

Request Headers: None (just the API key in URL)

Response Headers:

Content-Type: application/json

X-Cache: May show if the response was served from a CDN cache

Server: Shows OpenWeather’s backend server (e.g., openresty)

5. Pokémon GraphQL API

Type: GraphQL (non-REST)

Endpoint: POST https://beta.pokeapi.co/graphql/v1beta

Query Example (used in request body):

```
{
    pokemon_v2_pokemon(limit: 1) {
        name
    }
}
```

Purpose: Access Pokémon data (GraphQL syntax)

Auth: No authentication required

❌ Nouns in URI: Only one endpoint (/graphql)

❌ HTTP Methods: Uses only POST (GraphQL standard)

✅ Status Codes: Returns 200 even on logical errors

✅ JSON Format: Response in JSON

❌ Path Variables: Not used (GraphQL uses a schema instead)

✅ Query flexibility: Handled in GraphQL query body

❌ Versioning: Not clear or enforced

✅ Stateless: Yes

🔵 Verdict: Not RESTful by design—follows GraphQL practices instead. Powerful, but doesn't conform to RESTful conventions.

```
curl --location 'https://beta.pokeapi.co/graphql/v1beta' \
--header 'Content-Type: application/json' \
--data '{
  "query": "{ pokemon_v2_pokemon(limit: 1) { name } }"
}'
```

Request Headers:

Content-Type: application/json

Response Headers:

Content-Type: application/json

Content-Length

Access-Control-Allow-Origin