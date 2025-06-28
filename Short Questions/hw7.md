### 1. Explain the concept of **API** (Application Programming Interface), why do we need APIs. [ref1](https://www.ibm.com/think/topics/api)  [ref2](https://aws.amazon.com/what-is/api/)

- What is an API? An API, or **application programming interface**, is a set of rules or protocols that enables software applications to **communicate** with each other to **exchange data, features and functionality**. For example, the weather bureau’s software system contains daily weather data. The weather app on your phone “talks” to this system via APIs and shows you daily weather updates on your phone.   
- why do we need APIs?  
APIs **simplify and accelerate** application and software development by allowing developers to **integrate data, services and capabilities from other applications, instead of developing them from scratch**.  
APIs also give application owners a **simple, secure** way to make their application data and functions available to departments within their organization. Application owners can also share or market data and functions to business partners or third parties.  
APIs allow for the **sharing of only the information necessary**, **keeping other internal system details hidden**, which helps with **system security**. Servers or devices do not have to fully expose data, APIs enable the **sharing of small packets of data, relevant to the specific request**.

### 2. Compare **developer API** vs **application API** (normal APIs).  
Developer API: **a set of library or framework methods** you invoke **in-process**—directly in your code—to perform operations on in-memory objects or local resources **without any network communication**.  
For example, calling String.toUpperCase() transforms text entirely within your application’s runtime.
```java
public class Example {
    public static void main(String[] args) {
        String greeting = "hello, world";
        // Call the toUpperCase() API on the String object
        String shout = greeting.toUpperCase();
        System.out.println(shout);  
    }
}

```

Application API: An Application API (or “Service API”) is a collection of **network-accessible endpoints** you **call over HTTP (or another protocol)** to interact with a remote service. Clients send requests (e.g., https://www.google.com/) and receive **serialized responses (JSON, XML), handling status codes, headers, and potential latency**.
```java
```

### 3. Name some different **types of APIs**.
1. WEB APIs: A Web API also called Web Services is an extensively used API over the web and can be easily **accessed using the HTTP protocols**. A Web application programming interface is an **open-source interface** and can be used by a **large number of clients** through their phones, tablets, or PCs.

2. LOCAL APIs: the programmers get the local **middleware services**. TAPI (Telephony Application Programming Interface), and .NET are common examples of Local APIs.

3. PROGRAM APIs: makes a **remote program appear to be local** by making use of **RPCs (Remote Procedural Calls)**.   
   (1) SOAP (SIMPLE OBJECT ACCESS PROTOCOL): SOAP (SIMPLE OBJECT ACCESS PROTOCOL): It defines messages in **XML format** used by **web applications to communicate with each other**. 
   (2) REST (Representational State Transfer): It **makes use of HTTP to GET, POST, PUT, or DELETE data**. It is basically used to **take advantage of the existing data**.  
   (3) JSON-RPC:  It **uses JSON for data transfer** and is a **lightweight remote procedural** call defining a few data structure types.  
   (4) XML-RPC: It is based on **XML and uses HTTP for data transfer**. This API is widely used to **exchange information** between **two or more networks**.  


### 4. Compare **path variables** vs **request parameters** in REST API. [ref](https://medium.com/nerd-for-tech/understanding-requestparam-and-pathvariable-in-spring-mvc-908e93abe88e)  
- Path Variables:  
A path variable is a dynamic segment embedded directly in the URL path that identifies a **specific resource (or nested resource)**. It’s mandatory for the route to resolve and usually corresponds to a unique identifier.  
Syntax: placed **between slashes**, e.g. /users/{userId}/orders/{orderId}  
Purpose: pinpoints exactly which resource you’re requesting or manipulating  
Example: 123 is a path variable, representing the user ID.  
```java
http://localhost:8080/api/users/123

```

```java
@RequestMapping("/api/users/{userId}")
public ResponseEntity<String> getUser(@PathVariable("userId") String userId) {
    // code to fetch the user with the given userId
}
```
In this case, the userId in the URL path will be passed to the getUser method. The URL: 'http://localhost:8080/api/users/123' will pass the value 123 to the userId parameter in the method.    

- Request Parameters  
A request parameter (also known as a query parameter) is passed after the ? in a URL as key–value pairs. They are generally optional and used to filter, sort, paginate, or modify how a collection endpoint behaves.  
Syntax: appended as ?key1=value1&key2=value2  
Purpose: provide auxiliary information or criteria without changing the resource’s identity      
Example: userId=123 is a query parameter.
```java
http://localhost:8080/api/users?userId=123

```

In this case, Spring will map the userId parameter **from the URL** to the **userId method parameter**. The URL:
```java
@RequestMapping("/api/users")
public ResponseEntity<String> getUser(@RequestParam("userId") String userId) {
    // code to fetch the user with the given userId
}
```

```java
http://localhost:8080/api/users?userId=123
```

### 6. Explain what **cURL** is and why we use API testing tools like **Postman** instead of testing APIs directly with cURL. [ref1](https://medium.com/@MissAmaraKay/what-is-curl-and-why-is-it-all-over-api-docs-b141c33805e0)   [ref2](https://www.reddit.com/r/webdev/comments/fw6hjv/what_is_the_benefit_to_using_api_testing_tools/)
(1) Definition: cURL, which stands for client URL and can be written as curl, is a **command line tool for file transfer with a URL syntax**. It supports a number of protocols including HTTP, HTTPS, FTP, and many more. HTTP/HTTPS makes it a great candidate for interacting with APIs!  
curl can be used on just about any platform on any hardware that exists today. This means, regardless of what you are running and where, the most basic curl commands should just work.  


(2) why we use API testing tools like **Postman** instead of testing APIs directly with cURL?  

Postman can:  
- Can **compose complex requests**, including headers, bodies, and authentication schemes. **Without memorizing or constructing lengthy command-line flags**;  
- Can **group those requests into shareable Collections**, define environment-specific variables (e.g. base URLs or tokens), and run automated test scripts or **monitors directly within the app**;
- Postman also provides **built-in support for OAuth flows, response visualization (pretty-printed JSON, timing metrics, charts), and auto-generated documentation**, which accelerates onboarding and ensures consistency across teams;  
- Moreover, its collaborative features—shared workspaces, versioning, and commenting, make it easy to maintain and evolve your API suite as projects grow, whereas tracking and organizing standalone cURL commands quickly becomes unwieldy;  

In short, while cURL is ideal for quick ad-hoc calls or CI-driven smoke tests, Postman (and similar tools) scale far better for ongoing development, testing, documentation, and team collaboration.


### 7. List common **HTTP status codes** and their meanings. [ref](https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status)


| Status Code Range | Category                  | Description                                                                                       |
|-------------------|---------------------------|---------------------------------------------------------------------------------------------------|
| 100–199           | Informational responses   | The server acknowledges and is processing the request.                                           |
| 200–299           | Successful responses      | The server successfully received, understood, and processed the request.                          |
| 300–399           | Redirection messages      | The server received the request, but there's a redirect to somewhere else (or additional action). |
| 400–499           | Client error responses    | The server couldn’t find (or reach) the page or website. This is an error on the site’s side.     |
| 500–599           | Server error responses    | The client made a valid request, but the server failed to complete the request.                  |
 
### 8. List **HTTP methods** and their meanings, and their expected **HTTP status codes**.

| HTTP Method | Meaning                                    | Expected Status Codes                                      |
|-------------|--------------------------------------------|------------------------------------------------------------|
| GET         | Retrieve a resource or list of resources   | 200 OK, 304 Not Modified, 404 Not Found                    |
| POST        | Create a new resource                      | 201 Created, 202 Accepted, 200 OK, 400 Bad Request         |
| PUT         | Replace or update a resource               | 200 OK, 204 No Content, 201 Created, 400 Bad Request, 404 Not Found |
| PATCH       | Partially update a resource                | 200 OK, 204 No Content, 400 Bad Request, 404 Not Found     |
| DELETE      | Remove a resource                          | 200 OK, 204 No Content, 404 Not Found                      |
| HEAD        | Retrieve only headers for a resource       | 200 OK, 304 Not Modified, 404 Not Found                    |
| OPTIONS     | Discover supported methods on a resource   | 204 No Content, 200 OK                                     |


### 9. Explain why **REST API is stateless**.
The definition of Stateless API: A stateless API **treats each request as an independent transaction** that is **unrelated to any previous request**. It **does not maintain any server-side state or session information about the client or previous requests**. **Each request from the client** to the server must **contain all the necessary data** for the server to process the request. The **server does NOT store** any context or session data between requests.

Stateless APIs are generally easier to design, implement, and scale because they don’t have to manage and maintain state information on the server-side. They are also more fault-tolerant because if one server instance fails, another instance can seamlessly handle the next request without any loss of context.

A REST API **IS stateless** because each HTTP request from a client to the server must contain all the information the server needs to fulfill that request—no client context (session data) is stored on the server between requests.

### 10. Discuss about best practices for **REST API design**, from **performance** perspective.


### 11. Explain the concept of **XSS (Cross-Site Scripting)** and **CSRF (Cross Site Request Forgery)** and how to avoid them.
- XSS Definition: XSS occurs when an attacker injects malicious scripts into web pages viewed by other users. The victim's browser executes these scripts, thinking they come from a trusted source.
  - How to Avoid:    
    Input validation: Sanitize and validate all user inputs on both client and server sides  
    Output encoding: Encode data before displaying it (convert < to &lt;, etc.)  
    Content Security Policy (CSP): Restrict which scripts can execute on your pages  
    Use secure frameworks: Modern frameworks like React automatically escape content by default  
    HTTPOnly cookies: Prevent JavaScript from accessing sensitive cookies  
 


- CSRF Definition: CSRF tricks authenticated users into performing unwanted actions on a web application where they're currently logged in. The attack leverages the user's existing authentication.
  - How to Avoid:  
    CSRF tokens: Include unique, unpredictable tokens in forms that the server validates  
    SameSite cookie attribute: Restrict when cookies are sent with cross-site requests  
    Referer/Origin header validation: Check that requests come from your own domain  
    Double submit cookies: Send CSRF token both as cookie and form parameter  
    Custom headers: Require custom headers for state-changing requests (AJAX calls can add these, but simple forms cannot)  


# API Practices: 

## Use **Postman** or other API testing tools to:

### 1. Find at least 5 different **public APIs** (e.g., GitHub APIs, Google Cloud APIs, GeoInfo APIs, Weather APIs) and use them to explain what defines a **REST API**. These APIs can use any HTTP methods and may also include **non-REST APIs** (e.g., GraphQL). Some public APIs may require API keys (user registration required);
GitHub API: https://docs.travis-ci.com/api/ 
Google Cloud API: https://developers.google.com/zero-touch/reference/customer/rest/
GeoInfo API: https://ipfind.io/
Weather API: https://publicapi.dev/us-weather-api  


## (1) GitHub APIs:    
### Travis CI API V2.1 Public API Information

| Feature            | Details                                                                                                                           |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| **Base URL**       | - Open Source: `https://api.travis-ci.org`  <br/> - Private (com): `https://api.travis-ci.com`  <br/> - Enterprise: `https://<your-domain>/api` :contentReference[oaicite:0]{index=0} |
| **Authentication** | - **Access token** in header: `Authorization: token <YOUR_TRAVIS_ACCESS_TOKEN>`  <br/> - **Accept** header: `application/vnd.travis-ci.2.1+json` :contentReference[oaicite:1]{index=1} |
| **HTTPS**          | Yes :contentReference[oaicite:2]{index=2}                                                                                                           |
| **CORS Support**   | Not documented (primarily intended for server-to-server use) :contentReference[oaicite:3]{index=3}                                                    |

#### Core Endpoints

| Method | Endpoint                                      | Description                                               | Success Code          |
|--------|-----------------------------------------------|-----------------------------------------------------------|-----------------------|
| GET    | `/repos/{owner}/{repo}`                       | Retrieve repository details                               | 200 OK :contentReference[oaicite:4]{index=4}    |
| GET    | `/repos/{owner}/{repo}/branches`              | List branches for a repository                            | 200 OK :contentReference[oaicite:5]{index=5}    |
| GET    | `/builds`                                     | List builds (filterable by repository, branch, etc.)      | 200 OK :contentReference[oaicite:6]{index=6}    |
| GET    | `/jobs`                                       | List jobs (filterable by build, repository, etc.)         | 200 OK :contentReference[oaicite:7]{index=7}    |
| GET    | `/requests`                                   | List requests (e.g., repository requests)                 | 200 OK :contentReference[oaicite:8]{index=8}    |
| GET    | `/accounts`                                   | List accessible accounts (user/org) — requires authentication | 200 OK :contentReference[oaicite:9]{index=9}    |
| POST   | `/auth/github`                                | Exchange a GitHub token for a Travis CI access token      | 200 OK :contentReference[oaicite:10]{index=10}    |
| GET    | `/config`                                     | Retrieve Travis CI configuration (e.g., GitHub API URL, scopes) | 200 OK :contentReference[oaicite:11]{index=11}    |

> **Note:** This is the API V2.1 reference; users are encouraged to migrate to API V3 (`https://developer.travis-ci.com`) for the latest features and consistency.
::contentReference[oaicite:12]{index=12}


## (2) Google Cloud APIs  
### Google Zero-Touch Enrollment Customer REST API

| Feature        | Details                                                                                           |
|----------------|---------------------------------------------------------------------------------------------------|
| **Base URL**   | `https://androiddeviceprovisioning.googleapis.com` :contentReference[oaicite:0]{index=0}                              |
| **Authentication** | OAuth 2.0 Bearer token with scope `https://www.googleapis.com/auth/androidworkzerotouchemm` :contentReference[oaicite:1]{index=1} |
| **HTTPS**      | Yes :contentReference[oaicite:2]{index=2}                                                                            |

#### Endpoints

##### v1.customers
| Method | Path               | Description                          | Success Code            |
|--------|--------------------|--------------------------------------|-------------------------|
| GET    | `/v1/customers`    | Lists the user’s customer accounts.  | 200 OK :contentReference[oaicite:3]{index=3} |

##### v1.customers.configurations
| Method | Path                                                                      | Description                                 | Success Code            |
|--------|---------------------------------------------------------------------------|---------------------------------------------|-------------------------|
| POST   | `/v1/{parent=customers/*}/configurations`                                 | Creates a new configuration.                | 200 OK :contentReference[oaicite:4]{index=4} |
| DELETE | `/v1/{name=customers/*/configurations/*}`                                 | Deletes an unused configuration.            | 200 OK :contentReference[oaicite:5]{index=5} |
| GET    | `/v1/{name=customers/*/configurations/*}`                                 | Gets the details of a configuration.        | 200 OK :contentReference[oaicite:6]{index=6} |
| GET    | `/v1/{parent=customers/*}/configurations`                                 | Lists a customer’s configurations.          | 200 OK :contentReference[oaicite:7]{index=7} |
| PATCH  | `/v1/{configuration.name=customers/*/configurations/*}`                   | Updates a configuration’s field values.     | 200 OK :contentReference[oaicite:8]{index=8} |

##### v1.customers.devices
| Method | Path                                                                     | Description                                                                 | Success Code            |
|--------|--------------------------------------------------------------------------|-----------------------------------------------------------------------------|-------------------------|
| POST   | `/v1/{parent=customers/*}/devices:applyConfiguration`                    | Applies a configuration to register a device for zero-touch enrollment.      | 200 OK :contentReference[oaicite:9]{index=9} |
| GET    | `/v1/{name=customers/*/devices/*}`                                       | Gets the details of a device.                                               | 200 OK :contentReference[oaicite:10]{index=10} |
| GET    | `/v1/{parent=customers/*}/devices`                                       | Lists a customer’s devices.                                                 | 200 OK :contentReference[oaicite:11]{index=11} |
| POST   | `/v1/{parent=customers/*}/devices:removeConfiguration`                   | Removes a configuration from a device.                                       | 200 OK :contentReference[oaicite:12]{index=12} |
| POST   | `/v1/{parent=customers/*}/devices:unclaim`                               | Unclaims a device from a customer and removes it from zero-touch enrollment. | 200 OK :contentReference[oaicite:13]{index=13} |

##### v1.customers.dpcs
| Method | Path                             | Description                                                                            | Success Code            |
|--------|----------------------------------|----------------------------------------------------------------------------------------|-------------------------|
| GET    | `/v1/{parent=customers/*}/dpcs`  | Lists the DPCs (device policy controllers) that support zero-touch enrollment.         | 200 OK :contentReference[oaicite:14]{index=14} |


## (3) GeoInfo APIs   
### IPfind.io Public API Information

| Feature           | Details                                                                                                           |
|-------------------|-------------------------------------------------------------------------------------------------------------------|
| **Base URL**      | `https://app.ipfind.io/api/iplocation` :contentReference[oaicite:0]{index=0}                                                              |
| **Authentication**| API Key passed as a query parameter:  
`?apikey=YOUR_APIKEY` :contentReference[oaicite:1]{index=1}                                                               |
| **HTTPS**         | Yes :contentReference[oaicite:2]{index=2}                                                                                               |
| **CORS Support**  | Not explicitly documented (likely allowed)                                                                          |

#### Endpoints

| Method | Endpoint                                                      | Description                                                      | Success Code                    |
|--------|---------------------------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/iplocation?apikey={apikey}`                             | Returns geolocation for the client’s own IP address              | 200 OK :contentReference[oaicite:3]{index=3}          |
| GET    | `/api/iplocation?apikey={apikey}&ip={IP_or_domain}`           | Returns geolocation for the specified IPv4/IPv6 address or domain| 200 OK :contentReference[oaicite:4]{index=4}          |

#### Request Parameters

- `apikey` (string, required): Your personal API key.
- `ip` (string, optional): The IP address (v4 or v6) or domain name to look up. If omitted, the client’s source IP is used. :contentReference[oaicite:5]{index=5}

#### Response Format

- All responses are JSON objects containing fields such as `continent`, `country`, `city`, `latitude`, `longitude`, `timezone`, etc.
- On error (e.g., invalid key or bad request), the API returns an appropriate 4xx status code with an error message in the JSON body. :contentReference[oaicite:6]{index=6}


## (4) Weather APIs  

### US Weather API Public API Information

| Feature           | Details                                                           |
|-------------------|-------------------------------------------------------------------|
| **Base URL**      | `https://api.weather.gov` :contentReference[oaicite:0]{index=0}                       |
| **Authentication**| Unknown :contentReference[oaicite:1]{index=1}                                       |
| **HTTPS**         | Yes :contentReference[oaicite:2]{index=2}                                           |
| **CORS Support**  | Yes :contentReference[oaicite:3]{index=3}                                           |

#### Endpoints

| Method | Endpoint                                                 | Description                                                              | Success Code    |
|--------|----------------------------------------------------------|--------------------------------------------------------------------------|-----------------|
| GET    | `/points/{latitude},{longitude}/stations`                | Retrieves the nearest weather station for the specified coordinates.     | 200 OK :contentReference[oaicite:4]{index=4} |
| GET    | `/points/{latitude},{longitude}/forecast`                | Retrieves the weather forecast for the specified coordinates.            | 200 OK :contentReference[oaicite:5]{index=5} |
| GET    | `/ridges/{radarId}/radar`                                | Retrieves radar imagery for the specified radar station ID.              | 200 OK :contentReference[oaicite:6]{index=6} |




#### What Defines a REST API

A **REST API** is one that adheres to Roy Fielding’s REST architectural principles:

- **Resource Identification in URIs:**  
  Every resource (user, book, weather report) has a unique URI (e.g., `/repos/{owner}/{repo}`, `/volumes`).

- **Uniform Interface:**  
  Uses standard HTTP methods (GET, POST, PUT, DELETE, etc.) with well-defined semantics.

- **Statelessness:**  
  Each request contains all information needed (authentication tokens or query parameters), and the server holds no client context between requests.

- **Cacheability:**  
  Responses explicitly indicate whether they are cacheable via HTTP headers (`Cache-Control`, `ETag`), improving performance.

- **Layered System:**  
  Clients cannot tell whether they’re talking directly to the server, a proxy, or a load balancer—allowing scalable architectures.

- **Optional – HATEOAS (Hypermedia as the Engine of Application State):**  
  Responses can include hyperlinks to related resources, guiding clients through available actions.

### 2. Justify whether these APIs follow **API design best practices**, and provide your better design for them.

(1) GitHub API
- Follow the best practice?  NO
  Multiple base URLs and version hidden in Accept header (hard to discover).   
  Mixed “global” (e.g. /builds, /jobs) and repo-scoped endpoints (not all under /repos/{owner}/{repo}).  
  No consistent query-param conventions for paging/filtering.  
  Non-RESTful paths (e.g. POST /auth/github).  
  CORS and HATEOAS not addressed.

- Better Design
### Improved Travis CI API Design

**Base URL & Versioning**
- **Base URL:** `https://api.travis-ci.com/v3/`
- **Versioning:** Version number in the path (`/v3/`), use standard `Accept: application/json`

**Authentication**
- **Bearer Token:**
  ```http
  Authorization: Bearer <YOUR_ACCESS_TOKEN>


(2) Google Cloud API --  Google Zero-Touch Enrollment Customer REST API
- Follow the best practice? Yes

- Better Design -- no need


(3) GeoInfo API -- IPfind.io API Design
- Follow the best practice?  
No API version in the URL or headers—clients can’t target a stable contract.    
API key in the query string (`?apikey=…`) risks accidental leakage (e.g. in logs or Referer).  
Single, intuitive endpoint (`/api/iplocation`) for the resource.  
Overloading query parameters for both “own IP” and “specific IP/domain” rather than using a clear path vs. query split.  
Returns 4xx on client faults, 200 on success.  
No documented error payload schema or codes beyond HTTP status.    
No versioned docs, no OpenAPI spec, and CORS support is unclear—hard for browser clients to adopt.  

- Better Design  

Resource-first URIs
```
GET /v2/location                  # Geo-locate caller’s IP
GET /v2/location/{ipOrDomain}     # Geo-locate specified IP or domain
```
Authorization: Bearer YOUR_API_KEY


(4) Weather APIs -- v2.1 APIs
- Follow the best practice?  
No version in URL or headers—clients can’t lock to a stable contract.                                              
None—opens risk of abuse; schools best practice call for an API key or user-agent requirement for rate limiting.   
No standard query parameters for filtering (e.g. date ranges) or units (metric vs. imperial).                   

- Better Design    
Version in path for discoverability and stability.
Authentication
```http
User-Agent: YourAppName/1.0  # required for usage tracking  
Authorization: Bearer YOUR_API_KEY  # optional, enables higher rate limits  


  Standard Error Envelope
```http
HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "error": {
    "status": 404,
    "title": "Location not found",
    "detail": "No weather stations found near 0.0000,0.0000"
  }
}
 
```

Standard Error Envelope
```http
HTTP/1.1 404 Not Found
Content-Type: application/json

{
  "error": {
    "status": 404,
    "title": "Location not found",
    "detail": "No weather stations found near 0.0000,0.0000"
  }
}
```


### 3. List the above APIs in form of **cURL commands**, and attach **Postman** screenshots in your markdown submission.
![GitHub APIs](1.jpg)
![Google Cloud APIs](2.jpg)   
![GeoInfo APIs](3.jpg)  
![Weather APIs](4.jpg)  

### 4. List the **request headers** and **response headers** of the APIs mentioned above, and explain what each key-value pair in the **headers** section does.  

# Request & Response Headers Overview

Below are the key request and response headers for each public API we discussed, with explanations of what each header does.

---

## 1. GitHub REST API

### Request Headers

| Header         | Example Value                           | Description                                                                                              |
|----------------|-----------------------------------------|----------------------------------------------------------------------------------------------------------|
| `Accept`       | `application/vnd.github+json`           | Informs GitHub which media type (version) of the API you expect. :contentReference[oaicite:0]{index=0}                     |
| `Authorization`| `token YOUR_PERSONAL_ACCESS_TOKEN`      | Your personal access token for higher rate limits and private‐repo access.                                 |
| `User-Agent`   | `MyApp/1.0`                             | Identifies your client application; required by GitHub to track usage.                                     |
| `Content-Type` | `application/json`                      | Required on requests with a JSON body (e.g. POST, PATCH).                                                 |

### Response Headers

| Header                 | Example Value                                                  | Description                                                                                       |
|------------------------|----------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `Content-Type`         | `application/json; charset=utf-8`                              | Indicates the response body is JSON UTF-8.                                                       |
| `X-RateLimit-Limit`    | `60`                                                           | Total number of requests permitted in this window.                                               |
| `X-RateLimit-Remaining`| `59`                                                           | Number of requests remaining in the current window.                                              |
| `X-RateLimit-Reset`    | `1372700873`                                                   | UNIX timestamp when the rate limit resets.                                                       |
| `ETag`                 | `"W/\"abc12345\""`                                             | Value used for conditional requests (If-None-Match).                                              |
| `Link`                 | `</repos?page=2>; rel="next", </repos?page=34>; rel="last"`    | Pagination links to fetch additional pages. :contentReference[oaicite:1]{index=1}                                   |
| `Cache-Control`        | `public, max-age=60, s-maxage=60`                              | Instructs clients/CDNs to cache responses for up to 60 seconds.                                   |
| `Vary`                 | `Accept, Authorization`                                        | Indicates which request headers affect the response.                                              |
| `Date`                 | `Wed, 01 Jan 2025 12:00:00 GMT`                                | Timestamp when the response was generated.                                                        |

---

## 2. Google Zero-Touch Enrollment Customer REST API

### Request Headers

| Header           | Example Value                          | Description                                                        |
|------------------|----------------------------------------|--------------------------------------------------------------------|
| `Authorization`  | `Bearer YOUR_OAUTH2_ACCESS_TOKEN`      | OAuth2 bearer token for `https://www.googleapis.com/auth/androidworkzerotouchemm` scope. :contentReference[oaicite:2]{index=2} |
| `Accept`         | `application/json`                     | Requests a JSON response.                                          |
| `Content-Type`   | `application/json`                     | Required on requests with a JSON body (POST, PATCH).              |
| `User-Agent`     | `MyApp/1.0`                            | Identifies your client; Google recommends including contact info. |

### Response Headers

| Header                  | Example Value                                 | Description                                                      |
|-------------------------|-----------------------------------------------|------------------------------------------------------------------|
| `Content-Type`          | `application/json; charset=UTF-8`             | Indicates the response body is JSON UTF-8.                      |
| `Cache-Control`         | `no-cache, private`                           | Responses are not cached publicly.                              |
| `X-Content-Type-Options`| `nosniff`                                     | Instructs browsers not to MIME-sniff the response.              |
| `Date`                  | `Mon, 27 Jan 2025 15:30:00 GMT`               | Timestamp of the response.                                      |
| `Server`                | `ESF`                                         | Google’s frontend server identifier.                            |
| `Alt-Svc`               | `h3=":443"; ma=2592000`                       | HTTP/3 alternative service advertisement.                       |

---

## 3. IPfind.io Geolocation API

### Request Headers

| Header         | Example Value           | Description                                            |
|----------------|-------------------------|--------------------------------------------------------|
| `Accept`       | `application/json`      | Requests a JSON-formatted response.    |
| `Authorization`| `Bearer YOUR_API_KEY`   | (Optional) if you switch to header-based auth.         |
| `User-Agent`   | `MyApp/1.0`             | Identifies your client.                                |

### Response Headers

| Header                      | Example Value                | Description                                                   |
|-----------------------------|------------------------------|---------------------------------------------------------------|
| `Content-Type`              | `application/json`           | Indicates the response body is JSON.        |
| `Access-Control-Allow-Origin`| `*`                         | Allows any origin to read the response (CORS).               |
| `Cache-Control`             | `no-store`                   | Instructs clients not to cache responses.                     |
| `Date`                      | `Fri, 27 Jun 2025 19:00:00 GMT`| Timestamp when the response was sent.                          |
| `Connection`                | `keep-alive`                 | Keeps the TCP connection open for subsequent requests.        |

---

## 4. US Weather (api.weather.gov) API

### Request Headers

| Header             | Example Value                                      | Description                                                                          |
|--------------------|----------------------------------------------------|--------------------------------------------------------------------------------------|
| `Accept`           | `application/geo+json`                             | Requests GeoJSON-formatted weather data. :contentReference[oaicite:5]{index=5}                         |
| `User-Agent`       | `myweatherapp.com, contact@myweatherapp.com`       | Required to identify your application; NOAA may contact you for abuse concerns. :contentReference[oaicite:6]{index=6} |
| `Accept-Encoding`  | `gzip, deflate`                                    | Allows compressed responses for efficiency.                                         |

### Response Headers

| Header                   | Example Value                              | Description                                                                                             |
|--------------------------|--------------------------------------------|---------------------------------------------------------------------------------------------------------|
| `Content-Type`           | `application/geo+json; charset=utf-8`      | Indicates GeoJSON response body.                                                                        |
| `Cache-Control`          | `max-age=300`                               | Advises clients to cache the response for 300 seconds. :contentReference[oaicite:7]{index=7}                              |
| `Last-Modified`          | `Wed, 26 Jun 2025 18:50:00 GMT`             | Timestamp when data was last updated; used with `If-Modified-Since`. :contentReference[oaicite:8]{index=8}               |
| `Expires`                | `Fri, 27 Jun 2025 19:05:00 GMT`             | Absolute expiration time for caches.                                                                    |
| `ETag`                   | `"abcdef123456"`                            | Validator for conditional requests (`If-None-Match`).                                                   |
| `Access-Control-Allow-Origin` | `*`                                    | Enables cross-origin requests from any domain.                                                         |
| `Date`                   | `Fri, 27 Jun 2025 19:00:00 GMT`             | Timestamp when the response was generated.                                                              |

---

## 5. Travis CI API V2.1

### Request Headers

| Header            | Example Value                                | Description                                                                            |
|-------------------|----------------------------------------------|----------------------------------------------------------------------------------------|
| `Accept`          | `application/vnd.travis-ci.2.1+json`         | Specifies the Travis API version 2.1 media type. :contentReference[oaicite:9]{index=9}                       |
| `Authorization`   | `token YOUR_TRAVIS_ACCESS_TOKEN`             | Your Travis CI API access token for authentication.                                    |
| `User-Agent`      | `MyClient/1.0`                               | Identifies your application to Travis.                                                 |
| `Content-Type`    | `application/json`                           | Required when sending a JSON body (e.g. POST to trigger a build). :contentReference[oaicite:10]{index=10} |

### Response Headers

| Header                    | Example Value                        | Description                                                                                            |
|---------------------------|--------------------------------------|--------------------------------------------------------------------------------------------------------|
| `Content-Type`            | `application/json; charset=utf-8`    | Indicates the response body is JSON.                                                                   |
| `Travis-API-Version`      | `2.1`                                | Echoes the API version serving your request.                                                           |
| `Cache-Control`           | `no-store`                            | Instructs clients not to cache build-trigger responses.                                                |
| `Access-Control-Allow-Origin` | `*`                              | Allows browser-based clients to call Travis API.                                                       |
| `Date`                    | `Fri, 27 Jun 2025 19:00:00 GMT`      | Timestamp of the response.                                                                             |
| `Server`                  | `Apache`                             | Underlying HTTP server software.                                                                       |

