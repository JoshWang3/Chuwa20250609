### Short Questions

1. What is an API and why do we need it?

An **API ** is a set of rules that allows different software systems to communicate with each other.
 **Why we need APIs:**

- Decouples frontend from backend
- Promotes modular development
- Enables reuse of services across applications
- Allows integration with third-party platforms (e.g., payment, maps)

2.Developer API vs Application API



| Feature         | Developer API                    | Application API (Internal/Normal API) |
| --------------- | -------------------------------- | ------------------------------------- |
| Target Audience | External developers              | Internal system modules               |
| Accessibility   | Often public and well-documented | Private or internal use               |
| Example         | GitHub API, Stripe API           | Login API, product listing API        |

3. Types of APIs

**REST API** – Resource-oriented, uses HTTP

**SOAP API** – XML-based, strict standards

**GraphQL API** – Client specifies exactly what data is needed

**WebSocket API** – Persistent two-way communication

**gRPC API** – High-performance binary protocol using Protobuf, good for microservices



4. Path Variables vs Query Parameters in REST API

| Feature  | Path Variable               | Query Parameter                |
| -------- | --------------------------- | ------------------------------ |
| Syntax   | `/users/{id}` → `/users/42` | `/users?page=2&limit=10`       |
| Purpose  | Identify specific resource  | Filtering, pagination, sorting |
| Position | In URL path                 | After `?` in URL               |

5. Components of a RESTful API

- **Endpoint**: URL path of the API (e.g., `/api/users`)
- **HTTP Method**: Action type – GET, POST, PUT, DELETE, etc.
- **Headers**: Metadata (e.g., `Content-Type`, `Authorization`)
- **Body**: Payload sent with POST/PUT requests (usually JSON)
- **Status Code**: Response code indicating result (e.g., 200 OK)



6. What is cURL, and why use Postman instead?



**cURL** is a command-line tool for making HTTP requests.

**Postman** is a GUI-based tool for API testing.
 **Why use Postman:**

Easy to send requests and view responses

Supports collections, environments, scripting

Better suited for collaboration and debugging



7. Common HTTP Status Codes



| Code | Meaning                       |
| ---- | ----------------------------- |
| 200  | OK – request succeeded        |
| 201  | Created – new resource        |
| 400  | Bad Request – client error    |
| 401  | Unauthorized – login required |
| 403  | Forbidden – no permission     |
| 404  | Not Found – resource missing  |
| 500  | Internal Server Error         |



8. HTTP Methods, Meanings, and Expected Status Codes



| Method | Purpose                 | Common Status Codes |
| ------ | ----------------------- | ------------------- |
| GET    | Retrieve data           | 200, 404            |
| POST   | Create resource         | 201, 400            |
| PUT    | Replace resource        | 200, 204, 400       |
| PATCH  | Update part of resource | 200, 204, 400       |
| DELETE | Remove resource         | 204, 404            |



9. Why is REST API stateless?

Each request is **independent** and contains all necessary information.

Server **does not store client state** between requests.

**Benefits**:

- Easier to scale
- Better caching
- More robust and fault-tolerant

10. Best Practices for REST API Design (Performance-Focused)

- Implement pagination, filtering, sorting
- Support partial responses (e.g., `fields=name,email`)
- Enable caching via headers (`ETag`, `Cache-Control`)
- Use compression (e.g., Gzip)
- Return only necessary data
- Use asynchronous processing for long-running tasks

   **11. What is XSS and CSRF? How can they be prevented?**

| Threat                                | Description                                                  | Example                            | Prevention                                                   |
| ------------------------------------- | ------------------------------------------------------------ | ---------------------------------- | ------------------------------------------------------------ |
| **XSS (Cross-Site Scripting)**        | Injecting malicious scripts into web pages                   | `<script>alert('hacked')</script>` | Input validation, output encoding, Content Security Policy (CSP) |
| **CSRF (Cross-Site Request Forgery)** | Exploiting a user's logged-in session to perform unwanted actions | Fake "change password" request     | CSRF tokens, SameSite cookies, 2FA                           |

