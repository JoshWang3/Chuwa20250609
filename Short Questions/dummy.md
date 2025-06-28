# 1. Explain the concept of API (Application Programming Interface), why do we need APIs.  
API enables us to ignote the internal implementation of a program, and make the communication safer and simpler.

# 2. Compare developer API vs application API (normal APIs)  

## 1️⃣ What is an Application API (“Normal API”)?<br>1️⃣ 什么是应用程序 API（“普通 API”）？

- General term for any API that allows applications to communicate.
<br>允许应用程序通信的任何 API 的通用术语。

- Used by applications (clients) to access specific functionality from another application or service.
<br>由应用程序 （客户端） 用于从其他应用程序或服务访问特定功能。

- Example: 例：

    - A weather app calling a weather service’s API to get current temperature.<br>一个天气应用，它调用天气服务的 API 来获取当前温度。

    - Using the Twitter API to fetch tweets.<br>使用 Twitter API 获取推文。

### Characteristics: 特性：

✅ Exposes specific functionalities of an application/service. <br>✅ 公开应用程序/服务的特定功能。

✅ Focused on consuming the service’s data or functionality.
<br>✅ 专注于使用服务的数据或功能。

✅ Abstracts internal logic, exposing only required endpoints.
<br>✅ 抽象内部逻辑，仅公开所需的端点。

✅ Used by frontend apps, mobile apps, other services to consume data.
<br>✅ 用于前端应用程序、移动应用程序、其他服务来消耗数据。

## 2️⃣ What is a Developer API? <br>2️⃣ 什么是开发者 API？
- A type of Application API specifically designed for external developers to use.
<br>专为外部开发人员使用而设计的 Application API 类型。

- It provides programmatic access to a platform’s or service’s functionality, enabling developers to build new applications or integrate with the platform.
<br>它提供对平台或服务功能的编程访问，使开发人员能够构建新的应用程序或与平台集成。

- Often public or partner-facing and comes with: <br>通常公开或面向合作伙伴，并附带：

    - Documentation <br>文档

    - SDKs

    - Rate limits <br>速率限制

    - Authentication methods (API keys, OAuth) <br>身份验证方法（API 密钥、OAuth）

### Examples: 例子：

- GitHub API allowing developers to automate repository management.
<br>GitHub API 允许开发人员自动化仓库管理。

- Stripe API for developers to integrate payment processing.
<br>Stripe API，供开发人员集成支付处理。

- OpenAI API for developers to integrate AI models.
<br>OpenAI API，供开发人员集成 AI 模型。

✅ Key Differences Table <br>✅ 主要差异表

| Aspect                   | Application API                                      | Developer API                                                           |
| ------------------------ | ---------------------------------------------------- | ----------------------------------------------------------------------- |
| **Scope**                | Any API used by apps to access service functionality<br>应用用于访问服务功能的任何 API | APIs **specifically exposed for developers** to build apps or integrate<br>暴露给开发人员用于构建应用程序或集成的API  |
| **Audience**             | Used internally or externally<br>内部或外部使用                        | Specifically designed for **external developers**<br>专为外部开发人员设计                       |
| **Documentation & SDKs** | May or may not have detailed docs<br>可能有也可能没有详细的文档                    | Detailed documentation, SDKs, sandbox environments provided<br>提供详细的文档、开发工具包、沙盒环境             |
| **Access Control**       | May have internal controls<br>可能有内部控制                           | Often has **public-facing authentication, rate limits**<br>通常具有面向公众的身份验证、速率限制                 |
| **Example**              | Weather app calling weather service<br>天气应用程序调用天气服务                  | Developers using Stripe to build payment flows<br>使用 Stripe 构建支付流程的开发人员                          |

✅ Summary <br>✅ 总结
- All Developer APIs are Application APIs, but not all Application APIs are Developer APIs.
<br>所有开发人员 API 都是应用程序 API，但并非所有应用程序 API 都是开发人员 API。

    - Application API: Generic term for APIs between systems, can be internal or external.<br>应用程序 API：系统之间 API 的通用术语，可以是内部的，也可以是外部的。

    - Developer API: Public-facing APIs designed explicitly for developers to build on top of a platform or service.<br>开发人员 API：专为开发人员在平台或服务之上构建的面向公众的 API。
# 3. Name some different types of APIs  

We have different API according to Architectural Styles:  <br>根据架构风格,API可分为：
## REST APIs:  
Representational State Transfer APIs are the most common type, using standard HTTP methods (GET, POST, PUT, DELETE) to interact with resources. They are known for their simplicity and flexibility. 
<br>具象状态传输 API 是最常见的类型，使用标准 HTTP 方法（GET、POST、PUT、DELETE）与资源交互。它们以其简单性和灵活性而闻名。

## SOAP APIs:  

Simple Object Access Protocol APIs use XML for communication and are often found in enterprise systems with strict security and standards. 
<br>简单对象访问协议 API 使用 XML 进行通信，通常出现在具有严格安全性和标准的企业系统中。

## GraphQL APIs:  

GraphQL allows clients to request exactly the data they need, improving performance and flexibility, according to Amazon. 
<br>据 Amazon 称，GraphQL 允许客户准确请求他们需要的数据，从而提高性能和灵活性。

## RPC APIs:  

Remote Procedure Call APIs are designed for performing specific actions or functions, often used in internal systems. 
<br>远程过程调用 API 旨在执行特定作或功能，通常用于内部系统。

## gRPC APIs:  

gRPC is a high-performance, open-source framework that uses protocol buffers and HTTP/2 for efficient inter-service communication. 
<br>gRPC 是一个高性能的开源框架，它使用协议缓冲区和 HTTP/2 实现高效的服务间通信。

## Webhooks:  

Webhooks are a way for one application to send real-time data to another when a specific event occurs. 
<br>Webhook 是一个应用程序在发生特定事件时将实时数据发送到另一个应用程序的一种方式。

## WebSocket API
- WebSocket API Overview The WebSocket API enables two-way interactive communication between a user's browser and a server, facilitating real-time data transfer. 
<br>WebSocket API概述Websocket API启用用户浏览器和服务器之间的双向交互式通信，从而促进实时数据传输。

- Functionality Unlike traditional REST APIs that handle requests and responses sequentially, WebSocket APIs support full-duplex communication, allowing data to flow freely in both directions. 
<br>与传统的REST API不同，该功能依次处理和响应，WebSocket API支持全双工通信，从而允许数据在两个方向上自由流动。

- Use Cases WebSocket APIs are particularly useful for applications that require real-time updates, like chat applications, online gaming, and live notifications.
<br>用例Websocket API对于需要实时更新的应用进程特别有用，例如聊天应用进程，在线游戏和实时通知。

## MCP API

The MCP API, referring to the Model Context Protocol API, is a standardized way for AI models to interact with external tools and data sources. It simplifies the process of connecting AI agents to various APIs, databases, and other resources, acting as a sort of "USB-C port" for AI applications. Unlike traditional APIs, MCP is specifically designed for Large Language Models (LLMs) and their unique needs in accessing and utilizing context. 
<br>MCP API（即模型上下文协议 API）是 AI 模型与外部工具和数据源交互的一种标准化方式。它简化了将 AI 代理连接到各种 API、数据库和其他资源的过程，充当 AI 应用程序的“USB-C 端口”。与传统 API 不同，MCP 专为大型语言模型 （LLM） 及其在访问和利用上下文方面的独特需求而设计。

# 4. Compare path variables vs request parameters in REST API.  

![Parameter](./parameter.png)
```
http://localhost:8080/userapp/users/{id}/load?minAge={minAge}&lastName={lastName}
http://localhost:8080/userapp/users/456/load?minAge=25&lastName=Stark
```
1️⃣ What are Path Variables? 1️⃣ 什么是路径变量？
- Part of the URL path used to identify a specific resource.
<br>URL 路径的一部分，用于标识特定资源。

- Declared using {} in the URL pattern. <br>在 URL 模式中声明 using{}。

Example:

Path Varibale: {id}
```java
@GetMapping("/users/{id}")
@ResponseBody
public String getUserById(@PathVariable String id) {
    return "ID: " + id;
}
http://localhost:8080/spring-mvc-basics/users/{id}
http://localhost:8080/spring-mvc-basics/users/1234
---
ID: 1234
```
## 2️⃣ What are Request Parameters (Query Parameters)? <br>2️⃣ 什么是请求参数（Query Parameters）？
- Passed after ? in the URL as key-value pairs.
<br>在 URL中'?'后面作为键值对传递。

- Used to filter, sort, or modify the response without changing the resource identity.
<br>用于筛选、排序或修改响应，而无需更改资源标识。

Example:

Request/Query Parameter: ?key1=value1&key2=value2
```java
@GetMapping("/foos")
@ResponseBody
public String getFooByIdUsingQueryParam(@RequestParam String id, String place) {
    return "ID: " + id;
}
http://localhost:8080/spring-mvc-basics/foos?id=abc&place=NewYork
---
ID: abc, Placce: NewYork

```

When do we use path variable? when do we use request parameter?

|Feature|@PathVariable|@RequestParam|
|------|------|------|
|Usage|Path variables<br>路径变量|Query parameters<br>查询参数|
|Mapping|Maps to URL template variables<br>映射到URL模板变量|Maps to request parameters with matching names<br>映射到具有匹配名称的请求参数|
|Position|Must be placed directly on the method parameter<br>必须直接放置在方法参数上|Can be placed anywhere in the method parameter list<br>可以将其放置在方法参数列表中的任何地方|
|Required|Always required<br>始终必需|Can be optional or required(default is required)<br>可以是可选的或必需的（默认是必需的）|
|Binding|Binds directly to the URI template variable<br>直接绑定到URI模板变量|Optional binding to a default value if not present<br>可选绑定与默认值如果不存在|
|URL Encoding|Automatically decoded<br>自动解码|Required for special characters in parameter values. Failing to do so can lead to unexpected behavior, errors, and misinterpretation of the URL by web servers and browsers.<br>参数值中的特殊字符需要URL编码。 否则可能会导致 Web 服务器和浏览器对 URL 的意外行为、错误和误解。|
|Example|@PathVariable("varName")<br>String varValue|@RequestParam("paramName")<br>String paramValue|




# 5. Explain the different components that make up a RESTful API and what does each part do?  
## 1️⃣ Resources (URIs)
Represent data entities the API exposes.

Identified by URLs.

Example:

`/users/123`

represents the user with ID `123`.

What it does:

Defines what the API operates on.

    Query Parameters, path variables

## 2️⃣ HTTP Methods
Used to perform actions on resources, mapping to CRUD operations:

|HTTP Method|	Purpose|
|------|------|
|GET|	Retrieve resource(s)|
|POST|	Create a new resource|
|PUT|	Update/replace an existing resource|
|PATCH|	Partially update a resource|
|DELETE|	Delete a resource|

What it does:

Defines what action is performed on the resource.

## 3️⃣ HTTP Status Codes

Communicate the result of the request to the client.

Common examples:

`200 OK` – Successful request

`201 Created` – Resource created

`204 No Content` – Request succeeded, no response body

`400 Bad Request` – Client error (invalid input)

`401 Unauthorized` – Authentication required

`404 Not Found` – Resource not found

`500 Internal Server Error` – Server error

What it does:
Provides standardized feedback to clients about the request outcome.

## 4️⃣ Request Header

Describe the request and the client.
请求标头：描述请求和客户端。

Common headers:
- `User-Agent`: Identifies the client software (browser, operating system, etc.).
<br>User-Agent：标识客户端软件（浏览器、作系统等）。

- `Content-Type:` Indicates request/response body type (`application/json`, etc.).

- `Authorization`: Contains tokens for authentication.
<br>包含用于身份验证的凭证(credentials)。

- `Accept`: Specifies response format the client expects.
<br>指定客户端可以处理的媒体类型。

What it does:
Facilitates content negotiation, authentication, and metadata exchange.

## 5️⃣ Request Body
Used with POST, PUT, PATCH methods to send data to the server for creating or updating resources.

Example:

```
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

What it does:
Carries data needed for creation or update operations.

## 6️⃣ Response Header

Describe the response and the server. Access control.
<br>响应标头：描述响应和服务器.

XSS: Prevent cross-site scripting

Content-Type: Specifies the media type of the response body.
<br>Content-Type：指定响应正文的媒体类型。

Cache-Control: Defines caching behavior for the response.
<br>Cache-Control：定义响应的缓存行为。

Set-Cookie: Sends a cookie to the client to be stored.
<br>Set-Cookie：将 Cookie 发送到客户端进行存储。




Server: Identifies the server software. Server：标识 Server 软件。
Think of it like this: A request header is like ordering food at a restaurant - you tell the waiter what you want and how you want it served. A response header is like receiving the food and information about it, such as how it was prepared and if you need to pay now or later.
<br>可以这样想：请求标头就像在餐厅点餐 - 您告诉服务员您想要什么以及您希望如何供应。响应标头就像接收食物和有关它的信息，例如它是如何准备的以及您是否需要现在或以后付款。

## 7️⃣ Response Body
Data returned to the client, often in JSON format.

Example:

```
{
  "id": 123,
  "name": "John Doe",
  "email": "john@example.com"
}
```

What it does:

Delivers resource data or result data back to the client.
# 6. Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL.  
✅ Why we still learn and use cURL:
<br>✅ 为什么我们仍然学习和使用 cURL：

✅ Lightweight, available on all systems.
<br>✅ 重量轻，适用于所有系统。

✅ Useful for quick checks over SSH on servers.
<br>✅ 用于对 SSHon 服务器的快速检查。

✅ Good for automation in shell scripts.
<br>✅ 适合在 shell 脚本中实现自动化。

✅ Why Postman is preferred for API testing:
<br>✅ 为什么 Postman 是 API 测试的首选：

✅ User-friendly, organized, supports environments. <br>✅ 用户友好、有序、支持环境。
✅ Better visualization, advanced testing, and documentation capabilities.
<br>✅ 更好的可视化、高级测试和文档功能。

✅ Ideal for collaborative, structured API testing and learning.
<br>✅ 非常适合协作、结构化的 API 测试和学习。

# 7. List common HTTP status codes and their meanings.  

`200 OK` – Successful request

`201 Created` – Resource created

`204 No Content` – Request succeeded, no response body

`400 Bad Request` – Client error (invalid input)

`401 Unauthorized` – Authentication required

`404 Not Found` – Resource not found

`500 Internal Server Error` – Server error

# 8. List HTTP methods and their meanings, and their expected HTTP status codes.  
POST: 201 Created
GET: 200 OK
PUT: 
DELETE:
# 9. Explain why REST API is stateless.  

| HTTP Method | Purpose                    | Typical Status Codes                  |
| ----------- | -------------------------- | ------------------------------------- |
| **GET**     | Retrieve resource          | `200`, `404`, `401/403`               |
| **POST**    | Create new resource        | `201`, `400`, `401/403`, `409`        |
| **PUT**     | Replace resource           | `200`, `204`, `400`, `404`, `401/403` |
| **PATCH**   | Partially update resource  | `200`, `204`, `400`, `404`, `401/403` |
| **DELETE**  | Delete resource            | `200`, `204`, `404`, `401/403`        |
| **HEAD**    | Retrieve headers           | `200`, `404`                          |
| **OPTIONS** | Retrieve supported methods | `204`, `200`                          |


# 10. Discuss about best practices for REST API design, from performance perspective.  

✅ Use GET for safe, idempotent retrievals. ✅ UseGET 进行安全、幂等的检索。

✅ Use POST for non-idempotent resource creation. ✅ UsePOST 创建非幂等资源。

✅ Use PUT when the client knows the resource URI and wants to replace it.
✅ UsePUT当客户端知道资源 URI 并希望替换它时。

✅ Use PATCH for partial updates. ✅ UsePATCH进行部分更新。

✅ Use DELETE for removals and ensure idempotency (deleting twice should still return success).
✅ UseDELETE进行删除并确保幂等性（删除两次仍应返回成功）。

✅ Return appropriate status codes to improve API clarity and debugging for clients.
✅ return适当的状态代码来提高 API 的清晰度和客户端的调试。

# 11. Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to avoid them.
1️⃣ What is XSS (Cross-Site Scripting)?
Definition: 定义：
XSS is a security vulnerability that allows attackers to inject malicious scripts (usually JavaScript) into webpages viewed by other users.
XSS是一种安全漏洞，允许攻击者将恶意脚本（通常是 JavaScript）注入其他用户查看的网页中。

How it works: 运作方式：
An attacker injects a malicious script into a webpage (e.g., via a comment field).
攻击者将恶意脚本注入网页（例如，通过评论字段）。

Another user visits the page and the malicious script executes in their browser.
另一个用户访问该页面，恶意脚本在其浏览器中执行。

The script can: 该脚本可以：

Steal cookies or session tokens. 窃取 cookie 或会话令牌。

Log keystrokes. 记录击键。

Redirect to malicious sites. 重定向到恶意网站。

Perform actions on behalf of the user.
代表用户执行作。

Types of XSS: XSS 的类型：
Stored XSS: Malicious script is permanently stored on the server (e.g., in a comment or profile).
存储的 XSS：恶意脚本永久存储在服务器上（例如，在评论或个人资料中）。

Reflected XSS: Malicious script is reflected off the server in the response (e.g., via URL parameters).
反射型 XSS：恶意脚本在响应中反映出服务器（例如，通过 URL 参数）。

DOM-based XSS: Malicious script manipulates the DOM in the browser, without new server requests.
基于 DOM 的 XSS：恶意脚本在浏览器中纵 DOM，无需新的服务器请求。

How to prevent XSS: 如何防止 XSS：
✅ Input validation and sanitization: ✅输入验证和清理：

Escape special characters (<, >, ", ', &) before displaying user input.
在显示用户输入之前转义特殊字符 （<，>，“，'，&）。

✅ Use frameworks that auto-escape (React, Angular, Django templates).
✅使用自动转义的框架（React、Angular、Django 模板）。

✅ Use Content Security Policy (CSP) headers to limit executable scripts.
✅ UseContent Security Policy （CSP）标头来限制可执行脚本。

✅ Avoid eval() and direct innerHTML manipulations with untrusted data.
✅ Avoideval（）和 directinnerHTML作。

✅ Encode output based on context (HTML, JS, URL).
✅ 根据上下文（HTML、JS、URL）对输出进行编码。

✅ 2️⃣ What is CSRF (Cross-Site Request Forgery)?
✅ 2️⃣ 什么是 CSRF（跨站点请求伪造）？
Definition: 定义：
CSRF is a security vulnerability that forces a user to execute unwanted actions on a web application where they are authenticated.
CSRF是一种安全漏洞，它迫使用户在经过身份验证的 Web 应用程序上执行不需要的作。

How it works: 运作方式：
The victim is logged into trustedbank.com. 受害者被记录在 intotrustedbank.com。

The attacker tricks the victim into visiting malicious.com.
攻击者诱骗受害者进入 visitingmalicious.com。

malicious.com contains a hidden request: malicious.com 包含隐藏请求：

```html
<img src="https://trustedbank.com/transfer?to=attacker&amount=1000">
```
Since the victim’s browser automatically sends cookies/session tokens, the request is executed with victim’s credentials.
由于受害者的浏览器会自动发送 cookie/会话令牌，因此将使用受害者的凭据执行请求。

CSRF vs. XSS: CSRF 与 XSS：

|                      | **XSS**                                 | **CSRF**                                       |
| -------------------- | --------------------------------------- | ---------------------------------------------- |
| **What it exploits**<br>它利用什么 | Trust of **user in the website**<br>网站中的用户信任        | Trust of **website in the user's browser**     |
| **Attack goal**<br>攻击目标      | Run malicious scripts on user's browser<br>在用户的浏览器上运行恶意脚本 | Perform unwanted actions on behalf of the user |


✅ Practical Prevention Recap: ✅ 实用预防回顾：
✅ XSS: Validate & sanitize inputs, encode outputs, enable CSP.
✅XSS：验证和净化输入，编码输出，启用CSP。

✅ CSRF: Use CSRF tokens, SameSite cookies, validate request origins.
✅CSRF：使用 CSRF 令牌、SameSite Cookie、验证请求来源。

