# HW7: API
@ Jun 26, 2025 _Gloria Wang_

## 1. Explain the concept of API (Application Programming Interface), why do we need APIs
> API is a contract / interface that allows two software applications / systems to communicate with each other
- API can define what operations are available
- Specifies how to access these operations 
- Abstracts complexity, like we dont need to know how it works, just call api
- Two Types of API: Developer API, Application API

#### Why do we need API:
- **Loose Coupling:**
  - Frontend and Backend can evolve independently
  - Systems can integrate without exposing internal logic
- **Reusability:**
  - A single API can serve multiple clients, like website, mobile app, ...
- **Security:**
  - We  can use tokens, cookies to implement authentication and authorization
- **Standard Communication:** 
  - HTTP -> API use standard protocols like HTTP, so any language and system can interact with them

## 2. Compare developer API vs application API (normal APIs)

#### Developer API:
- used by developer to build software modules
- like methods, jar, commandline application

#### Application API:
- used to expose business logic / services over the network
- used in frontend-backed communication
- like REST API (HTTP) , GRAPHQL, GPRC, WEB SOCKET, MQTT, MCP, … SOAP

| Feature                     | Developer API                                   | Application API                               |
|----------------------------|--------------------------------------------------|-----------------------------------------------|
| Audience                   | Other developers                                 | Frontend, external systems                    |
| Usage                      | Code-to-code (method/class calls)                | Over network (HTTP request)                   |
| Example                    | Java `List`, Spring annotations                  | REST API, GraphQL, ...                        |
| Encapsulates               | Internal logic of libraries or modules           | Business logic or service capabilities        |
| Requires programming skill | Yes                                              | Not necessarily — can use Postman, curl, etc. |

> A Developer API is for building software; an Application API is for integrating software

## 3. Name some different types of APIs
| API Type             | Protocol / Format         | Description                                                              |
|----------------------|---------------------------|--------------------------------------------------------------------------|
| **REST API**         | HTTP + JSON/XML           | Most popular; stateless; uses HTTP verbs (GET, POST, PUT, DELETE)        |
| **GraphQL API**      | HTTP + Query Language     | Client controls response shape; single endpoint; great for nested data   |
| **gRPC**             | HTTP/2 + Protocol Buffers | High-performance binary protocol; used in microservices; needs codegen   |
| **WebSocket**        | TCP (full duplex)         | Real-time, two-way communication; used in chat apps, games               |
| **MQTT**             | TCP/IP                    | Lightweight, pub-sub model; used in IoT and mobile applications          |
| **SOAP**             | HTTP + XML (WSDL)         | Older, strict XML-based protocol; used in legacy enterprise systems      |
| **Command Line API** | Shell/Terminal Commands | Developer-facing APIs for scripts and CLI tools (e.g., Git CLI)          |

## 4. Compare path variables vs request parameters in REST API

- **Path Variables**: used to uniquely identify a specific resource: `/users/{id}`
- **Request Parameters**: after `?`, used to filter, sort, or provide optional input: `?age=24`
  - always used for:
    - filtering (`age=25`)
    - sorting (`sort=desc`)
    - pagination (`page=2&size=10`)
    - searching (`keyword=book`)

## 5. Explain the different components that make up a RESTful API and what does each part do?

### 1. HTTP Methods
Defines the action:
- `GET`: Retrieve data
- `POST`: Create new resource
- `PUT`: Update existing resource
- `DELETE`: Remove resource
- ...

### 2. URL (Uniform Resource Locator)
Define which resource and what conditions we're referring to
- **Path Variables**: `/users/{id}`
- **Request Parameters (Query Strings)**: `?age=25&sort=desc`

### 3. Http Headers 
Key-Value pairs that pass metadata:  
- `Authorization: Bearer <token>` → for authentication  
- `Content-Type: application/json` → defines request/response format
- `Cookie`, `Accept`, `X-Frame-Options` (e.g., for security policies)

### 4. Request Body
The actual data (content) sent in `POST`, `PUT`, or `PATCH` requests (usually in JSON or XML)          
- e,g,`{ "name": "Alice", "email": "a@x.com" }`                                          
- But not used in `GET` or `DELETE` methods

### 5. HTTP (Response) Status Code
- 2xx → ✅ Success: Everything went fine 
- 3xx → 🔁 Redirection: “You’re at the wrong place, go there instead” 
- 4xx → ❌ Client Error: “Your request is bad” (frontend or user error)
- 5xx → 💥 Server Error: “I tried but I failed” (backend/server error)

e.g.
- 200 OK → Standard successful response
- 301 Moved Permanently → Resource has a new permanent URL (e.g., `twitter → x`)
- 404 Not Found → Resource doesn't exist
- 500 Internal Server Error → Generic server failure

### 6. Response Body
Main content returned by the server after processing the request, usually in JSON format (sometimes XML, plain text, or binary data depending on the API
- Used in: `GET`, `POST`, `PUT`, sometimes `PATCH`
- Not used in: `204 No Content`, often empty for `DELETE`
- Includes: resource data, status messages, error details, etc

### 7. Response Headers
Key-value pairs sent by the server alongside the response,  provide metadata about the response
- Includes security, caching, rate limits, pagination info, ...
- Not visible in the body; used by the browser or client to interpret data

## 6. Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL.
> cURL: "Client for URL" → command line tool used to make HTTP requests to URLs, like REST API
- send HTTP requests
- add headers like authorization / content type
- send JSON data in requests bodies
- debug responses from API

#### Example
```bash
curl -X POST https://api.example.com/users \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer abc123" \
  -d '{"name": "Alice", "email": "alice@example.com"}'
```

### Why use Postman instead of just cURL:
> cURL is fast and scriptable — great for automation and terminal pros
> 
> Postman is better for exploration, debugging, and team collaboration because it provides a visual, interactive interface with powerful features like saved collections, environments, and test scripts

| Feature                    | cURL                                 | Postman                                           |
|----------------------------|--------------------------------------|---------------------------------------------------|
| Interface                  | Text-based terminal                  | Graphical user interface (click & input friendly) |
| Ease of Use                | Requires memorizing syntax           | Easy input fields and dropdowns                   |
| Reusability                | Hard to reuse unless saved in script | Requests can be saved in collections              |
| Visual Response Viewer     | No (plain text only)                 | Yes (formatted JSON viewer, headers tab, etc.)    |
| Environment Support        | Manual via shell scripts             | Built-in environment & variable management        |
| History / Logs             | Not saved unless piped to files      | Automatically tracks request history              |
| Team Collaboration         | Not ideal                            | Great — can share workspaces and collections      |
| Automated Testing          | Requires scripts (e.g., bash)        | Built-in test scripts with JavaScript             |


## 8. List common HTTP status codes and their meanings
- 1xx: Informational (rare in REST)
- 2xx: ✅ Success
- 3xx: 🔁 Redirection (usually handled automatically by browsers)
- 4xx: ❌ Client-side errors (your request is bad)
- 5xx: 💥 Server-side errors (something broke on the server)

#### Common Status Code
| Status Code               | Meaning                                                          |
|---------------------------|------------------------------------------------------------------|
| 200 OK                    | The request was successful and the server responded with data.   |
| 201 Created               | A new resource was successfully created.                         |
| 204 No Content            | The request was successful but no content is returned.           |
| 301 Moved Permanently     | The resource has been moved to a new URL permanently.            |
| 302 Found                 | Temporarily redirected to another URL.                           |
| 400 Bad Request           | The request was malformed or had invalid syntax.                 |
| 401 Unauthorized          | Authentication is required or failed.                            |
| 403 Forbidden             | You don’t have permission to access the requested resource.      |
| 404 Not Found             | The requested resource doesn’t exist.                            |
| 409 Conflict              | The request conflicts with current state of the resource.        |
| 500 Internal Server Error | A generic error occurred on the server.                          |
| 502 Bad Gateway           | The server received an invalid response from an upstream server. |
| 503 Service Unavailable   | The server is temporarily unavailable or overloaded.             |


## 9. Explain why REST API is stateless

### Stateless means that
- No memory on te server
- Each request is independent
- All necessary data like authentication must be included in every request (using token, cookies 🍪, user ID, ...)

### so Why stateless❓
#### 1. Scalability
- Stateless servers can handle more requests more easily
  - since no user session is stored, any server in a cluster can handle any request
  - → enables **horizontal scaling** (adding more servers) without worrying about syncing session data

> _🌰 Example: In a load-balanced system, request 1 can go to Server A, and request 2 can go to Server B — both can process it without knowing previous context_

#### 2. Simplicity & Maintainability
- Server logic is simpler since it doesn’t need to manage or track session state
- → Fewer bugs related to session timeout, memory leaks, or concurrency issues

#### 3. Reliability and Fault Tolerance
- If a server goes down, no session data is lost, the next server just picks up the next request
- → so stateless systems recover more easily from failure

#### 4. Cacheability
- Because each request is independent and self-contained, responses can be cached more safely
- → improves performance and reduces server load

#### 5. Testing & Debugging
- Stateless requests are repeatable and predictable
- → makes it easier to test with tools like Postman or cURL cuz every request has all the needed info

### 🔁 Contrast: Stateful Systems
The server stores login state or cart state in memory
  - If the server restarts or the session expires, user data is lost
  - Which means we must keep hitting the same server (or replicate the session across servers) → add complexity

## 10. Discuss about best practices for REST API design, from performance perspective
#### 1. Implement Pagination, Filtering, and Sorting
- Never return entire datasets (GET /users without pagination = ❌).
- Use query parameters:
  - `GET /users?page=1&pageSize=50`
  - `GET /products?category=books&sort=price&limit=20`

#### 2. Use HTTP Caching
- Use headers like `Cache-Control`, `ETag`, and `Last-Modified`
- Allow clients or CDNs to reuse responses without hitting backend again
- `Cache-Control: public, max-age=3600`

#### 3. Use Lightweight Data Formats
- Prefer JSON over XML — smaller size, faster to parse.
- If data must be super small: use compressed formats like MessagePack or Gzip (enabled at server level)

#### 4. Avoid Over-fetching or Under-fetching
- Structure responses to only return needed fields
- For large objects, use field selection:
  - `GET /users/123?fields=id,name,email`

#### 5. Use Asynchronous Processing (when needed)
- For long-running operations (e.g., report generation), respond immediately:
  - `POST /reports → 202 Accepted`
    `Location: /reports/987/status`

#### 6. Minimize N+1 Queries
- Batch your DB or downstream requests to avoid making one request per object
  - Example: don’t fetch authors for each blog post in separate queries

#### 7. Enable Gzip Compression
- Compress large responses at the server level using Content-Encoding: gzip
- Reduces bandwidth and speeds up delivery

#### 8. Design Predictable and Cache-Friendly URLs
- Keep URLs clean and hierarchical.
- Avoid using random query tokens that disable caching:
  - `/api/users/123`        ✅
  - `/api/users?id=123`     ✅
  - `/api/users?_random=xyz` ❌

#### 9. Use Rate Limiting and Throttling
- Prevent abuse, reduce load spikes, and stabilize performance.
- Headers like:
  - `X-RateLimit-Limit: 1000`
  - `X-RateLimit-Remaining: 997`

#### 10. Prefer Bulk Operations for Performance
- Instead of multiple POST requests, prefer:
    ```http request
    POST /users/bulk
    Content-Type: application/json
    
    [
      {
        "name": "Alice",
        "email": "..."
      },
      {
        "name": "Bob",
        "email": "..."
      },
      {
        "name": "Charlie",
        "email": "..."
      }
    ]
    ```
  
## 11. Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to avoid them

### XSS (Cross-Site Scripting)
> XSS happens when an attacker 🥷 injects malicious JavaScript into a trusted website / API response
> 
> This code runs in victim's browser and will:
> - steal cookies 🍪 / tokens
> - redirect users to malicious sites
> - modify page content

#### How to Prevent XSS 💉
- Escape/sanitize user input (especially before rendering in HTML)
- Use a templating engine that auto-escapes (e.g., Thymeleaf, React JSX)
- Set HTTP headers:
  - `Content-Security-Policy: default-src 'self'` 
  - `X-XSS-Protection: 1; mode=block` 
- Validate input on both frontend and backend

### CSRF (Cross Site Request Forgery)
> CSRF tricks logged in users into submitting unwanted requests, such as deleting account, without users consent
> 
> Attackers 🥷 exploit two conditions:
> - The browser automatically includes authentication cookies 🍪 in cross-site requests
> - The user is already authenticated (logged in) 

#### How to Prevent CSRF 💉
- Use **CSRF tokens** 
  - backend generates a secret token and validates it in every request
- Require **custom headers** (like X-CSRF-TOKEN) for state-changing requests
- Disable `GET` for sensitive actions 
  - CSRF mostly affects unsafe methods like POST, PUT, DELETE
- Use **SameSite cookies**:
  - `Set-Cookie: sessionId=abc123; SameSite=Strict`
- Implement **user confirmation** steps for critical actions

### Summary:
| Attack Type | What It Does                                               | How to Prevent                                              |
|-------------|------------------------------------------------------------|-------------------------------------------------------------|
| XSS         | Injects & runs JS in the browser to steal or manipulate    | Sanitize input, use CSP, escape output                      |
| CSRF        | Tricks browser into making unintended authenticated request| Use CSRF tokens, SameSite cookies, and custom headers       |

# API Practices
## 1. Find at least 5 different public APIs, use them to explain what defines a REST API
### 1. GitHub APIs
```Http 
GET https://api.github.com/users/GloriaUPUP
```
**Purpose**: Retrieve public profile data for the GitHub user `GloriaUPUP`  
**Authentication**: ❌ Not required (for public user data)  
**RESTful?**: ✅

| REST Component         | Value / Explanation                                                                    |
|------------------------|----------------------------------------------------------------------------------------|
| HTTP Method            | `GET` — used to retrieve data                                                          |
| URL                    | `https://api.github.com/users/GloriaUPUP` — with `GloriaUPUP` as a path variable       |
| Request Headers        | Postman-Token, Host, User-Agent, Accept, Accept-Encoding, and Connection               |
| Request Body           | None (GET requests don’t send data)                                                    |
| Response Status Code   | `200 OK`                                                                               |
| Response Body          | JSON with user info (`login`, `id`, `node_id`, etc.)                                   |
| Response Headers       | Includes `Date`: Fri, 27 Jun 2025, `Content-Type`: JSON, `X-RateLimit-Limit`: 60 , etc |

### 2. Google Books API
```Http
GET https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=AIzaSyDbQ6-Cf73dSDn-kWvY5L95x8koc9gG4iA
```
**Purpose**: Retrieve a list of books related to the search query "Harry Potter"  
**Authentication**: ✅ Yes (API Key required)  
**RESTful?**: ✅

| REST Component         | Value / Explanation                                                                 |
|------------------------|-------------------------------------------------------------------------------------|
| **HTTP Method**        | `GET` — retrieves data about books                                                  |
| **URL**                | `/volumes?q=harry+potter&key=...` — uses query parameters, no path variable         |
| **Request Headers**    | Postman-Token, Host, User-Agent, Accept, Accept-Encoding, and Connection            |
| **Request Body**       | None — `GET` method, all data sent via query parameters                             |
| **Response Status Code**| `200 OK` — success                                                                  |
| **Response Body**      | JSON with array of book objects (kinds, totalItems, author, publisher, title, etc.) |
| **Response Headers**   | `Content-Type: application/json`, `Content-Encoding`, etc.                          |

### 3. BigDataCloud Reverse Geocoding API
```Http
GET https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=37.7749&longitude=-122.4194
```
**Purpose**: Returns detailed geographic location data (e.g., city, country, continent) for a given set of latitude and longitude coordinates  
**Authentication**: ❌  Not required  
**RESTful?**: ✅

| REST Component           | Value / Explanation                                                                                            |
|--------------------------|----------------------------------------------------------------------------------------------------------------|
| **HTTP Method**          | `GET` — retrieves location info for coordinates                                                                |
| **URL**                  | Uses query parameters: `latitude`, `longitude`                                                                 |
| **Request Headers**      | Same as above                                                                                                  |
| **Request Body**         | None — all data passed via query parameters                                                                    |
| **Response Status Code** | `200 OK` — success                                                                                             |
| **Response Body**        | JSON with location info: `{ "city": "San Francisco", "locality": "Mission-Bernal", "postcode": "94102", ... }` |
| **Response Headers**     | Includes `Content-Type: application/json`, cache encoding, x-lookup-source, etc.                               |

### 4. JSONPlaceholder API
```Http
POST https://jsonplaceholder.typicode.com/posts

Body: 
{
    "title": "foo",
    "body": "bar",
    "userId": 1
  }
```
**Purpose**: Creates a new post entry (used for testing RESTful POST requests — no actual data is saved permanently)     
**Authentication**: ❌    
**RESTful?**: ✅ Yes

| REST Component           | Value / Explanation                                                                     |
|--------------------------|-----------------------------------------------------------------------------------------|
| **HTTP Method**          | `POST` — used to submit new data (create a resource)                                    |
| **URL**                  | `https://jsonplaceholder.typicode.com/posts` — `/posts` clean RESTful resource endpoint |
| **Request Headers**      | `Content-Type: application/json` (must be set to send JSON body)                        |
| **Request Body**         | JSON payload: `{ "title": "foo", "body": "bar", "userId": 1 }`                          |
| **Response Status Code** | `201 Created` — indicates successful creation of a resource                             |
| **Response Body**        | JSON response: `{ "title": "foo", "body": "bar", "userId": 1, "id": 101 }`              |
| **Response Headers**     | `Content-Type: application/json; charset=utf-8`, etc                                    |


### 5. wttr.in Weather API

```Http
GET https://wttr.in/San%20Francisco?format=j1
```
**Purpose**: Retrieve the current weather and forecast for a specified location (`San Francisco`) in JSON format.  
**Authentication**: ❌  
**RESTful?**: ✅

| REST Component           | Value / Explanation                                                                                    |
|--------------------------|--------------------------------------------------------------------------------------------------------|
| **HTTP Method**          | `GET` — retrieves weather information                                                                  |
| **URL**                  | `/San%20Francisco?format=j1` — uses path + query parameter for location and format                     |
| **Request Headers**      | `User-Agent`, `Accept`, etc.                                                                           |
| **Request Body**         | None — data is passed via URL parameters                                                               |
| **Response Status Code** | `200 OK` — response returned successfully                                                              |
| **Response Body**        | JSON object with fields like `current_condition`, `"FeelsLikeC": "14"`, and `"cloudcover": "75"`, etc. |
| **Response Headers**     | `Content-Type: application/json`, `Date`, `Content-Length` ,...                                        |

## 2. Justify whether these APIs follow API design best practices, and provide your better design for them
### 1. GitHub APIs
```Http 
GET https://api.github.com/users/GloriaUPUP
```
✅ Best Design:
- Clear resource-based path (/users/{username})
- Uses path variables correctly 
- No unnecessary parameters 
- Proper status codes (200, 404)
- Stateless and cacheable

### 2. Google Books API
```Http
GET https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=AIzaSyDbQ6-Cf73dSDn-kWvY5L95x8koc9gG4iA
```
✅ Good Design:
- Well-structured endpoint
- Uses query parameters for search
- Proper pagination and filtering options available
- Auth via API key (standard practice)

⚠️ Possible Improvement:
- 	URL could be cleaner using path instead of query:

✌️ Better Design
```Http
GET https://www.googleapis.com/books/v1/books/search/harry-potter?key=YOUR_API_KEY
```

### 3. BigDataCloud Reverse Geocoding API
```Http
GET https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=37.7749&longitude=-122.4194
```
⚠️ Possible Improvement:
- Versioning (v1): Helps with backward compatibility as the API evolves
- Clean parameter names: lat and lon are standard, short, and widely recognized
- Resource clarity : Clearly shows the resource and action: geocode/reverse
- Scalable design: Can later add /geocode/forward or /geocode/batch easily

✌️ Better Design
```Http
GET https://api.bigdatacloud.net/v1/geocode/reverse?lat=37.7749&lon=-122.4194
```

### 4. JSONPlaceholder API
```Http
POST https://jsonplaceholder.typicode.com/posts
```
️⚠️ Possible Improvements
- Versioning (v1): Supports backward compatibility
- Clean param (userId): Passes user clearly in request

✌️ Better Design

```Http
POST https://jsonplaceholder.typicode.com/v1/posts?userId=1
```

### 5. wttr.in Weather API

```Http
GET https://wttr.in/San%20Francisco?format=j1
```
⚠️ REST Design Issues:
- Endpoint uses unusual structure (/San Francisco as path, but dynamic)
- No clear versioning
- Hard to extend or organize endpoints for future

✌️ Better Design
```Http
GET /api/v1/weather?location=San+Francisco&format=json
```

## 3. List the above APIs in form of cURL commands, and attach Postman screenshots
### 1. GitHub APIs
```bash
curl -X GET "https://api.github.com/users/GloriaUPUP"
```
![Postman Screenshot - GitHub User API](PostmanScreenshots/GitHubAPI.png)

### 2. Google Books API
```bash
curl -X GET "https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=AIzaSyDbQ6-Cf73dSDn-kWvY5L95x8koc9gG4iA"
```
![Postman Screenshot - Google Books API](PostmanScreenshots/GoogleBooksAPI.png)

### 3. BigDataCloud Reverse Geocoding API
```bash
curl -X GET "https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=37.7749&longitude=-122.4194"
```
![Postman Screenshot - Geocoding API](PostmanScreenshots/GeocodingAPI.png)

### 4. JSONPlaceholder API
```bash
curl -X POST "https://jsonplaceholder.typicode.com/posts" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "foo",
    "body": "bar",
    "userId": 1
  }'
```
![Postman Screenshot - Geocoding API](PostmanScreenshots/JSONPlaceholderAPI.png)


### 5. wttr.in Weather API

```bash
curl "https://wttr.in/San%20Francisco?format=j1"
```
![Postman Screenshot - Weather API](PostmanScreenshots/WeatherAPI.png)

## 4. List the request headers and response headers of the APIs mentioned above, and explain what each key-value pair in the headers section does.
### 1. GitHub APIs
```Http
GET https://api.github.com/users/GloriaUPUP
```

#### 🙏 Request Headers

| Key               | Value                               | Description                                                    |
|-------------------|-------------------------------------|----------------------------------------------------------------|
| `Postman-Token`   | `<calculated when request is sent>` | Unique token for Postman’s internal tracking.                  |
| `Host`            | `<calculated when request is sent>` | Specifies the target host (e.g., `api.github.com`).            |
| `User-Agent`      | `PostmanRuntime/7.44.1`             | Identifies the client sending the request — here it's Postman. |
| `Accept`          | `*/*`                               | Indicates the client accepts any content type.                 |
| `Accept-Encoding` | `gzip, deflate, br`                 | Specifies compression formats the client can decode.           |
| `Connection`      | `keep-alive`                        | Keeps the TCP connection alive for reuse.                      |

#### ↩️ Response Headers

| Key                             | Value (Truncated)                 | Description                                       |
|---------------------------------|-----------------------------------|---------------------------------------------------|
| `Date`                          | Fri, 27 Jun 2025 08:45:52 GMT     | Timestamp when the server sent the response.      |
| `Content-Type`                  | `application/json; charset=utf-8` | Specifies the MIME type of the response.          |
| `Cache-Control`                 | `public, max-age=60, s-maxage=60` | Response can be cached for 60 seconds.            |
| `Vary`                          | `Accept,Accept-Encoding,...`      | Indicates which headers affect cache.             |
| `ETag`                          | `W/"f9ff01ab05..."`               | Entity tag used for cache validation.             |
| `Last-Modified`                 | Mon, 23 Jun 2025 14:03:48 GMT     | When the user data was last updated.              |
| `X-GitHub-Media-Type`           | `github.v3; format=json`          | Version and format of GitHub API used.            |
| `x-github-api-version-selected` | `2022-11-28`                      | API version that was chosen.                      |
| `Access-Control-Expose-Headers` | `...`                             | Headers accessible by browsers in CORS.           |
| `Access-Control-Allow-Origin`   | `*`                               | Allows all origins to access the response.        |
| `Strict-Transport-Security`     | `max-age=31536000...`             | Enforces HTTPS for the domain.                    |
| `X-Frame-Options`               | `deny`                            | Prevents clickjacking by disallowing iframes.     |
| `X-Content-Type-Options`        | `nosniff`                         | Disables content type sniffing.                   |
| `X-XSS-Protection`              | `0`                               | Disables legacy XSS protection in browsers.       |
| `Referrer-Policy`               | `origin-when-cross-origin,...`    | Controls what referrer data is sent.              |
| `Content-Security-Policy`       | `default-src 'none'`              | Restricts loading of any external resources.      |
| `Content-Encoding`              | `gzip`                            | Response is compressed using gzip.                |
| `Server`                        | `github.com`                      | Server that responded to the request.             |
| `Accept-Ranges`                 | `bytes`                           | Supports partial content delivery.                |
| `X-RateLimit-Limit`             | `60`                              | Max requests allowed in rate window.              |
| `X-RateLimit-Remaining`         | `56`                              | Remaining requests before hitting the limit.      |
| `X-RateLimit-Reset`             | `1751016649`                      | Time when rate limit resets (Unix timestamp).     |
| `X-RateLimit-Resource`          | `core`                            | Type of resource being limited.                   |
| `X-RateLimit-Used`              | `4`                               | Requests already used.                            |
| `Content-Length`                | `462`                             | Size of the response body.                        |
| `X-GitHub-Request-Id`           | `C9F4:7A6C6:...`                  | Unique ID for this request (for troubleshooting). |

### 2. Google Books API

```Http
GET https://www.googleapis.com/books/v1/volumes?q=harry+potter&key=...
```

#### 🙏 Request Headers

| Key               | Value                               | Description                                                              |
|-------------------|-------------------------------------|--------------------------------------------------------------------------|
| `Postman-Token`   | `<calculated when request is sent>` | Used by Postman to ensure request uniqueness (e.g., in collection runs). |
| `Host`            | `<calculated when request is sent>` | Specifies the server host (here it's `www.googleapis.com`).              |
| `User-Agent`      | `PostmanRuntime/7.44.1`             | Identifies the client — in this case, Postman.                           |
| `Accept`          | `*/*`                               | The client is willing to accept any MIME type.                           |
| `Accept-Encoding` | `gzip, deflate, br`                 | Indicates compression formats accepted by the client.                    |
| `Connection`      | `keep-alive`                        | Maintains persistent TCP connection to the server.                       |


#### ↩️ Response Headers

| Key                          | Value                             | Description                                                              |
|------------------------------|-----------------------------------|--------------------------------------------------------------------------|
| `Content-Type`               | `application/json; charset=UTF-8` | Response is in JSON format with UTF-8 encoding.                          |
| `Vary`                       | `Origin`, `X-Origin`, `Referer`   | Indicates the request headers that influence response caching.           |
| `Content-Encoding`           | `gzip`                            | Response was compressed using gzip.                                      |
| `Date`                       | `Fri, 27 Jun 2025 09:01:25 GMT`   | Time the response was generated.                                         |
| `Server`                     | `ESF`                             | Google's Edge Server Frontend that processed the request.                |
| `X-XSS-Protection`           | `0`                               | Disables browser's built-in XSS filter.                                  |
| `X-Frame-Options`            | `SAMEORIGIN`                      | Prevents clickjacking by disallowing frame embedding from other origins. |
| `X-Content-Type-Options`     | `nosniff`                         | Prevents MIME type sniffing in browsers.                                 |
| `Alt-Svc`                    | `h3=":443"; ma=2592000,...`       | Advertises HTTP/3 and fallback support over port 443.                    |
| `Transfer-Encoding`          | `chunked`                         | Response is sent in dynamically sized chunks.                            |

### 3. BigDataCloud Reverse Geocoding API
```Http
GET https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=37.7749&longitude=-122.4194
```

#### 🙏 Request Headers
| Key               | Value                               | Description                                                  |
|-------------------|-------------------------------------|--------------------------------------------------------------|
| `Postman-Token`   | `<calculated when request is sent>` | Ensures request uniqueness in Postman sessions.              |
| `Host`            | `<calculated when request is sent>` | Target host server (in this case `api.bigdatacloud.net`).    |
| `User-Agent`      | `PostmanRuntime/7.44.1`             | Identifies Postman as the client making the request.         |
| `Accept`          | `*/*`                               | Willing to accept any MIME type.                             |
| `Accept-Encoding` | `gzip, deflate, br`                 | Tells the server which compression formats are acceptable.   |
| `Connection`      | `keep-alive`                        | Suggests a persistent connection (no reconnect per request). |


#### ↩️ Response Headers

| Key                           | Value                             | Description                                                  |
|-------------------------------|-----------------------------------|--------------------------------------------------------------|
| `Content-Type`                | `application/json; charset=utf-8` | The response format and character encoding.                  |
| `Date`                        | `Fri, 27 Jun 2025 09:04:07 GMT`   | Timestamp of the response.                                   |
| `Access-Control-Allow-Origin` | `*`                               | CORS header allowing any origin to access.                   |
| `Content-Encoding`            | `br`                              | Response is Brotli-compressed.                               |
| `X-Lookup-Source`             | `coordinates`                     | Indicates that the lookup source was geographic coordinates. |
| `X-Response-Time-ms`          | `1.67`                            | Server time to process the request in milliseconds.          |
| `Transfer-Encoding`           | `chunked`                         | Response sent in chunks, useful for streaming.               |

### 4. JSONPlaceholder API
```Http
POST https://jsonplaceholder.typicode.com/posts

Body: 
{
    "title": "foo",
    "body": "bar",
    "userId": 1
  }
```
#### 🙏 Request Headers
| Key               | Value                               | Description                                                 |
|-------------------|-------------------------------------|-------------------------------------------------------------|
| `Postman-Token`   | `<calculated when request is sent>` | Unique ID per Postman request (used for tracing/debugging). |
| `Content-Type`    | `application/json`                  | Specifies that the request body format is JSON.             |
| `Content-Length`  | `<calculated when request is sent>` | Length of the request payload (set automatically).          |
| `Host`            | `<calculated when request is sent>` | Specifies the server’s hostname.                            |
| `User-Agent`      | `PostmanRuntime/7.44.1`             | Identifies the client (Postman).                            |
| `Accept`          | `*/*`                               | Accepts any response content type.                          |
| `Accept-Encoding` | `gzip, deflate, br`                 | Allows compressed responses to reduce payload size.         |
| `Connection`      | `keep-alive`                        | Reuses TCP connection for multiple requests.                |


#### ↩️ Response Headers

| **Header**                         | **Value**                                                                                                             | **Description**                                                                            |
|------------------------------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| `Date`                             | Fri, 27 Jun 2025 09:12:34 GMT                                                                                         | Timestamp when the response was generated by the server.                                   |
| `Content-Type`                     | `application/json; charset=utf-8`                                                                                     | Indicates that the body content is JSON encoded in UTF-8.                                  |
| `Content-Length`                   | `65`                                                                                                                  | Length of the response body in bytes.                                                      |
| `Connection`                       | `keep-alive`                                                                                                          | Keeps the TCP connection open for potential reuse.                                         |
| `Access-Control-Allow-Credentials` | `true`                                                                                                                | Allows credentials (like cookies, HTTP authentication) in CORS requests.                   |
| `Access-Control-Expose-Headers`    | `Location`                                                                                                            | Allows the `Location` header to be accessible to the browser via JavaScript (CORS).        |
| `Cache-Control`                    | `no-cache`                                                                                                            | Instructs clients not to cache the response.                                               |
| `Etag`                             | `W/"41-GDNaWfnVU6RZhpLbye0veBaqcHA"`                                                                                  | Entity tag for identifying changes to the resource (for caching optimization).             |
| `Expires`                          | `-1`                                                                                                                  | Deprecated in HTTP/1.1 — typically means the content is already expired.                   |
| `Location`                         | `https://jsonplaceholder.typicode.com/posts/101`                                                                      | URL of the newly created resource (in this case, the new post).                            |
| `NEL`                              | `{"report_to":"heroku-nel","response_headers":["Via"],"max_age":3600,"success_fraction":0.01,"failure_fraction":0.1}` | Network Error Logging policy. Specifies how and where to report failures from the browser. |
| `Pragma`                           | `no-cache`                                                                                                            | Legacy header for backward compatibility with HTTP/1.0 caches.                             |
| `Report-To`                        | `{"group":"heroku-nel","endpoints":[{"url":"https://nel.heroku.com/reports?..."}],"max_age":3600}`                    | Defines the group and endpoint to which network errors should be reported.                 |
| `Reporting-Endpoints`              | `heroku-nel="https://nel.heroku.com/reports?..."`                                                                     | Maps the group name (`heroku-nel`) to its actual reporting URL. Used by NEL and CSP.       |
| `Server`                           | `cloudflare`                                                                                                          | Indicates Cloudflare is acting as the CDN or reverse proxy.                                |
| `Vary`                             | `Origin, X-HTTP-Method-Override, Accept-Encoding`                                                                     | Indicates which request headers affect the response; helps with caching.                   |
| `Via`                              | `2.0 heroku-router`                                                                                                   | Indicates the message passed through the Heroku router.                                    |
| `X-Content-Type-Options`           | `nosniff`                                                                                                             | Prevents MIME-type sniffing for security reasons.                                          |
| `X-Powered-By`                     | `Express`                                                                                                             | Indicates the server uses the Express framework (Node.js).                                 |
| `X-Ratelimit-Limit`                | `1000`                                                                                                                | Max number of requests allowed in a given time window.                                     |
| `X-Ratelimit-Remaining`            | `999`                                                                                                                 | Number of requests remaining in the current rate limit window.                             |
| `X-Ratelimit-Reset`                | `1751015606`                                                                                                          | Unix timestamp of when the current rate limit window resets.                               |
| `Cf-Cache-Status`                  | `DYNAMIC`                                                                                                             | Indicates Cloudflare did not cache the response (dynamic content).                         |
| `CF-RAY`                           | `9563d2d1cb85cf8f-SJC`                                                                                                | Unique ID used by Cloudflare to trace the request.                                         |
| `alt-svc`                          | `h3=":443"; ma=86400`                                                                                                 | Informs the client that HTTP/3 is supported on port 443 (with max age in seconds).         |

### 5. wttr.in Weather API
```Http
GET https://wttr.in/San%20Francisco?format=j1
```

#### 🙏 Request Headers
| **Header**         | **Value**                           | **Description**                                                     |
|--------------------|-------------------------------------|---------------------------------------------------------------------|
| `Postman-Token`    | `<calculated when request is sent>` | Unique identifier for the request session (Postman auto-generated). |
| `Content-Type`     | `application/json`                  | Specifies that the body content is JSON.                            |
| `Content-Length`   | `<calculated when request is sent>` | Length of the request body in bytes.                                |
| `Host`             | `<calculated when request is sent>` | Server host receiving the request.                                  |
| `User-Agent`       | `PostmanRuntime/7.44.1`             | Identifies Postman as the HTTP client.                              |
| `Accept`           | `*/*`                               | Indicates all content types are accepted.                           |
| `Accept-Encoding`  | `gzip, deflate, br`                 | Compression formats the client accepts.                             |
| `Connection`       | `keep-alive`                        | Suggests maintaining the TCP connection for further requests.       |


#### ↩️ Response Headers
| **Header**                    | **Value**                     | **Description**                                                                 |
|-------------------------------|-------------------------------|---------------------------------------------------------------------------------|
| `Access-Control-Allow-Origin` | `*`                           | Allows any domain to access the resource — enables CORS from all origins.       |
| `Content-Length`              | `50688`                       | Total byte size of the response body.                                           |
| `Content-Type`                | `application/json`            | Indicates the returned content is in JSON format.                               |
| `Date`                        | Fri, 27 Jun 2025 09:17:01 GMT | Timestamp when the response was generated by the server.                        |