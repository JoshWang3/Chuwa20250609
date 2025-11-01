# 1. Explain the concept of API (Application Programming Interface), why do we need APIs.  
API enables us to ignote the internal implementation of a program, and make the communication safer and simpler.

# 2. Compare developer API vs application API (normal APIs)  

## 1️⃣ What is an Application API (“Normal API”)?1️⃣ 什么是应用程序 API（“普通 API”）？

- General term for any API that allows applications to communicate.
允许应用程序通信的任何 API 的通用术语。

- Used by applications (clients) to access specific functionality from another application or service.
由应用程序 （客户端） 用于从其他应用程序或服务访问特定功能。

- Example: 例：

    - A weather app calling a weather service’s API to get current temperature.一个天气应用，它调用天气服务的 API 来获取当前温度。

    - Using the Twitter API to fetch tweets.使用 Twitter API 获取推文。

### Characteristics: 特性：

✅ Exposes specific functionalities of an application/service. ✅ 公开应用程序/服务的特定功能。

✅ Focused on consuming the service’s data or functionality.
✅ 专注于使用服务的数据或功能。

✅ Abstracts internal logic, exposing only required endpoints.
✅ 抽象内部逻辑，仅公开所需的端点。

✅ Used by frontend apps, mobile apps, other services to consume data.
✅ 用于前端应用程序、移动应用程序、其他服务来消耗数据。

## 2️⃣ What is a Developer API? 2️⃣ 什么是开发者 API？
- A type of Application API specifically designed for external developers to use.
专为外部开发人员使用而设计的 Application API 类型。

- It provides programmatic access to a platform’s or service’s functionality, enabling developers to build new applications or integrate with the platform.
它提供对平台或服务功能的编程访问，使开发人员能够构建新的应用程序或与平台集成。

- Often public or partner-facing and comes with: 通常公开或面向合作伙伴，并附带：

    - Documentation 文档

    - SDKs

    - Rate limits 速率限制

    - Authentication methods (API keys, OAuth) 身份验证方法（API 密钥、OAuth）

### Examples: 例子：

- GitHub API allowing developers to automate repository management.
GitHub API 允许开发人员自动化仓库管理。

- Stripe API for developers to integrate payment processing.
Stripe API，供开发人员集成支付处理。

- OpenAI API for developers to integrate AI models.
OpenAI API，供开发人员集成 AI 模型。

✅ Key Differences Table ✅ 主要差异表

| Aspect                   | Application API                                      | Developer API                                                           |
| ------------------------ | ---------------------------------------------------- | ----------------------------------------------------------------------- |
| **Scope**                | Any API used by apps to access service functionality应用用于访问服务功能的任何 API | APIs **specifically exposed for developers** to build apps or integrate暴露给开发人员用于构建应用程序或集成的API  |
| **Audience**             | Used internally or externally内部或外部使用                        | Specifically designed for **external developers**专为外部开发人员设计                       |
| **Documentation & SDKs** | May or may not have detailed docs可能有也可能没有详细的文档                    | Detailed documentation, SDKs, sandbox environments provided提供详细的文档、开发工具包、沙盒环境             |
| **Access Control**       | May have internal controls可能有内部控制                           | Often has **public-facing authentication, rate limits**通常具有面向公众的身份验证、速率限制                 |
| **Example**              | Weather app calling weather service天气应用程序调用天气服务                  | Developers using Stripe to build payment flows使用 Stripe 构建支付流程的开发人员                          |

✅ Summary ✅ 总结
- All Developer APIs are Application APIs, but not all Application APIs are Developer APIs.
  所有开发人员 API 都是应用程序 API，但并非所有应用程序 API 都是开发人员 API。

    - Application API: Generic term for APIs between systems, can be internal or external.应用程序 API：系统之间 API 的通用术语，可以是内部的，也可以是外部的。

    - Developer API: Public-facing APIs designed explicitly for developers to build on top of a platform or service.开发人员 API：专为开发人员在平台或服务之上构建的面向公众的 API。
# 3. Name some different types of APIs  

We have different API according to Architectural Styles:  根据架构风格,API可分为：
## REST APIs:  
Representational State Transfer APIs are the most common type, using standard HTTP methods (GET, POST, PUT, DELETE) to interact with resources. They are known for their simplicity and flexibility. 
具象状态传输 API 是最常见的类型，使用标准 HTTP 方法（GET、POST、PUT、DELETE）与资源交互。它们以其简单性和灵活性而闻名。

## SOAP APIs:  

Simple Object Access Protocol APIs use XML for communication and are often found in enterprise systems with strict security and standards. 
简单对象访问协议 API 使用 XML 进行通信，通常出现在具有严格安全性和标准的企业系统中。

## GraphQL APIs:  

GraphQL allows clients to request exactly the data they need, improving performance and flexibility, according to Amazon. 
据 Amazon 称，GraphQL 允许客户准确请求他们需要的数据，从而提高性能和灵活性。

## RPC APIs:  

Remote Procedure Call APIs are designed for performing specific actions or functions, often used in internal systems. 
远程过程调用 API 旨在执行特定作或功能，通常用于内部系统。

## gRPC APIs:  

gRPC is a high-performance, open-source framework that uses protocol buffers and HTTP/2 for efficient inter-service communication. 
gRPC 是一个高性能的开源框架，它使用协议缓冲区和 HTTP/2 实现高效的服务间通信。

## Webhooks:  

Webhooks are a way for one application to send real-time data to another when a specific event occurs. 
Webhook 是一个应用程序在发生特定事件时将实时数据发送到另一个应用程序的一种方式。

## WebSocket API
- WebSocket API Overview The WebSocket API enables two-way interactive communication between a user's browser and a server, facilitating real-time data transfer. 
WebSocket API概述Websocket API启用用户浏览器和服务器之间的双向交互式通信，从而促进实时数据传输。

- Functionality Unlike traditional REST APIs that handle requests and responses sequentially, WebSocket APIs support full-duplex communication, allowing data to flow freely in both directions. 
与传统的REST API不同，该功能依次处理和响应，WebSocket API支持全双工通信，从而允许数据在两个方向上自由流动。

- Use Cases WebSocket APIs are particularly useful for applications that require real-time updates, like chat applications, online gaming, and live notifications.
用例Websocket API对于需要实时更新的应用进程特别有用，例如聊天应用进程，在线游戏和实时通知。

## MCP API

The MCP API, referring to the Model Context Protocol API, is a standardized way for AI models to interact with external tools and data sources. It simplifies the process of connecting AI agents to various APIs, databases, and other resources, acting as a sort of "USB-C port" for AI applications. Unlike traditional APIs, MCP is specifically designed for Large Language Models (LLMs) and their unique needs in accessing and utilizing context. 
MCP API（即模型上下文协议 API）是 AI 模型与外部工具和数据源交互的一种标准化方式。它简化了将 AI 代理连接到各种 API、数据库和其他资源的过程，充当 AI 应用程序的“USB-C 端口”。与传统 API 不同，MCP 专为大型语言模型 （LLM） 及其在访问和利用上下文方面的独特需求而设计。

# 4. Compare path variables vs request parameters in REST API.  

URL Structure:
![Parameter](./img/parameter.png)

Example: 

```
http://localhost:8080/userapp/users/{id}/load?minAge={minAge}&lastName={lastName}
http://localhost:8080/userapp/users/456/load?minAge=25&lastName=Stark
```
1️⃣ What are Path Variables? 1️⃣ 什么是路径变量？
- Part of the URL path used to identify a specific resource.
URL 路径的一部分，用于标识特定资源。

- Declared using {} in the URL pattern. 在 URL 模式中声明 using{}。

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
## 2️⃣ What are Request Parameters (Query Parameters)? 2️⃣ 什么是请求参数（Query Parameters）？
- Passed after ? in the URL as key-value pairs.
在 URL中'?'后面作为键值对传递。

- Used to filter, sort, or modify the response without changing the resource identity.
用于筛选、排序或修改响应，而无需更改资源标识。

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
|Usage|Path variables路径变量|Query parameters查询参数|
|Mapping|Maps to URL template variables映射到URL模板变量|Maps to request parameters with matching names映射到具有匹配名称的请求参数|
|Position|Must be placed directly on the method parameter必须直接放置在方法参数上|Can be placed anywhere in the method parameter list可以将其放置在方法参数列表中的任何地方|
|Required|Always required始终必需|Can be optional or required(default is required)可以是可选的或必需的（默认是必需的）|
|Binding|Binds directly to the URI template variable直接绑定到URI模板变量|Optional binding to a default value if not present可选绑定与默认值如果不存在|
|URL Encoding|Automatically decoded自动解码|Required for special characters in parameter values. Failing to do so can lead to unexpected behavior, errors, and misinterpretation of the URL by web servers and browsers.参数值中的特殊字符需要URL编码。 否则可能会导致 Web 服务器和浏览器对 URL 的意外行为、错误和误解。|
|Example|@PathVariable("varName")String varValue|@RequestParam("paramName")String paramValue|




# 5. Explain the different components that make up a RESTful API and what does each part do?  
## 1️⃣ Resources (URIs)
Represent data entities the API exposes.
代表 API 暴露的数据实体。

Identified by URLs.
通过 URL 进行标识。

Example:

`/users/123`

represents the user with ID `123`.
表示 ID 为 123 的用户。

What it does:
它的作用：

Defines what the API operates on.
定义 API 操作的对象。

It also includes Query Parameters, path variables.

## 2️⃣ HTTP Methods
Used to perform actions on resources, mapping to CRUD operations:
用于对资源执行操作，映射到 CRUD 操作：

|HTTP Method|	Purpose| CRUD|
|------|------|------|
|GET|	Retrieve resource(s)|Read|
|POST|	Create a new resource|Create|
|PUT|	Update/replace an existing resource|Update|
|PATCH|	Partially update a resource|Update|
|DELETE|	Delete a resource|Delete|

What it does:
它做什么：

Defines what action is performed on the resource.
定义对资源执行的操作。

## 3️⃣ HTTP Status Codes3️⃣ HTTP 状态码

Communicate the result of the request to the client.
向客户端传达请求的结果。

Common examples:

`200 OK` – Successful request
`200 OK` – 请求成功

`201 Created` – Resource created
`201 Created` – 资源已创建

`204 No Content` – Request succeeded, no response body
`204 No Content` – 请求成功，无响应体

`400 Bad Request` – Client error (invalid input)
`400 Bad Request` – 客户端错误（无效输入）

`401 Unauthorized` – Authentication required
`401 Unauthorized` – 需要认证

`404 Not Found` – Resource not found
`404 Not Found` – 资源未找到

`500 Internal Server Error` – Server error
`500 Internal Server Error` – 服务器错误

What it does:
它做什么：

Provides standardized feedback to clients about the request outcome.
向客户提供标准化的请求结果反馈。

## 4️⃣ Request Header

Describe the request and the client.
请求标头：描述请求和客户端。

Common headers:
- `User-Agent`: Identifies the client software (browser, operating system, etc.).
User-Agent：标识客户端软件（浏览器、作系统等）。

- `Content-Type:` Indicates request/response body type (`application/json`, etc.).

- `Authorization`: Contains tokens for authentication.
包含用于身份验证的凭证(credentials)。

- `Accept`: Specifies response format the client expects.
指定客户端可以处理的媒体类型。

What it does:
Facilitates content negotiation, authentication, and metadata exchange.
促进内容协商、身份验证和元数据交换。

## 5️⃣ Request Body

Used with POST, PUT, PATCH methods to send data to the server for creating or updating resources.
与 POST、PUT、PATCH 方法一起使用，用于向服务器发送数据以创建或更新资源。

Example:

```
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

What it does:
Carries data needed for creation or update operations.
携带创建或更新操作所需的数据。

## 6️⃣ Response Header

Describe the response and the server. Access control.
响应标头：描述响应和服务器.是为了限制对于服务器的访问权限.

XSS: Prevent cross-site scripting

Content-Type: Specifies the media type of the response body.
Content-Type：指定响应正文的媒体类型。

Cache-Control: Defines caching behavior for the response.
Cache-Control：定义响应的缓存行为。

Set-Cookie: Sends a cookie to the client to be stored.
Set-Cookie：将 Cookie 发送到客户端进行存储。

Server: Identifies the server software. Server：标识 Server 软件。

Headers Provide metadata about the request and response.标头提供关于请求和响应的元数据。

Request headers and response headers are not the same, although they are both part of the HTTP communication process and share the same basic structure (key-value pairs).
请求标头和响应标头并不相同，尽管它们都是 HTTP 通信过程的一部分，并且共享相同的基本结构（键值对）。

Think of it like this: A request header is like ordering food at a restaurant - you tell the waiter what you want and how you want it served. A response header is like receiving the food and information about it, such as how it was prepared and if you need to pay now or later.
可以这样想：请求标头就像在餐厅点餐 - 您告诉服务员您想要什么以及您希望如何供应。响应标头就像接收食物和有关它的信息，例如它是如何准备的以及您是否需要现在或以后付款。

## 7️⃣ Response Body
Data returned to the client, often in JSON format.
返回给客户端的数据，通常为 JSON 格式。

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
资源数据或结果数据发送回客户端。

| **Component**        | **What it does**                     |
| -------------------- | ------------------------------------ |
| **Resources (URIs)** | Identifies what entity to operate on识别要操作的实体 |
| **HTTP Methods**     | Defines actions on resources定义对资源的操作         |
| **Status Codes**     | Communicates request results传达请求结果         |
| **Headers**          | Metadata for requests/responses请求/响应的元数据      |
| **Request Body**     | Sends data for creation/update发送用于创建/更新的数据       |
| **Response Body**    | Returns data to the client返回数据给客户端           |
| **Query Parameters** | Allows filtering/sorting/pagination允许过滤/排序/分页  |


# 6. Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with cURL.

✅ What is cURL?✅ 什么是 cURL？
cURL (Client URL) is:cURL (Client URL) 是：

- A command-line tool for sending HTTP requests to servers.一个用于向服务器发送 HTTP 请求的命令行工具。

- Supports various protocols (HTTP, HTTPS, FTP, etc.).支持多种协议（HTTP、HTTPS、FTP 等）。

- Allows you to test APIs directly from the terminal.允许您直接从终端测试 API。

Example:示例：

To test a REST API:要测试 REST API：

```bash
curl -X GET https://api.example.com/users/123
```

To send JSON data:要发送 JSON 数据：

```bash
curl -X POST https://api.example.com/users \
     -H "Content-Type: application/json" \
     -d '{"name":"Alice","email":"alice@example.com"}'
```

## ✅ Why do we use API testing tools like Postman instead of cURL?✅ 为什么我们使用 Postman 等 API 测试工具，而不是 cURL？

### 1️⃣ Ease of Use1️⃣ 易用性

- cURL:

    - Requires memorizing command syntax.需要记忆命令语法。

    - Complex for large payloads, authentication, and chained requests.对于大负载、认证和链式请求来说复杂。

- Postman:

    - User-friendly GUI for sending requests and viewing responses.用户友好的 GUI 界面，用于发送请求和查看响应。

    - Easy to modify parameters, headers, and body fields.易于修改参数、标头和正文字段。

### 2️⃣ Better Response Visualization2️⃣ 更好的响应可视化
cURL: Returns raw response in terminal, harder to read large JSON responses.cURL: 在终端返回原始响应，阅读大型 JSON 响应较困难。

Postman: Formats JSON/XML responses with syntax highlighting, pretty view, and structure navigation.Postman: 格式化 JSON/XML 响应，带有语法高亮、美观视图和结构导航。

### 3️⃣ Organizing and Saving Requests3️⃣ 组织和保存请求
Postman: Allows saving API requests into collections, making it easy to rerun tests.Postman: 允许将 API 请求保存到集合中，便于重新运行测试。

Supports environments (dev, staging, prod) with variables for quick switching.支持开发、测试和生产等环境，通过变量实现快速切换。

Supports documentation alongside requests.支持在请求中附带文档。

### 4️⃣ Automated Testing and Scripting4️⃣ 自动化测试和脚本
Postman: Supports test scripts (JavaScript) to validate responses automatically.Postman：支持使用测试脚本（JavaScript）自动验证响应。

Useful for regression testing and CI/CD pipelines.适用于回归测试和 CI/CD 流程。

### 5️⃣ Authentication Handling5️⃣ 身份验证处理

- Postman supports:Postman 支持：

    - OAuth 2.0 flows

    - Bearer tokens

    - API keys

    - Digest/Basic Auth

- These are harder and more tedious to manage manually with cURL.这些通过 cURL 手动管理更加困难且繁琐。

### 6️⃣ Advanced Features6️⃣ 高级功能
Postman supports:Postman 支持：

✅ API Mocking✅ API 模拟

✅ Monitors for scheduled API tests✅ 定时 API 测试监控

✅ Visual documentation generation✅ 生成可视化文档

✅ Integration with CI tools for automated API tests✅ 与 CI 工具集成进行自动化 API 测试

## ✅ Summary Table✅ 摘要表

| Aspect             | **cURL**                   | **Postman**                       |
| ------------------ | -------------------------- | --------------------------------- |
| Interface          | Command-line               | GUI                               |
| Ease of Use易用性        | Requires syntax knowledge需要语法知识  | User-friendly drag-and-drop用户友好的拖放       |
| Response View响应视图      | Raw text原始文本                   | Pretty formatted JSON/XML格式化的 JSON/XML         |
| Request Management请求管理 | Manual history             | Save requests in collections保存请求到集合中      |
| Scripting脚本编写          | No built-in test scripting没有内置的测试脚本 | Automated testing with scripts使用脚本进行自动化测试    |
| Auth Handling认证处理      | Manual headers手动设置Header             | Built-in auth support内置认证支持             |
| Collaboration协作      | No                         | Share collections and docs easily轻松分享集合和文档 |

## ✅ Why we still learn and use cURL:✅ 为什么我们仍然学习和使用 cURL：

✅ Lightweight, available on all systems.
✅ 重量轻，适用于所有系统。

✅ Useful for quick checks over SSH on servers.
✅ 用于对 SSHon 服务器的快速检查。

✅ Good for automation in shell scripts.
✅ 适合在 shell 脚本中实现自动化。

## ✅ Why Postman is preferred for API testing:✅ 为什么 Postman 是 API 测试的首选：

✅ User-friendly, organized, supports environments. ✅ 用户友好、有序、支持环境。

✅ Better visualization, advanced testing, and documentation capabilities.
✅ 更好的可视化、高级测试和文档功能。

✅ Ideal for collaborative, structured API testing and learning.
✅ 非常适合协作、结构化的 API 测试和学习。

# 7. List common HTTP status codes and their meanings.  列出常见的HTTP状态代码及其含义

`200 OK` – Successful request
`200 OK` – 请求成功

`201 Created` – Resource created
`201 Created` – 资源已创建

`204 No Content` – Request succeeded, no response body
`204 No Content` – 请求成功，无响应体

`400 Bad Request` – Client error (invalid input)
`400 Bad Request` – 客户端错误（无效输入）

`401 Unauthorized` – Authentication required
`401 Unauthorized` – 需要认证

`404 Not Found` – Resource not found
`404 Not Found` – 资源未找到

`500 Internal Server Error` – Server error
`500 Internal Server Error` – 服务器错误

# 8. List HTTP methods and their meanings, and their expected HTTP status codes.列出HTTP方法及其含义及其预期的HTTP状态代码。
### 1️⃣ GET 

- Meaning: Retrieve data/resource from the server. 含义：从服务器检索 data/resource。

- Expected Status Codes: 预期状态代码：

    - `200 OK` → Successfully retrieved the resource.
`200 确定`→ 已成功检索资源。

    - `404 Not Found` → Resource does not exist.
`404 Not Found`→ 资源不存在。

    - `401 Unauthorized` / `403 Forbidden` → No access rights.
`401 未授权`/`403 禁止`→ 无访问权限。

Example: 例：

`GET /users/123` → Retrieve user with ID 123.
`GET /users/123`→检索 ID 为 123 的用户。

### 2️⃣ POST 
- Meaning: Create a new resource on the server.
含义：在服务器上创建一个新的资源。

- Expected Status Codes: 预期状态代码：

    - `201 Created` → Resource successfully created. `201 Created` → 资源已成功创建。

    - `400 Bad Request` → Invalid input data.
`400 错误请求`→输入数据无效。

    - `401 Unauthorized` / `403 Forbidden` → No access rights.
`401 未授权`/`403 禁止`→ 无访问权限。

    - `409 Conflict` → Resource conflict (e.g., duplicate entry).
`409 冲突`→资源冲突（例如，重复条目）。

Example: 例：

`POST /users` → Create a new user.
`POST /users`→ 创建新用户。

### 3️⃣ PUT 
- Meaning: Replace an existing resource with new data.
含义：用新数据替换现有资源。

- Expected Status Codes: 预期状态代码：

    - `200 OK` → Resource successfully updated, returns updated resource.
`200 OK`→ Resource successfully updated（资源已成功更新），返回更新的资源。

    - `204 No Content` → Successfully updated, no response body returned.
`204 No Content`→ 已成功更新，未返回响应正文。

    - `400 Bad Request` → Invalid input data.
`400 错误请求`→输入数据无效。

    - `401 Unauthorized` / `403 Forbidden` → No access rights.
`401 未授权`/`403 禁止`→ 无访问权限。

    - `404 Not Found` → Resource does not exist.
`404 Not Found`→ 资源不存在。

Example: 例：

`PUT /users/123` → Replace user 123’s data completely.
`PUT /users/123`→ 完全替换用户 123 的数据

### 4️⃣ PATCH 
- Meaning: Partially update an existing resource. 含义：部分更新现有资源。

- Expected Status Codes: 预期状态代码：

    - `200 OK` → Successfully updated, returns updated resource.
`200 OK`→ 已成功更新，返回更新的资源。

    - `204 No Content` → Successfully updated, no response body.
`204 无内容`→已成功更新，无响应正文。

    - `400 Bad Request` → Invalid input. `400 错误请求`→输入无效。

    - `401 Unauthorized` / `403 Forbidden` → No access rights.
`401 未授权`/`403 禁止`→ 无访问权限。

    - `404 Not Found` → Resource not found.
`404 Not Found`→ 未找到资源。

Example: 例：

`PATCH /users/123` → Update specific fields (e.g., just the email) of user 123.
`PATCH /users/123`→ 更新用户 123 的特定字段（例如，仅电子邮件）。

### 5️⃣ DELETE 
- Meaning: Delete a resource on the server. 含义：删除服务器上的一个资源。

- Expected Status Codes: 预期状态代码：

    - `200 OK` → Successfully deleted, may return confirmation data.
`200 OK`→ 已成功删除，可能会返回确认数据。

    - `204 No Content` → Successfully deleted, no response body.
`204 无内容`→ 已成功删除，无响应正文。

    - `401 Unauthorized` / `403 Forbidden` → No access rights.
`401 未授权`/`403 禁止`→ 无访问权限。

    - `404 Not Found` → Resource not found.
`404 Not Found`→ 未找到资源。

Example: 

`DELETE /users/123` → Delete user with ID 123.
`DELETE /users/123`→删除 ID 为 123 的用户。

### 6️⃣ HEAD 
- Meaning: Same as GET but returns only headers, no response body.
含义：与 GETbutt 相同，只返回 header，不返回响应体。

- Expected Status Codes: 预期状态代码：

    - `200 OK` → Resource exists. `200 OK`→资源存在。

    - `404 Not Found` → Resource not found.
`404 Not Found`→ 未找到资源。

Used for checking if a resource exists or inspecting headers.
用于检查资源是否存在或检查 headers。

### 7️⃣ OPTIONS 
- Meaning: Used to describe communication options for the target resource.
含义：用于描述目标资源的通信选项。

- Expected Status Codes: 预期状态代码：

    - `204 No Content` → Successful with headers listing allowed methods.
`204 No Content`→ 成功，标题列出允许的方法。

    - `200 OK` → Sometimes used to return options with a body.
`200 OK`→ 有时用于返回带有 body 的选项。

Commonly used for CORS preflight requests. 通常用于 CORS 预检请求。

## ✅ Summary Table ✅ 汇总表


| HTTP Method | Purpose                    | Typical Status Codes                  |
| ----------- | -------------------------- | ------------------------------------- |
| **GET**     | Retrieve resource          | `200`, `404`, `401/403`               |
| **POST**    | Create new resource        | `201`, `400`, `401/403`, `409`        |
| **PUT**     | Replace resource           | `200`, `204`, `400`, `404`, `401/403` |
| **PATCH**   | Partially update resource  | `200`, `204`, `400`, `404`, `401/403` |
| **DELETE**  | Delete resource            | `200`, `204`, `404`, `401/403`        |
| **HEAD**    | Retrieve headers           | `200`, `404`                          |
| **OPTIONS** | Retrieve supported methods | `204`, `200`                          |

## ✅ Best Practices to Remember✅ 要记住的最佳实践

✅ Use GET for safe, idempotent retrievals. ✅ 用GET 进行安全、幂等的检索。

✅ Use POST for non-idempotent resource creation. ✅ 用POST 创建非幂等资源。

✅ Use PUT when the client knows the resource URI and wants to replace it.
✅ 用PUT当客户端知道资源 URI 并希望替换它时。

✅ Use PATCH for partial updates. ✅ 用PATCH进行部分更新。

✅ Use DELETE for removals and ensure idempotency (deleting twice should still return success).
✅ 用DELETE进行删除并确保幂等性（删除两次仍应返回成功）。

✅ Return appropriate status codes to improve API clarity and debugging for clients.
✅ return适当的状态代码来提高 API 的清晰度和客户端的调试。

# 9. Explain why REST API is stateless. 

## 1️⃣ What does stateless mean? 1️⃣ 无状态是什么意思？
A stateless system means: Astateless 系统意味着：

Each request from the client to the server must contain all the information needed to understand and process the request.
从客户端到服务器的每个请求都必须包含理解和处理请求所需的所有信息。

The server does not store any client context between requests.
服务器在请求之间不存储任何客户端上下文。

In other words: 换句话说：

The server does not remember previous interactions with the client.
服务器不记得以前与客户端的交互。

Every request is independent and self-contained. 每个请求都是独立且自包含的。

## 2️⃣ Why is REST API designed to be stateless?
2️⃣ 为什么REST API被设计为无状态的？

REST (Representational State Transfer) intentionally enforces statelessness for the following reasons:
REST（Representational State Transfer）有意强制执行无状态性，原因如下：

✅ Simplicity ✅ 单纯

The server does not need to track sessions or client state, simplifying implementation and scaling.
服务器不需要跟踪会话或客户端状态，从而简化了实现和扩展。

✅ Scalability ✅ 可扩展性
Statelessness allows horizontal scaling: 无状态允许水平缩放：

Any server in a cluster can handle any request because there is no dependency on previous interactions.
集群中的任何服务器都可以处理任何请求，因为不依赖于以前的交互。

Load balancers can easily distribute requests to multiple servers.
负载均衡器可以轻松地将请求分发到多个服务器。

✅ Reliability ✅ 可靠性

No session state on the server means less risk of memory leaks or inconsistent states during server failures.
服务器上没有会话状态意味着在服务器故障期间内存泄漏或状态不一致的风险较小。

✅ Cacheability ✅ 可缓存性

Stateless requests allow intermediate caches (e.g., CDN, proxies) to store responses and reuse them without worrying about session state.
无状态请求允许中间缓存（例如 CDN、代理）存储响应并重用它们，而无需担心会话状态。

### ✅ Separation of concerns ✅ 关注点分离

Client manages its own state (e.g., pagination, authentication tokens), while the server focuses on processing requests and returning data.
Client 管理自己的状态（例如，分页、身份验证令牌），而 Server 则专注于处理请求和返回数据。

## 3️⃣ Example of stateless REST request 3️⃣ 无状态 REST 请求示例

When making: 

```http
GET /users/123
Authorization: Bearer <token>
```
The request must: 请求必须：

✅ Contain all necessary data (authentication token).
✅ 包含所有必要的数据（身份验证令牌）。

✅ Not rely on previous requests to determine what to return.
✅ 不要依赖以前的请求来确定要返回的内容。

## 4️⃣ But how do REST APIs handle authentication if stateless?4️⃣ 但是如果无状态，REST API 如何处理身份验证？
Instead of maintaining sessions, REST APIs use tokens (e.g., JWT) sent with each request.
REST API 使用随每个请求发送的令牌（例如 JWT），而不是维护会话。

The token contains user identity and authorization information, allowing the server to authenticate the client without remembering previous sessions.
该令牌包含用户身份和授权信息，允许服务器在不记住先前会话的情况下对客户端进行身份验证。

## ✅ Summary ✅ 总结

|                            |                                                                                                |
| -------------------------- | ---------------------------------------------------------------------------------------------- |
| **Statelessness in REST:** | Each request contains all needed information and is independent. 每个请求都包含所有需要的信息，并且是独立的。                              |
| **Why?**                   | For simplicity, scalability, reliability, and cacheability.实现简单性、可扩展性、可靠性和可缓存性。                                    |
| **Impact:**                | The server does not store client state; the client is responsible for maintaining its context.服务器不存储客户端状态;客户端负责维护其上下文。 |

# 10. Discuss about best practices for REST API design, from performance perspective. 

### 1. Use Proper HTTP Methods and Status Codes1.使用正确的 HTTP 方法和状态代码

- Use:

    - GET for retrieval (safe, cacheable). GET进行检索（安全、可缓存）。

    - POST for creation. POST进行创建。

    - PUT/PATCH for updates. PUT/PATCH进行更新。

    - DELETE for deletions. DELETE进行删除。

- Using correct methods allows HTTP caching, reduces ambiguity, and improves intermediaries' (CDNs, proxies) effectiveness.
使用正确的方法可以实现 HTTP 缓存，减少歧义，并提高中介（CDN、代理）的效率。

### 2. Enable Caching 2.启用缓存
- Use HTTP cache headers: 使用 HTTP 缓存标头：

    - `Cache-Control`, `ETag`, `Last-Modified`, `Expires`. 

- Cache static or rarely-changing data to reduce server load.
缓存静态或很少更改的数据以减少服务器负载。

- Allow clients, proxies, and CDNs to cache responses safely.
允许客户端、代理和 CDN 安全地缓存响应。

```http
Cache-Control: public, max-age=3600
ETag: "abc123"
```

### 3. Pagination and Partial Responses 3.分页和部分响应
- Avoid returning large datasets in one request.
避免在一个请求中返回大型数据集。

- Implement: 实现：

    - Pagination (`limit`, `offset` or cursor-based). 

    - Filtering (`?status=active`). 

    - Sorting (`?sort=name`). 

- Consider partial responses using `fields` or GraphQL if needed to return only required fields.
如果需要，请使用`fields`或GraphQl考虑部分响应.

**Benefit**: Reduces bandwidth and server computation per request.
优点：减少每个请求的带宽和服务器计算。

### 4. Use Compression 4.使用压缩
- Enable GZIP or Brotli compression for responses. 启用 GZIP 或 Brotli 压缩以获取响应。

- Dramatically reduces payload sizes, speeding up network transfer.
显著减少有效负载大小，加快网络传输速度。

Header: 

```h
Content-Encoding: gzip
```
### 5. Avoid Unnecessary Data (Reduce Payload Size)5. 避免不必要的数据（减少有效负载大小）
- Send only necessary data: Sendonly 必要的数据：

    - Use specific endpoints (e.g., `/users/123/profile` vs. `/users/123` if only profile data is needed).
使用特定端点（例如，`/users/123/profile`vs.`/users/123`如果只需要配置文件数据）。

    - Avoid heavy nested structures if not required.
如果不需要，请避免使用繁重的嵌套结构。

- Normalize payload design for minimal redundancy.
规范化有效负载设计以实现最小冗余。

### 6. Asynchronous Processing 6.异步处理
- For long-running operations, return a `202 Accepted` and process the request asynchronously, providing a status endpoint for the client to poll or using webhooks for notifications.
对于长时间运行的动作，返回一个`202 Accepted`并异步处理请求，为客户端提供状态端点以进行轮询或使用 Webhook 进行通知。

### 7. Rate Limiting and Throttling 7.速率限制和限制
- Implement rate limiting to prevent abuse and protect backend services.
实施限流以防止滥用并保护后端服务。

- Return relevant headers to inform clients of limits:
  返回相关标头以通知客户端限制：

    - `X-RateLimit-Limit`

    - `X-RateLimit-Remaining`

    - `X-RateLimit-Reset`

### 8. Connection Keep-Alive 8.连接保持活动状态
- Allow HTTP connections to be reused for multiple requests to reduce TCP overhead:
  允许将 HTTP 连接重新用于多个请求，以减少 TCP 开销：

    - Use `Connection: keep-alive`.

### 9. Use HTTP/2 or HTTP/3 9.使用 HTTP/2 或 HTTP/3
- These protocols: 这些协议：

    - Enable multiplexing multiple requests on a single connection.
    在单个连接上启用多路复用多个请求。

    - Reduce latency and head-of-line blocking. 减少延迟和队头阻塞。

    - Provide built-in header compression. 提供内置的标头压缩。

### 10. Efficient Database Access Patterns 10. 高效的数据库访问模式
- While not part of the API surface, remember:
虽然不是 API 表面的一部分，但请记住：

    - Use database indexes effectively for queries your API makes frequently.
使用数据库索引有效地进行 API 频繁进行的查询。

    - Avoid N+1 query problems (batch data fetching when necessary).
避免 N+1 查询问题（必要时批量获取数据）。

    - Use caching layers (Redis, Memcached) for frequently accessed data.
对经常访问的数据使用缓存层（Redis、Memcached）。

### 11. Use CDN for Static Content and Public APIs11. 将 CDN 用于静态内容和公共 API

- For public APIs with high traffic, serve cacheable resources through CDNs to reduce latency and server load.
对于高流量的公网 API，通过 CDN 提供可缓存资源，以减少延迟和服务器负载。

### 12. Monitor and Profile API Performance 12.监控和分析 API 性能

- Track: 跟踪：

    - Response times 响应时间

    - Error rates 错误率

    - Traffic patterns 流量模式

- Use profiling to identify slow endpoints, large payloads, or inefficient queries.
使用分析来识别慢速终端节点、大型负载或低效查询。

✅ Summary Table ✅ 汇总表

| Best Practice最佳实践               | Performance性能优势 Benefit                   |
| --------------------------- | ------------------------------------- |
| HTTP Methods & Status CodesHTTP方法和状态代码 | Enables cache, reduces ambiguity启用缓存，减少歧义      |
| Enable Caching启用缓存              | Reduce server load and response times减少服务器负载和响应时间 |
| Pagination & Filtering分页和过滤      | Reduce payload size减小有效负载大小                   |
| Compression压缩                 | Reduce payload size减小有效负载大小                   |
| Avoid Unnecessary Data避免不必要的数据      | Reduce bandwidth usage减少带宽使用                |
| Async Processing异步处理            | Prevent request timeouts防止请求超时              |
| Rate Limiting速率限制               | Prevent overload 防止过载                     |
| Keep-Alive保持活动状态                  | Reduce connection overhead减少连接开销            |
| HTTP/2/3HTTP/2/3 协议                    | Reduce latency减少延迟                        |
| DB Access Patterns数据库访问模式          | Reduce query times减少查询时间                    |
| CDN UsageCDN 使用情况                   | Faster content delivery更快的内容交付               |
| Monitoring监测                  | Continuous optimization持续优化               |


## ✅ Final Notes: ✅ 最后说明：

- RESTful API performance is achieved by careful endpoint design, leveraging HTTP capabilities, and using infrastructure best practices.
RESTful API 性能是通过仔细设计终端节点、利用 HTTP 功能和使用基础设施最佳实践来实现的。

- Small design choices, like enabling GZIP and using pagination, can drastically improve client-perceived performance and backend scalability.
较小的设计选择（例如启用 GZIP 和使用分页）可以显著提高客户端感知的性能和后端可扩展性。

# 11. Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to avoid them.
## 1. What is XSS (Cross-Site Scripting)?1. 什么是XSS(跨站点脚本)?

### Definition: 定义：

XSS is a security vulnerability that allows attackers to inject malicious scripts (usually JavaScript) into webpages viewed by other users.
XSS是一种安全漏洞，允许攻击者将恶意脚本（通常是 JavaScript）注入其他用户查看的网页中。

### How it works: 运作方式：

1. An attacker injects a malicious script into a webpage (e.g., via a comment field).
攻击者将恶意脚本注入网页（例如，通过评论字段）。

2. Another user visits the page and the malicious script executes in their browser.
另一个用户访问该页面，恶意脚本在其浏览器中执行。

3. The script can: 该脚本可以：

    - Steal cookies or session tokens. 窃取 cookie 或会话令牌。

    - Log keystrokes. 记录击键。

    - Redirect to malicious sites. 重定向到恶意网站。

    - Perform actions on behalf of the user.
代表用户执行作。

### Types of XSS: XSS 的类型：

- Stored XSS: Malicious script is permanently stored on the server (e.g., in a comment or profile).
存储的 XSS：恶意脚本永久存储在服务器上（例如，在评论或个人资料中）。

- Reflected XSS: Malicious script is reflected off the server in the response (e.g., via URL parameters).
反射型 XSS：恶意脚本在响应中被反射出服务器（例如，通过 URL 参数）。

- DOM-based XSS: Malicious script manipulates the DOM in the browser, without new server requests.
基于 DOM 的 XSS：恶意脚本在浏览器中操纵DOM，无需新的服务器请求。

### How to prevent XSS: 如何防止 XSS：
✅ Input validation and sanitization: ✅输入验证和清理：

Escape special characters (`<`, `>`, `"`, `'`, `&`) before displaying user input.
在显示用户输入之前转义特殊字符 （`<`, `>`, `"`, `'`, `&`）。

✅ Use frameworks that auto-escape (React, Angular, Django templates).
✅使用自动转义的框架（React、Angular、Django 模板）。

✅ Use Content Security Policy (CSP) headers to limit executable scripts.
✅ 使用Content Security Policy （CSP）标头来限制可执行脚本。

✅ Avoid `eval()` and direct `innerHTML` manipulations with untrusted data.
✅ 避免 `eval()`和直接`innerHTML`动作。

✅ Encode output based on context (HTML, JS, URL).
✅ 根据上下文（HTML、JS、URL）对输出进行编码。

## 2. What is CSRF (Cross-Site Request Forgery)?2. 什么是 CSRF（跨站点请求伪造）？

### Definition: 定义：

CSRF is a security vulnerability that forces a user to execute unwanted actions on a web application where they are authenticated.
CSRF是一种安全漏洞，它迫使用户在经过身份验证的 Web 应用程序上执行不需要的动作。

### How it works: 运作方式：

1. The victim is logged into trustedbank.com. 受害者被记录在 intotrustedbank.com。

2. The attacker tricks the victim into visiting malicious.com.
攻击者诱骗受害者进入 visitingmalicious.com。

3. malicious.com contains a hidden request: malicious.com 包含隐藏请求：

    ```html
    <img src="https://trustedbank.com/transfer?to=attacker&amount=1000">
    ```
4. Since the victim’s browser automatically sends cookies/session tokens, the request is executed with victim’s credentials.
由于受害者的浏览器会自动发送 cookie/会话令牌，因此将使用受害者的凭据执行请求。

### CSRF vs. XSS: 

|                      | **XSS**                                 | **CSRF**                                       |
| -------------------- | --------------------------------------- | ---------------------------------------------- |
| **What it exploits**它利用什么 | Trust of **user in the website**网站中的用户信任        | Trust of **website in the user's browser**用户浏览器中的网站信任度     |
| **Attack goal**攻击目标      | Run malicious scripts on user's browser在用户的浏览器上运行恶意脚本 | Perform unwanted actions on behalf of the user代表用户执行不想要的动作 |

How to prevent CSRF: 如何防止 CSRF：
✅ Use anti-CSRF tokens: ✅使用 anti-CSRF Token：

Generate a unique token per session/request, embed it in forms.
为每个会话/请求生成一个唯一的令牌，并将其嵌入到表单中。

Server verifies the token on each request.
Server 在每个请求上验证令牌。

✅ SameSite cookies: ✅SameSite Cookie：

Set cookies with SameSite=Strict or SameSite=Lax to block cross-site requests.
使用 SameSite=StrictorSameSite=Lax设置 cookie 以阻止跨站点请求。

✅ Require re-authentication for sensitive actions. ✅ 需要对敏感作进行重新身份验证。
✅ Use CORS headers carefully and disallow untrusted cross-origin requests.
✅ 谨慎使用 CORS 标头，并禁止不受信任的跨域请求。

| Aspect方面         | **XSS**                                                 | **CSRF**                                                    |
| -------------- | ------------------------------------------------------- | ----------------------------------------------------------- |
| **Definition**定义 | Inject malicious scripts into webpages viewed by others将恶意脚本注入他人查看的网页 | Perform unwanted actions using user’s authenticated session使用用户经过身份验证的会话执行不想要的动作 |
| **Exploits**利用   | User’s trust in the website用户对网站的信任                             | Website’s trust in the user/browser网站对用户/浏览器的信任                         |
| **Impact**影响     | Cookie theft, redirect, credential theftCookie 盗窃、重定向、凭据盗窃                | Unauthorized fund transfer, account changes未经授权的资金转账、账户更改                 |
| **Prevention**预防 | Input sanitization, output encoding, CSP输入清理、输出编码、CSP                | CSRF tokens, SameSite cookies, re-authenticationCSRF 令牌、SameSite Cookie、重新身份验证            |

✅ Practical Prevention Recap: ✅ 实用预防回顾：

✅ XSS: Validate & sanitize inputs, encode outputs, enable CSP.
✅XSS：验证和净化输入，编码输出，启用CSP。

✅ CSRF: Use CSRF tokens, SameSite cookies, validate request origins.
✅CSRF：使用 CSRF 令牌、SameSite Cookie、验证请求来源。

# 12. Use Postman or other API testing tools to:
## 1. Find at least 5 different public APIs (e.g., GitHub APIs, Google Cloud APIs, GeoInfo APIs, Weather APIs) and use them to explain what defines a REST API.  

## 2. Justify whether these APIs follow API design best practices, and provide your better design for them. 

A:

### 1️⃣ NVD Change History

**URL:**

```ruby
https://nvd.nist.gov/vuln/detail/{cve}#VulnChangeHistorySection
```

#### Analysis:

-   This is **not a REST API endpoint**; it is a **web URL with a fragment (`#`)**, intended for human navigation, not programmatic access.
    
-   **Fragment identifiers (`#`) are not sent to the server**; they are handled client-side by the browser to scroll to a section.
    
-   **Cannot be used programmatically to get JSON structured data.**
    

#### Better RESTful Design:

✅ Expose a **REST API endpoint** like:

```bash
GET https://api.nvd.nist.gov/v1/vulnerabilities/{cve}/change-history
```

-   **Returns structured JSON change history for the CVE**, enabling integration, analysis, and automation.
    
-   Enables **filtering, pagination, and caching** for large change logs.
    

---

### 2️⃣ TV Maze API

**URL:**

```bash
http://api.tvmaze.com/shows/{id}
http://api.tvmaze.com/shows
```

#### Analysis:

✅ Good RESTful design:

-   Uses **plural noun (`shows`)** for collections.
    
-   Uses **path variables (`{id}`) to access individual resources**.
    
-   Uses `GET` to retrieve data.
    

#### Improvement Opportunities:

-   For performance:
    
    -   Add **pagination parameters** to `GET /shows` to prevent returning the entire dataset, e.g.:
        
        ```bash
        GET /shows?page=2&limit=50
        ```
    
-   Enable **filtering/search via query parameters**, e.g.:
    
    ```sql
    GET /shows?genre=comedy&language=en
    ```
    

✅ Overall, this API aligns well with RESTful design.

---

### 3️⃣ Google Maps Autocomplete API

**URL:**

```ruby
https://maps.googleapis.com/maps/api/place/autocomplete/json?input=McDonald's&location=47.6654852,-122.3004415&radius=500&strictbounds=true&types=establishment&key=YOUR_API_KEY
```

#### Analysis:

✅ This API:

-   Uses `GET` appropriately for retrieval.
    
-   Uses **query parameters for filtering and configuration**, which is correct.
    
-   Uses JSON response (`/json` in path).
    

#### Improvement Opportunities:

-   API key is exposed in URL:
    
    -   Though common, using **API keys in headers (`Authorization: Bearer <key>`)** is safer to avoid logging sensitive keys in URLs.
    
-   Uses a nested structure (`/maps/api/place/autocomplete/json`):
    
    -   Could be simplified semantically:
        
        ```bash
        GET /api/places/autocomplete?input=...
        ```
        

✅ Overall, design aligns well with REST best practices while providing flexibility for filtering.

---

### 4️⃣ GitHub Commit API

**URL:**

```bash
https://api.github.com/repos/{repo_owner}/{repo_name}/commits/{commit_sha}
```

#### Analysis:

✅ Excellent REST API design:

-   Uses **clear hierarchical resource structure: repos → commits**.
    
-   Uses **path variables (`{repo_owner}`, `{repo_name}`, `{commit_sha}`) to identify specific resources**.
    
-   Uses `GET` for retrieval.
    

#### Improvement Opportunities:

-   None necessary; GitHub's API is widely considered **REST design best practice**.
    

---

### 5️⃣ XAMPP phpMyAdmin API

**URL:**

```bash
http://localhost/phpmyadmin/index.php?route=/sql&pos=0&db=first_db&table=users
```

#### Analysis:

❌ This is **not a REST API**:

-   It is a **web interface URL with query parameters used for navigation**.
    
-   Uses `index.php` with route query parameters, which **does not follow RESTful semantics**.
    
-   Exposes **database table structures directly**, which is insecure.
    

#### Better RESTful Design:

✅ Design an API such as:

```pgsql
GET http://localhost/api/databases/{db}/tables/{table}/records?limit=10&offset=0
```

or for CRUD:

```bash
POST   /api/databases/{db}/tables/{table}/records
GET    /api/databases/{db}/tables/{table}/records/{record_id}
PUT    /api/databases/{db}/tables/{table}/records/{record_id}
DELETE /api/databases/{db}/tables/{table}/records/{record_id}
```

✅ This:

-   Follows RESTful structure.
    
-   Uses HTTP methods appropriately.
    
-   Secures access via authentication tokens, avoiding direct DB exposure.
    

---

### ✅ **Summary Table**

| API | REST Best Practice | Comments | Suggested Improvement |
| --- | --- | --- | --- |
| **NVD** | ❌ | Web URL, not REST API | Expose structured `/vulnerabilities/{cve}/change-history` |
| **TV Maze** | ✅ | Good REST design | Add pagination/filtering |
| **Google Maps Autocomplete** | ✅ | Well-structured | Use headers for API keys, simplify path |
| **GitHub Commits** | ✅✅ | Best practice | None |
| **XAMPP phpMyAdmin** | ❌ | Web navigation URL | RESTful CRUD API for DB access |

---

### ✅ Conclusion:

**REST API best practices** involve:

-   Using **clear, hierarchical URIs with nouns**.
    
-   Using **HTTP methods appropriately**.
    
-   Providing **pagination and filtering**.
    
-   Returning **structured JSON data**.
    
-   Securing APIs without direct database exposure.

    
### Explaination of what defines a REST API:

A Rest API is defined by URL, Request method, Status code, Request Header, Request Body, Response Header, Response Body. All the 5 APIs have method of "GET" and status code `200 OK`.



​    


​    
## 3. List the above APIs in form of cURL commands , and attach Postman screenshots in your markdown submission.  
1) curl --location 'https://nvd.nist.gov/vuln/detail/CVE-2015-5963#VulnChangeHistorySection'
![NVD](./img/NVD.png)

2) curl --location 'http://api.tvmaze.com/shows/1'
![TVMAZE](./img/TVMAZEShow.png)
   getAll:
   ![TVMAZE](./img/TVMAZEShows.png)

3) curl --location 'https://maps.googleapis.com/maps/api/place/autocomplete/json?input=McDonald%27s&location=47.6654852%2C-122.3004415&radius=500&strictbounds=true&types=establishment&key=AIzaSyAShBm0yahHepOUFsAZzg_PDtTrwgTqWa4'
![GGMap](./img/GGMapAutoComplete.png)

4) curl --location 'https://api.github.com/repos/ADOdb/ADOdb/commits/bd9eca9f40220f9918ec3cc7ae9ef422b3e448b8'
![GitHubCommit](./img/GitHubCommit.png)

5) curl --location 'http://localhost/phpmyadmin/index.php?route=%2Fsql&pos=0&db=first_db&table=users'
![XAMPPDBAdmin](./img/XAMPPDBAdmin.png)

## 4. List the request headers and response headers of the APIs mentioned above, and explain what each key-value pair in the headers section does

**1. Request Header:** 

|Header Key | Header Value | What it does |
|------|------|------|
|Cookie| phpMyAdmin=lkcit7j8iichmrc53fagi1f5j1; pma_lang=en | Includes cookies that the client previously received from the server, used to maintain session state and personalize user experience.包括客户端之前从服务器接收的 Cookie，用于维护会话状态和个性化用户体验。|
|Postman-Token| \<calculated when request is sent\>|The Postman-Token header appends a random UUID to every outgoing request. Postman adds this header for API developers to better debug requests sent and to ensure separate requests appear distinct to the receiving server.|
|Host| \<calculated when request is sent\>| The Host header is added to identify the domain name for which a request is being sent to the server. This header is implicitly sent by every HTTP client. It is most likely that without this header, your request will return an HTTP 404 error. |
|User-Agent|PostmanRuntime/7.43.2|Help the server identify Postman as the HTTP requesting application or client |
|Accept|\*/\*| Indicates the media types (e.g., text/html, application/json) that the client is willing to accept from the server.指示客户端愿意从服务器接受的媒体类型（例如，text/html、application/json）。In this case it tells the server that Postman can understand and process all forms of response content types.这里它告诉服务器Postman可以理解和处理所有形式的响应内容类型。 | 
|Accept-Encoding| gzip, deflate, br| Specifies the encoding algorithms (like gzip, deflate) that the client understands.指定客户端理解的编码算法（如 gzip、deflate）。In this case it indicates to the server that Postman HTTP client supports a defined list of content-encoding or compression algorithms as response.这里它向服务器注明Postman HTTP客户端支持定义的内容编码或压缩算法的列表作为响应。|
|Connection| "keep-alive"|Postman added the Connection header to indicate the server to keep the underlying network connection open once the current response is received. This allows Postman to reuse the sameconnection for faster response times in subsequent requests to the same server.You can remove this header or enter a new one with a different value, such as `Connection: Close` to control this behaviour.|


**2. Other Common Request Headers:**

|Header Key| What it does|
|------|------|
|Content-Type|(POST/PUT/PATCH) Specifies the media type (e.g., application/json) of the data being sent in the request body, crucial for the server to correctly interpret the data.指定请求正文中发送的数据的媒体类型（例如 application/json），这对于服务器正确解释数据至关重要。|
|Content-Length|Indicates the size (in bytes) of the message body, allowing the recipient to determine when the complete message has been received.指示消息正文的大小（以字节为单位），允许收件人确定何时收到完整的消息。
|Accept-Language|Tells the server about the preferred human language(s) of the user agent.告知服务器用户代理的首选人类语言。|
|Authorization|Used to carry credentials (like a token or username/password) to authenticate the client with the server, often for accessing protected resources.用于携带凭证（如令牌或用户名/密码）以向服务器验证客户端，通常用于访问受保护的资源。|
|Cache-Control|Directs caching mechanisms, specifying policies like maximum cache age, cache location, and whether revalidation is needed.指导缓存机制，指定最大缓存期限、缓存位置以及是否需要重新验证等策略。|



**3. Response Header:**

|Header Key|Header Value| What it does|
|------|------|------|
|Date|Mon, 30 Jun 2025 01:52:27 GMT|-|
|Server|Apache/2.4.58 (Win64) OpenSSL/3.1.3 PHP/8.2.12|Contains information about how the server handles requests|
|X-Powered-By|PHP/8.2.12|Specifies the technology (ASP .NET, PHP, JBoss, e.g.) supporting the web application. |
|Set-Cookie|phpMyAdmin=4mofbdl6u4j5hs7lp31590t9iv; path=/phpmyadmin/; HttpOnly; SameSite=Strict|Sends Cookies from the server to the user agent|
|Expires|Mon, 30 Jun 2025 01:52:42 +0000|Specifies a fixed date/time for the expiration of a cached resource.指定缓存资源的过期的固定日期/时间。|
|Cache-Control|no-store, no-cache, must-revalidate, pre-check=0, post-check=0, max-age=0|Directs caching mechanisms, indicating how the response should be cached (e.g., max-age, no-cache, no-store).指示缓存机制，指示应如何缓存响应（例如，max-age、no-cache、no-store）。|
|Last-Modified|Mon, 30 Jun 2025 01:52:42 +0000|Date and time a resource was last modified|
|X-Frame-Options|DENY| Used to avoid clickjacking attacks by making sure their content is not embedded into other sites.通过确保其内容未被嵌入其他网站来避免点击攻击|
|X-XSS-Protection|1; mode=block|Stops pages from loading when they detect reflected cross-site scripting (XSS) attack|
|X-Content-Type-Options|nosniff|Can be used to opt out of MIME type sniffing a response away from the declared content-type|
|X-Permitted-Cross-Domain-Policies|none|-|
|X-Robots-Tag|noindex, nofollow|-|
|Pragma|no-cache|-|
|Vary|Accept-Encoding|Determines how to match future request headers to decide whether the cached response can be used rather than requesting a fresh one.确定如何匹配未来的请求标头，以决定是否可以使用缓存的响应，而不是请求新的响应。 |
|Keep-Alive|timeout=5, max=100|Custom header|
|Connection|Keep-Alive|A general header specifying whether the current network connection will stay open once the current transaction finishes|
|Transfer-Encoding|chunked|Specify the form of encoding used to transfer the entity to the user|
|Content-Type|text/html; charset=utf-8| Indicates the media type of the resource being sent, such as text/html, application/json, image/jpeg, or audio/mp3.指示正在发送的资源的媒体类型，例如 text/html、application/json、image/jpeg 或 audio/mp3。|
|Content-Encoding|gzip| Indicates the encoding of the response body, such as gzip or deflate.表示响应体的编码，例如 gzip 或 deflate。|

**4. Other Common Response Headers:**

|Header Key| What it does|
|------|------|
|Content-Length|Specifies the size of the response body in bytes.指定响应正文的大小（以字节为单位）。|
|ETag|Provides a unique identifier for the resource, used for conditional requests.提供资源的唯一标识符，用于条件请求。|
|Location|Redirects the client to a different URL.将客户端重定向到其他 URL。|
|WWW-Authenticate|Challenges the client for authentication.质询客户端进行身份验证。|
|Access-Control-Allow-Origin|Used in Cross-Origin Resource Sharing (CORS) to specify which origins are allowed to access the resource.用于跨域资源共享 （CORS） 中，用于指定允许哪些源访问资源。|



