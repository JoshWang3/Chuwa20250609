
# API Concepts and Practices

## 1. Explain the concept of API (Application Programming Interface), why do we need APIs

### **Interview wrap-up sentence**

An API is essentially the *language* software uses to collaborate—giving us modularity, security, and faster innovation while keeping each component cleanly separated.

------

### **Defination:**

An **API (Application Programming Interface)** is a formal *contract* that lets two independent pieces of software talk to each other without exposing their internal details.

------

### **What it is**

1. **Interface layer** – a set of clearly defined endpoints, inputs, and outputs that hide the underlying code.
2. **Contract** – both caller and provider agree on the same request/response format (e.g., JSON over HTTP).
3. **Abstraction** – consumers use well-named functions like GET /users/{id} instead of digging into database tables.

### **Why we need it**

| **Need**                    | **How an API solves it**                                     |
| --------------------------- | ------------------------------------------------------------ |
| **Loose coupling (解耦)**   | Changes inside one service don’t break others as long as the contract stays the same. |
| **Reuse & speed**           | Teams reuse existing services (payments, maps) instead of rebuilding them, shortening time-to-market. |
| **Security (安全性)**       | Only approved operations are exposed; internals and sensitive data stay hidden behind the interface. |
| **Scalability (可扩展性)**  | Clear boundaries let each service scale, deploy, or migrate technology stacks independently. |
| **Ecosystem & integration** | Public APIs (e.g., Stripe, GitHub) enable third-party apps and partner integrations, expanding product reach. |

## 2. Compare developer API vs application API (normal APIs)

### **Interview wrap-up sentence**

> “A developer API is a *library contract* you link into your codebase, while an application API is a *service contract* you call over the network; both expose functionality, but they differ in transport, audience, and failure characteristics.”

- **Core Idea:**

  Think of two layers of “talk”:

  - **Developer API** – the *code-level* contract a programmer calls inside the same process (e.g., a Java SDK, Python package).
  - **Application (normal) API** – the *network-level* contract an application calls across the wire (e.g., a REST/GraphQL endpoint).

  

  | **Dimension**               | **Developer API (Library / SDK)**                   | **Application API (Service / Remote)**                       |
  | --------------------------- | --------------------------------------------------- | ------------------------------------------------------------ |
  | **Location**                | Runs **in-process** with your code (method calls)   | Runs **out-of-process** over HTTP/HTTPS, gRPC, WebSocket     |
  | **Primary audience**        | Other **developers** compiling against the library  | Whole **applications / services / mobile clients**           |
  | **Typical form**            | Language-specific classes, functions, interfaces    | Language-agnostic URIs + HTTP methods or other wire protocols |
  | **Data exchange**           | Native objects in memory                            | Serialized payloads (JSON, Protobuf, XML)                    |
  | **Latency & failure modes** | Micro-seconds; failures are exceptions              | Milli-seconds+; failures are network timeouts, 4xx/5xx status |
  | **Versioning**              | Package/module version (e.g., Maven, npm)           | Endpoint versioning (/v1/users) or header negotiation        |
  | **Security scope**          | Compile-time visibility / language access modifiers | Transport-level security (TLS), auth tokens, rate limits     |
  | **Role in architecture**    | Encapsulates complexity, enforces coding standards  | Enables micro-service communication, third-party integrations |
  | **Examples**                | java.util.List, AWS SDK for Java                    | Stripe REST API, GET /users/{id} on your backend             |

## 3. Name some different types of APIs
- Open APIs (Public APIs)
- Partner APIs
- Internal APIs (Private APIs)
- Composite APIs
- REST APIs
- SOAP APIs
- GraphQL APIs

## 4. Compare path variables vs request parameters in REST API
- **Path Variables**: Identifiers within the URL path (e.g., `/users/{id}`).

- **Request Parameters**: Key-value pairs added after `?` in the URL (e.g., `/search?query=term`).

  A **path variable** is part of the resource’s identity (`/users/42`), while a **request (query) parameter** fine-tunes how that resource is retrieved or represented (`/users?role=admin&page=2`). Keeping these roles separate makes URLs predictable, cache-friendly, and easier to evolve. 

  [One sentence summary: Path variables answer **“which resource?”**; query parameters answer **“how would you like it?”**]

  ### **Best-practice thumb rules**

  1. **Resource identity → path**

     *Good*: GET /articles/2025-06-27

     *Poor*: GET /articles?id=2025-06-27

  2. **Optional or multi-value detail → query string**

     *Filters*: /articles?tag=java&tag=rest

     *Pagination*: /articles?page=3&size=50

  3. **Never overload a path variable** to pass optional flags—keep them in the query string.

  4. **Escape special characters** (%2F, %20) in path values so slashes and spaces don’t break routing.

## 5. Explain the different components that make up a RESTful API and what does each part do?
- **Endpoint (URI)**: Resource location.

- **HTTP Methods**: Define actions (GET, POST, etc.).

- **Headers**: Metadata (e.g., auth, content-type).

- **Body**: Carries data (mainly in POST/PUT).

- **Status Codes**: Indicate result of request.

  > **One-sentence takeaway:**

  > *URI* says **where**, *method* says **what**, *headers* say **how/under which rules**, *body* carries **data in or out**, and *status code* answers **how it went**, while optional hypermedia shows **what’s next**.

## 6. Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL
- **cURL** is a command-line tool for making HTTP requests.
- Tools like **Postman** provide a GUI, easier authentication handling, history, and collaboration, improving productivity for API testing.

## 7. List common HTTP status codes and their meanings
- 200 OK: Success
- 201 Created: Resource created
- 400 Bad Request: Invalid request
- 401 Unauthorized: No or invalid credentials
- 403 Forbidden: Access denied
- 404 Not Found: Resource not found
- 500 Internal Server Error: Server error

## 8. List HTTP methods and their meanings, and their expected HTTP status codes
- GET: Retrieve (200)
- POST: Create (201)
- PUT: Update (200 or 204)
- DELETE: Remove (204)
- PATCH: Partial update (200)

## 9. Explain why REST API is stateless
Each REST request contains all the information needed, and the server does not store session state between requests. This enhances scalability and reliability.

[A **REST (Representational State Transfer) API** is *stateless* because **the server never stores client context between requests; every call must carry all information needed to process it**. This design choice makes the service simpler, more scalable, and easier to cache.]

## 10. Discuss about best practices for REST API design, from performance perspective
- Use caching (e.g., ETag, Cache-Control)
- Paginate large results
- Minimize payload size
- Use efficient HTTP status codes
- Compress responses (gzip)

## 11. Explain the concept of XSS and CSRF and how to avoid them
- **XSS**: Injecting malicious scripts into web pages. Avoid by sanitizing inputs and using CSP headers.
- **CSRF**: Forging a request from authenticated user. Avoid by using tokens (e.g., CSRF tokens) and same-site cookies.

# API Practices

## 1. Find at least 5 different public APIs and explain what defines a REST API
Examples: GitHub API, OpenWeatherMap API, Google Maps API, NASA API, GeoDB Cities API

A **REST API** is a web service that follows the constraints of Representational State Transfer (client-server separation, statelessness, cacheability, layered system, uniform interface, optional code-on-demand) and uses standard HTTP verbs, URIs, headers, status codes, and representations (usually JSON) to expose *resources* such as **/users/42** or **/weather**. 

## 2. Justify whether these APIs follow API design best practices, and provide your better design for them
- Evaluation criteria: clear URI structure, use of HTTP methods, proper status codes, authentication mechanism

Below is a quick “score-card” for the five APIs, mapped against common REST best-practice criteria (clear resource URIs, correct verb usage, predictable status codes, pagination, caching, versioning, consistent error format, hypermedia, rate-limiting headers). I highlight ✔︎ (where they excel), ⚠️ (where they partly comply), and ❌ (where they fall short) and then show one or two **concrete redesign suggestions** that would nudge each API closer to an ideal REST style.

------

**1 · GitHub REST API v3 ✔︎✔︎✔︎**

**What it gets right**

| **Criterion**                   | **Verdict** | **Notes**                                                    |
| ------------------------------- | ----------- | ------------------------------------------------------------ |
| Resource-oriented URIs          | ✔︎           | GET /users/{username}, PATCH /repos/{owner}/{repo} …         |
| Proper verbs & status codes     | ✔︎           | Uses PATCH for partial update, returns 304 on conditional GETs |
| Pagination & rate-limit headers | ✔︎           | Link, X-RateLimit-*                                          |
| Caching                         | ✔︎           | ETag, Last-Modified, supports conditional requests           |
| Versioning                      | ⚠️           | Media-type (“Accept: application/vnd.github+json; version=2022-11-28”); harder for casual callers |
| Hypermedia (HATEOAS)            | ⚠️           | Link header but few in-body links objects                    |

**Better design**

```bash
# Path-based versioning for clarity
GET /v4/users/{username}

# Rich in-body links for discoverability
{
  "login": "octocat",
  "name": "Monalisa Octocat",
  "links": [
    { "rel": "repos", "href": "/v4/users/octocat/repos", "method": "GET" }
  ]
}
```

------

**2 · OpenWeatherMap API ⚠️**

| **Criterion**         | **Verdict** | **Notes**                                                    |
| --------------------- | ----------- | ------------------------------------------------------------ |
| URI clarity           | ⚠️           | /data/2.5/weather mixes nouns with a “data” prefix; city vs coords chosen by query string |
| Versioning            | ✔︎           | Numeric segment (2.5, 3.0) in path                           |
| HTTP verbs/codes      | ✔︎           | Simple GET; returns 404, 429                                 |
| Consistent error body | ❌           | Error payloads vary across endpoints                         |
| Caching headers       | ❌           | No ETag/Cache-Control on hourly data                         |

**Better design**

```bash
# Resource-first URIs
GET /v3/weather/cities/{cityId}
GET /v3/weather/coordinates/{lat},{lon}

# Standard error envelope
{
  "status": 404,
  "title": "City not found",
  "detail": "No weather data for cityId 999999"
}

# Strong caching
Cache-Control: public, max-age=600
ETag: "abc123"
```

------

**3 · NASA APOD ⚠️**

| **Criterion**              | **Verdict** | **Notes**                                 |
| -------------------------- | ----------- | ----------------------------------------- |
| URI                        | ⚠️           | Single endpoint /planetary/apod (RPC-ish) |
| Versioning                 | ❌           | None at path or header                    |
| Caching & CDN-friendliness | ⚠️           | Images are cacheable; JSON lacks ETag     |
| Pagination                 | N/A         | One record per day                        |
| Error schema               | ❌           | Plain text on failures                    |

**Better design**

```bash
GET /v1/apod/2025-06-27        # canonical date‐based resource
Accept: application/json

Cache-Control: public, max-age=86400
ETag: "2025-06-27"
```

Provide OpenAPI spec + structured error JSON.

------

**4 · Spotify Web API ✔︎**

| **Criterion**          | **Verdict** | **Notes**                                     |
| ---------------------- | ----------- | --------------------------------------------- |
| URI, verbs, status     | ✔︎           | /v1/tracks/{id}, proper GET/PUT/DELETE        |
| Versioning             | ✔︎           | /v1/… prefix                                  |
| Pagination / filtering | ✔︎           | limit, offset; Link headers on some endpoints |
| OAuth2 & rate-limit    | ✔︎           | 429 Too Many Requests with Retry-After        |
| Caching                | ⚠️           | No ETag on tracks/{id}                        |

**Minor tweaks**

- Add ETag / conditional GETs for static metadata
- Consistent Link header for all collection endpoints (some still rely on query params).

------

**5 · GeoDB Cities API ⚠️**

| **Criterion**      | **Verdict** | **Notes**                                   |
| ------------------ | ----------- | ------------------------------------------- |
| URI & version      | ✔︎           | /v1/geo/cities …                            |
| Pagination         | ✔︎           | offset/limit but **no** Link header         |
| Filtering          | ✔︎           | namePrefix, minPopulation, etc.             |
| Error body & codes | ⚠️           | Uses 400 but provides minimal detail        |
| Caching            | ❌           | City data seldom changes; could expose ETag |

**Better design**

```bash
GET /v1/geo/cities?namePrefix=seatt&limit=10
Link: <...&offset=10>; rel="next"

# Immutable city payloads => enable caching
Cache-Control: public, max-age=86400
ETag: "city-5809844"
```

### **Cross-API design patterns worth emulating**

1. **Consistent error envelope** – e.g., RFC 7807 *Problem Details* with type, title, status, detail.
2. **Hypermedia links** (rel, href, method) to advertise next actions.
3. **Cache & concurrency headers** (Cache-Control, ETag, If-Match) for GET/PUT.
4. **Path-based versioning** *or* media-type versioning—pick one and stick to it.
5. **Rate-limit metadata** (X-RateLimit-Remaining, Retry-After) across every API, not just a few.

> **Take-away:** GitHub and Spotify already hit most best-practice checkpoints; OpenWeatherMap, NASA APOD, and GeoDB could gain clarity, cacheability, and future-proofing by adopting resource-centric paths, stronger caching semantics, a uniform error model, and richer pagination/link metadata.

## 3. List the above APIs in form of cURL commands and attach Postman screenshots

Example:

```bash
curl -X GET "https://api.github.com/users/octocat" -H "Accept: application/vnd.github+json"
```

![hw7-1](./Homework/HW7/hw7-1.jpg)

![hw7-2](./Homework/HW7/hw7-2.jpg)

![hw7-3](./Homework/HW7/hw7-3.jpg)

![hw7-4](./Homework/HW7/hw7-4.jpg)

![hw7-5](./Homework/HW7/hw7-5.jpg)

## 4. List the request headers and response headers of the APIs mentioned above and explain them

### Example Headers:
- **Request Headers**:
  - `Authorization`: Access token or API key
  - `Content-Type`: Format of the request body (e.g., `application/json`)
- **Response Headers**:
  - `Content-Type`: Format of the response (e.g., `application/json`)
  - `Cache-Control`: Caching policy
  - `ETag`: Versioning of the resource

