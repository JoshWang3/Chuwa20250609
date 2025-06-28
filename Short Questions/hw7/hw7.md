# HW7 - API

---


## 1. **Explain the concept of API (Application Programming Interface), why do we need APIs.**
* **What is API**
  * An API (Application Programming Interface) is a set of rules that allows one software system to communicate with another.

* **Why do we need APIs**
  * Integration: APIs let different systems work together — for example, embed Google Maps in your app.
  * Efficiency: We can reuse existing services (like payment, translation, or login) instead of building them by ourselves.
  * Security: APIs expose only selected parts of a system, keeping the rest safe and hidden.
  * Scalability: APIs make it easier to build large systems using smaller, modular parts (like microservices).
  * Automation: Systems can interact without manual steps — like placing orders, sending emails, or syncing data.

---
## 2. **Compare developer API vs application API (normal APIs)**

* **Developer APIs** are usually part of code libraries or SDKs, used by writing actual code inside the same program.
* **Application APIs** are web-based interfaces used to communicate between different systems, often over HTTP.

| **Term**          | **Developer API (Library/SDK)**                       | **Application API (Web/API Layer)**                   |
| ----------------- | ----------------------------------------------------- | ----------------------------------------------------- |
| **What it is**    | Code-level APIs (classes, methods, SDKs)              | Network-level APIs for system-to-system communication |
| **How it's used** | Direct method/function calls in your app              | HTTP requests, WebSockets, etc.                       |
| **Used by**       | Developers writing code using libraries or frameworks | Frontend apps, third-party services, mobile apps      |
| **Example**       | Java’s `List`, Python’s `os`, Android SDK             | REST API, GraphQL, Stripe API, `/api/user/123`        |
| **Runs where**    | Inside the application (same runtime)                 | Across the network (client ↔ server)                  |
| **Documentation** | Language-specific, IDE-supported                      | Often language-agnostic, public docs or OpenAPI specs |
| **Tools used**    | Code editor, SDK, language docs                       | Postman, curl, Swagger, HTTP clients                  |



---
## 3. **Name some different types of APIs**

| **Type**          | **How It Works**                                       | **Used For**                               | **Example**                 |
| ----------------- | ------------------------------------------------------ | ------------------------------------------ | --------------------------- |
| **REST API**      | Stateless, HTTP-based (GET, POST, etc.)                | Web services, mobile apps                  | GitHub, Twitter API         |
| **GraphQL API**   | Single endpoint, flexible querying                     | Custom frontend queries                    | Shopify, GitHub GraphQL API |
| **SOAP API**      | XML-based, strict schema                               | Enterprise or legacy systems               | Banking software            |
| **WebSocket API** | Persistent, full-duplex connection over HTTP           | Real-time communication                    | Chat, stock apps            |
| **Socket (TCP)**  | Low-level, manual messaging                            | Games, custom backend services             | Multiplayer game networking |
| **gRPC**          | Binary protocol (Protocol Buffers) over HTTP/2         | Microservice-to-microservice communication | Google Cloud APIs           |
| **CLI-based API** | Command-line interface that wraps internal or web APIs | Developer tools and automation             | `aws s3 ls`, `git push`     |



---
## 4. **Compare path variables vs request parameters in REST API.**

| **Aspect**            | **Path Variables**                        | **Request Parameters (Query Params)**        |
| --------------------- | ----------------------------------------- |----------------------------------------------|
| **Where they appear** | In the **URL path**                       | After the `?` in the **query string**        |
| **Format**            | `/users/{id}` → `/users/123`              | `/users?role=admin&page=2`                   |
| **Used for**          | Identifying a **specific resource**       | Providing **optional filters or extra info** |
| **Required?**         | Yes, usually required                     | Often optional                               |
| **Semantic meaning**  | Part of the resource's identity (RESTful) | Extra info to modify or refine the response  |
| **Best for**          | Resource IDs, parent-child paths          | Filtering, sorting, searching, pagination    |


---
## 5. **Explain the different components that make up a RESTful API and what does each part do?**

* **URLs / Endpoints**: It defines what data you're accessing or modifying.
* **HTTP Methods**: Tells the server what operation to do on the resource.
  * | Method | Action              |
    | ------ | ------------------- |
    | GET    | Read data           |
    | POST   | Create new data     |
    | PUT    | Update full data    |
    | PATCH  | Update partial data |
    | DELETE | Remove data         |

* **Path Variables**: Dynamic parts of the URL that identify a specific resource.
* **Query Parameters**: Extra info added to the URL to filter, sort, or paginate results.
* **Request Body**: The data sent along with a POST, PUT, or PATCH request.
* **Response Body**: The data the server sends back after a request.
* **HTTP Status Codes**: Numbers that indicate the result of the request.
  * | Code | Meaning              |
    | ---- | -------------------- |
    | 200  | OK (success)         |
    | 201  | Created (after POST) |
    | 400  | Bad request          |
    | 401  | Unauthorized         |
    | 404  | Not found            |
    | 500  | Server error         |

* **Headers**: Metadata about the request or response
  * Example
    * Content-Type: application/json 
    * Authorization: Bearer <token>

**Summary**

| **Component**   | **What it Does**                          |
| --------------- | ----------------------------------------- |
| Endpoint (URL)  | Identifies the resource                   |
| HTTP Method     | Tells the server what action to take      |
| Path Variable   | Targets a specific item                   |
| Query Parameter | Adds filters or options                   |
| Request Body    | Sends data to create/update               |
| Response Body   | Returns data from the server              |
| Status Code     | Indicates result of the request           |
| Headers         | Sends metadata (e.g., auth, content type) |



---
## 6. **Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL.**

* **cURL**: short for Client for URLs, is a command-line tool used to send requests to URLs — including REST APIs.
  * It works from the terminal or command prompt.
  * It supports many protocols (like HTTP, HTTPS, FTP).
  * It can be used to test APIs by typing raw HTTP requests.

**Example**
```bash
curl -X POST https://api.example.com/users \
  -H "Content-Type: application/json" \
  -d '{"name": "Alice"}'
```


* **Why need API testing tools like Postman instead of cURL?**

  * Postman is better for day-to-day API development, testing, and debugging, because of its visual interface, saved requests, testing features, and collaboration tools.

    | **Feature**               | **cURL**                               | **Postman**                                      |
    | ------------------------- | -------------------------------------- | ------------------------------------------------ |
    | **Interface**             | Command-line only                      | Graphical UI (user-friendly)                     |
    | **Ease of Use**           | Requires memorizing commands and flags | Point-and-click interface, very easy to learn    |
    | **Request History**       | Manual (you need to save commands)     | Automatically saves and organizes past requests  |
    | **Testing Collections**   | Not supported                          | Easily create and organize multiple requests     |
    | **Environment Variables** | Manual scripting                       | Built-in support for environments and variables  |
    | **Response View**         | Plain text                             | Pretty-printed JSON, status codes, time, headers |
    | **Collaboration**         | None                                   | Share workspaces and requests with your team     |


---
## 7. **List common HTTP status codes and their meanings.**

* **1xx – Informational**
  * | **Code** | **Meaning**         | **Description**                            |
    | -------- | ------------------- | ------------------------------------------ |
    | 100      | Continue            | Request received, keep going               |
    | 101      | Switching Protocols | Server is changing to a different protocol |

* **2xx – Success**
  * | **Code** | **Meaning** | **Description**                            |
    | -------- | ----------- | ------------------------------------------ |
    | 200      | OK          | Request succeeded (GET, PUT, DELETE, etc.) |
    | 201      | Created     | Resource was created (usually after POST)  |
    | 202      | Accepted    | Request accepted but not yet processed     |
    | 204      | No Content  | Success, but no data returned              |

* **3xx – Redirection**
  * | **Code** | **Meaning**                | **Description**                 |
    | -------- | -------------------------- | ------------------------------- |
    | 301      | Moved Permanently          | Resource has moved to a new URL |
    | 302      | Found (Temporary Redirect) | Resource temporarily moved      |
    | 304      | Not Modified               | Cached version is still valid   |

* **4xx – Client Errors**
  * | **Code** | **Meaning**          | **Description**                                       |
    | -------- | -------------------- | ----------------------------------------------------- |
    | 400      | Bad Request          | Invalid syntax or request data                        |
    | 401      | Unauthorized         | Authentication required (login or token missing)      |
    | 403      | Forbidden            | Authenticated, but no permission                      |
    | 404      | Not Found            | Resource doesn’t exist                                |
    | 405      | Method Not Allowed   | HTTP method not supported for this URL                |
    | 409      | Conflict             | Request conflicts with server state (e.g., duplicate) |
    | 422      | Unprocessable Entity | Validation failed (common in APIs)                    |

* **5xx – Server Errors**
  * | **Code** | **Meaning**           | **Description**                           |
    | -------- | --------------------- | ----------------------------------------- |
    | 500      | Internal Server Error | Server crashed or unknown error           |
    | 501      | Not Implemented       | Server doesn’t support this functionality |
    | 502      | Bad Gateway           | Invalid response from an upstream server  |
    | 503      | Service Unavailable   | Server is down or overloaded              |
    | 504      | Gateway Timeout       | Timeout from another server               |

    
---
## 8. **List HTTP methods and their meanings, and their expected HTTP status codes.**

| **Method**  | **Action**              | **Used For**                                      | **Expected Success Codes**              |
| ----------- | ----------------------- | ------------------------------------------------- | --------------------------------------- |
| **GET**     | Read / fetch data       | Retrieve data from the server                     | `200 OK`, `304 Not Modified`            |
| **POST**    | Create new data         | Send new data to the server (e.g., create a user) | `201 Created`, `200 OK`, `202 Accepted` |
| **PUT**     | Update entire resource  | Replace an existing resource                      | `200 OK`, `204 No Content`              |
| **PATCH**   | Update part of resource | Modify part of a resource                         | `200 OK`, `204 No Content`              |
| **DELETE**  | Remove a resource       | Delete data from the server                       | `200 OK`, `204 No Content`              |
| **HEAD**    | Fetch headers only      | Like GET, but no response body                    | `200 OK`, `204 No Content`              |
| **OPTIONS** | Check allowed methods   | Discover what methods are allowed for a URL       | `204 No Content`                        |


---
## 9. **Explain why REST API is stateless.**
* A stateless API means that each request is independent — the server doesn’t remember anything about previous requests. In other words: The client must send all the necessary data with every request, because the server doesn’t store session state between calls.
* **Why is stateless?**: 
  * Statelessness improves:
    * Scalability: Any server can handle any request — no need to store session info.
    * Simplicity: Each request is self-contained — easier to debug and cache
    * Reliability: No server-side memory of users — fewer errors across retries.
    * Load balancing: Requests can go to different servers without needing sticky sessions

---
## 10. **Discuss about best practices for REST API design, from performance perspective.**

* 1\. **Use Proper HTTP Methods**
  * Stick to `GET`, `POST`, `PUT`, `PATCH`, `DELETE` appropriately.
  * This helps caching, proxy optimization, and request routing.

* 2\. **Enable HTTP Caching**
  * Use headers like `Cache-Control`, `ETag`, and `Last-Modified`.
  * Let clients or CDNs avoid unnecessary repeat requests.
  * Example: Server replies with `304 Not Modified` if content hasn't changed.
    ```http
    GET /articles/123
    If-None-Match: "v2-etag-hash"
    ```

* 3\. **Use Pagination for Large Datasets**
  * Never return thousands of items in one call. Reduces response size and memory load on both client and server.
  * Use query parameters like:
      ```bash
      GET /users?page=2&limit=20
      ```

* 4\. **Support Filtering, Sorting, and Field Selection**
  * Let clients control the data they get.
  * Example:
    ```http
    GET /products?category=books&sort=price&fields=id,name,price
    ```

* 5\. **Avoid N+1 Query Problems**
  * If an endpoint returns a list of resources, make sure it doesn’t hit the database separately for each one.
  
    ❌ Bad:  
    `GET /orders` → loops and calls DB for each `order.customer`
    
    ✅ Good:  
    Use **joins** or **batch queries** internally.

* 6\. **Compress Responses**
  * Enable **gzip** or **brotli** compression on the server.
  * Cuts down bandwidth usage significantly, especially for large JSON responses.

* 7\. **Use Asynchronous Processing for Heavy Tasks**
  * For time-consuming actions (e.g., file upload, report generation), use background jobs and return a `202 Accepted` with a task ID.
    ```http
    POST /reports → 202 Accepted
    Location: /reports/789/status
    ```

* 8\. **Reduce Payload Size**
  * Don’t send unnecessary metadata or deeply nested objects.
  * Avoid verbose field names or unused fields.

* 9\. **Keep URLs Clean and Predictable**
  * Short, RESTful URLs help with caching and performance at edge/CDN levels.
      ```http
      ✅ GET /users/123
      ❌ GET /get-user-details-by-id?id=123
      ```
* 10\. **Use Rate Limiting and Throttling**
  * Protect your API from overload (intentional or not).
  * Send rate limit headers like:
      ```yaml
      X-RateLimit-Limit: 1000
      X-RateLimit-Remaining: 450
      ```

* 11\. **Use versioning**: Avoid breaking changes by using `/v1/` style or header-based versioning.

* 12\. **Monitor performance**: Track slow endpoints using logging and APM tools (like New Relic, Datadog).

* 13\. **Minimize database roundtrips**: Optimize query structure, use indexing, or consider caching layers.


---
## 11. **Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to avoid them.**

* **XSS (Cross-Site Scripting)**: 
  * XSS is when an attacker injects malicious JavaScript into a website, and that script runs in another user's browser.
  * If the site shows it directly on the page without sanitizing, every user who visits the page sees an alert — or worse, the attacker could steal cookies, tokens, or session info.
* **How to prevent XSS**:
* | **Strategy**                      | **What to do**                                              |
  | --------------------------------- | ----------------------------------------------------------- |
  | **Escape output**                 | Always encode user input before showing it in HTML          |
  | **Use frameworks safely**         | Use template engines that auto-escape (e.g. React, Angular) |
  | **Input validation**              | Only accept valid values (e.g., text, numbers)              |
  | **Content Security Policy (CSP)** | Blocks inline scripts and unknown sources                   |
  | **Avoid `innerHTML`**             | Use safer DOM methods like `textContent` or `appendChild`   |

* **CSRF (Cross-Site Request Forgery)**:
  * CSRF tricks a logged-in user’s browser into sending a request like deleting account, without their knowledge.
* **How to prevent CSRF**:
* | **Strategy**                   | **What to do**                                                                         |
  | ------------------------------ | -------------------------------------------------------------------------------------- |
  | **Use CSRF tokens**            | Include a unique token in each form/request and verify it server-side                  |
  | **SameSite cookies**           | Set cookies to `SameSite=Lax` or `Strict` so they're not sent with cross-site requests |
  | **Check Referer/Origin**       | On sensitive actions, validate the source of the request                               |
  | **Use authentication headers** | APIs should use tokens (e.g., Bearer) instead of relying on cookies                    |


---
## **API Practices:**
* Use Postman or other API testing tools to:
1. Find at least 5 different public APIs (e.g., GitHub APIs, Google Cloud APIs, GeoInfo APIs, Weather APIs and use them to explain what defines a REST API. These APIs can use any HTTP methods and may also include non-REST APIs (e.g., GraphQL). Some public APIs may require API keys (user registration required);
2. Justify whether these APIs follow API design best practices, and provide your better design for them.
---

   * **GitHub API:** Get User Profile
   * 
     | Field               | Example                                                             |
     | ------------------- |---------------------------------------------------------------------|
     | **Endpoint (URL)**  | `https://api.github.com/users/weixinliu618`                         |
     | **HTTP Method**     | `GET`                                                               |
     | **Path Variable**   | `octocat` (GitHub username)                                         |
     | **Query Parameter** | None                                                                |
     | **Request Body**    | None                                                                |
     | **Response Body**   | JSON: `{ "login": "WeixinLiu618", "id": 105184727, ... }`      |
     | **Status Code**     | `200 OK`, `404 Not Found`                                           |
     | **Headers**         | `Accept`, `User-Agent`, `X-RateLimit-Limit`, `X-RateLimit-Remaining` |
  
     * **RESTful?** Yes
     * **Best Practices:**
       * Uses HTTP methods correctly
       * Resource-based URL structure
     * **Suggested Improvement**: Include version in URL path (e.g., `/v3/users/weixinliu618`) for clarity.
---

   * **OpenWeatherMap API:** Get Weather by City
   * 
     | Field               | Example                                                                                      |
     | ------------------- |----------------------------------------------------------------------------------------------|
     | **Endpoint (URL)**  | `https://api.openweathermap.org/data/2.5/weather?q=London&appid=f02b97e4d1547dc6f36741a7e8227793` |
     | **HTTP Method**     | `GET`                                                                                        |
     | **Path Variable**   | None                                                                                         |
     | **Query Parameter** | `q=London`, `appid=API_KEY`                                                                  |
     | **Request Body**    | None                                                                                         |
     | **Response Body**   | JSON: `{ "weather": [...], "main": { "temp": 22 }, ... }`                                    |
     | **Status Code**     | `200 OK`, `401 Unauthorized`, `404 Not Found`                                                |
     | **Headers**         | `Accept`, `Content-Type`, `Cache-Control`                                                    |
     * **RESTful?** Yes
     * **Best Practices?:**
       * Issues: Too many query params, logic in parameters
     * **Suggested Improvement**: Use `/weather/city/London/?aapid=API_KEY` for clarity
---

   * **IP-API**: IP Geolocation Lookup
   * 
     | Field               | Example                                                  |
     | ------------------- | -------------------------------------------------------- |
     | **Endpoint (URL)**  | `http://ip-api.com/json/24.48.0.1`                       |
     | **HTTP Method**     | `GET`                                                    |
     | **Path Variable**   | `24.48.0.1` (IP address)                                 |
     | **Query Parameter** | Optional: `fields=country,city,isp`                      |
     | **Request Body**    | None                                                     |
     | **Response Body**   | JSON: `{ "country": "Canada", "city": "Montreal", ... }` |
     | **Status Code**     | `200 OK` or JSON with `"status": "fail"`                 |
     | **Headers**         | `Content-Type`, `Access-Control-Allow-Origin`            |
     * **RESTful?** Yes
     * **Best Practices?:**
       * Clean, resource-based, no auth required
       * Issue: fail did not return a status code
     * **Suggested Improvement**: 
       * Offer optional API key for rate-limiting and tracking
       * Provide fail status code if fail
---

   * **Dog API**: get dogs breeds infomation
   * 
     | **Field**           | **Example / Explanation**                                                                  |
     | ------------------- |--------------------------------------------------------------------------------------------|
     | **Endpoint (URL)**  | `https://dogapi.dog/api/v2/breeds` — returns a list of all dog breeds                      |
     | **HTTP Method**     | `GET`                                                                                      |
     | **Path Variable**   | None — full list returned from `/breeds`, no ID required in path                           |
     | **Query Parameter** | Optional: currently no documented filtering or pagination                                  |
     | **Request Body**    | None — standard GET request                                                                |
     | **Response Body**   | JSON: `{ "data": [ { "id": "labrador-retriever", "attributes": { "name": ..., ... } } ] }` |
     | **Status Code**     | `200 OK` on success;                                                                       |
     | **Headers**         | Includes: `Content-Type: application/json`, CORS headers, `Date`                           |

     * **RESTful?** Yes
     * **Best Practices?:**
       * Clean and versioned endpoint (/v2)
       * Issue: No documented error responses or pagination; No support for field filtering (e.g., ?fields=name)
     * **Suggested Improvement**:
       * Add support for pagination and field selection:?page=1&limit=20
       * Include error handling with appropriate HTTP status codes (400, 404, etc.)
       * Add optional API key support for analytics or rate-limiting if needed in the future
---

   * **Spotify API**: get artist data
   * 
     | **Field**           | **Example / Explanation**                                                               |
     | ------------------- |-----------------------------------------------------------------------------------------|
     | **Endpoint (URL)**  | `https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb`                             |
     | **HTTP Method**     | `GET` — retrieves artist metadata                                                       |
     | **Path Variable**   | `4Z8W4fKeB5YxbusRsdQVPb` — Spotify Artist ID                                            |
     | **Query Parameter** | None (not required or supported on this endpoint)                                       |
     | **Request Body**    | None                                                                                    |
     | **Response Body**   | JSON: `{ "name": "Radiohead", "genres": [...], "followers": {...}, "popularity": ... }` |
     | **Status Code**     | `200 OK` on success, `401 Unauthorized` if token is missing/invalid, `404` for bad ID   |
     | **Headers**         | `Authorization: Bearer {access_token}`, `Content-Type: application/json`, `Date`, etc.  |

       * **Best Practices?:**
         * Follows /v1/artists/{id} path pattern
         * Stateless and token-authenticated, uses Bearer token for secure access
       * **Suggested Improvement**:
         * Add field filtering, e.g.: `GET /v1/artists/4Z8W4fKeB5YxbusRsdQVPb?fields=name,popularity`
         * Expose rate-limiting info via headers (X-RateLimit-Limit, etc.)
---


3. List the above APIs in form of cURL commands , and attach Postman screenshots.

* **1\. GitHub API:** Get User Profile
```bash
  curl -X GET "https://api.github.com/users/weixinliu618"
```
![api-github.png](postman-screenshots/api-github.png)
---

* **2\. OpenWeatherMap API – Get Weather by City**

```bash
  curl -X GET "https://api.openweathermap.org/data/2.5/weather?q=London&appid=f02b97e4d1547dc6f36741a7e8227793" 
```
![api-openweathermap.png](postman-screenshots/api-openweathermap.png)

---
* **3\. IP-API – IP Geolocation Lookup**

```bash
curl -X GET "http://ip-api.com/json/24.48.0.1" 
```
![api-ip.png](postman-screenshots/api-ip.png)

---
* **4\. Dog API – Get Dog Breeds Information**

```bash
curl -X GET "https://dogapi.dog/api/v2/breeds"
```
![api-dog.png](postman-screenshots/api-dog.png)

---

* **5\. Spotify API – Get Artist Data**

```bash
curl "https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb" \
     -H "Authorization: Bearer  BQCoNr3I_E0hjsZanoBI89FLqADEMlU-HGQEutZyyAwvjhvGzCwlouSAkac3qctV-5QiELd71zv3F3rFbTONwAxZC6TT8ordrpMRAE7r2DGxrIebGA_OfFknDfhAhW05Qm0gqFzE8nY"

```
![api-spotify.png](postman-screenshots/api-spotify.png)

---


  4. List the request headers and response headers of the APIs mentioned above, and explain what each key-value pair in the headers section does.


### **1\. GitHub API – Get User Profile**

#### ✅ Request Headers:

| Header | Example Value | Purpose |
| --- | --- | --- |
| `Accept` | `application/vnd.github.v3+json` | Specifies desired API version and format |
| `User-Agent` | `curl` | Required by GitHub to identify client |

#### 🔁 Response Headers (examples):

| Header | Purpose |
| --- | --- |
| `Content-Type` | `application/json; charset=utf-8` – tells the client what type of data is returned |
| `X-RateLimit-Limit` | Max number of allowed requests per hour (e.g., `60`) |
| `X-RateLimit-Remaining` | Remaining requests in current window |
| `ETag` | Cache validator – helps clients avoid re-fetching unchanged data |
| `Status` | HTTP response status (e.g., `200 OK`) |

---

### **2\. OpenWeatherMap API – Get Weather by City**

#### ✅ Request Headers:

| Header | Purpose |
| --- | --- |
| `Accept` | `application/json` – tells the server client wants JSON |
| *(Optional)* | `Content-Type: application/json` – not needed for GET but may be included for clarity |

#### 🔁 Response Headers:

| Header | Purpose |
| --- | --- |
| `Content-Type` | Response data format (`application/json`) |
| `Cache-Control` | Caching behavior (e.g., `no-cache`, `max-age`) |
| `Access-Control-Allow-Origin` | CORS header for browser access |
| `Date` | Time the response was sent |
| `Status` | HTTP status (`200 OK`, `401`, `404`) |

---

### **3\. IP-API – IP Geolocation Lookup**

#### ✅ Request Headers:

| Header | Purpose |
| --- | --- |
| `Content-Type` | `application/json` – tells the server what format client expects |

#### 🔁 Response Headers:

| Header | Purpose |
| --- | --- |
| `Content-Type` | Response content format |
| `Access-Control-Allow-Origin` | Allows CORS from any domain (`*`) |
| `Status` | HTTP status (`200 OK` or included in JSON body) |

---

### **4\. Dog API – Get Dog Breeds Information**

#### ✅ Request Headers:

| Header | Purpose |
| --- | --- |
| `Accept` | `application/json` – client expects JSON |
| `Content-Type` | `application/json` – often used to indicate content being sent (even if empty) |

#### 🔁 Response Headers:

| Header | Purpose |
| --- | --- |
| `Content-Type` | Format of response (`application/json`) |
| `Date` | When the response was generated |
| `Access-Control-Allow-Origin` | CORS header to allow cross-domain access |
| `Status` | HTTP status code |

---

### **5\. Spotify API – Get Artist Data**

#### ✅ Request Headers:

| Header | Example Value | Purpose |
| --- | --- | --- |
| `Authorization` | `Bearer <ACCESS_TOKEN>` | OAuth 2.0 token for authentication |
| `Content-Type` | `application/json` | Indicates client is working with JSON |
| `Accept` (optional) | `application/json` | Specifies response format preference |

#### 🔁 Response Headers:

| Header | Purpose |
| --- | --- |
| `Content-Type` | Format of returned data |
| `WWW-Authenticate` | Returned when token is missing/invalid |
| `Retry-After` | (if rate-limited) how long to wait before retry |
| `X-RateLimit-Limit` | Max requests allowed per time window |
| `X-RateLimit-Remaining` | Remaining calls allowed |
| `X-RateLimit-Reset` | When the rate limit window resets |

---

### 📌 Summary of Header Roles

| Header | Meaning |
| --- | --- |
| `Accept` | Tells server what format client expects |
| `Content-Type` | Tells server/client what type of content is being sent or received |
| `Authorization` | Provides credentials (e.g., token) |
| `User-Agent` | Identifies client software |
| `X-RateLimit-*` | Provides rate limit usage details |
| `ETag` | Helps with conditional caching |
| `Cache-Control` | Controls caching behavior |
| `Access-Control-Allow-Origin` | Allows CORS requests (used in browsers) |
| `Date` | Timestamp of the response |
| `WWW-Authenticate` | Specifies how to authenticate on error |
---