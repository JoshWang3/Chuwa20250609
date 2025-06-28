
## API Practices
### Question 1
REST API is defined by six architectural constraints:
- Client-Server Architecture: client and server are separate, independent
- Stateless: the server stores no client context between requests
- Cacheable: e.g. Response Header, Cache-Control: public, max-age=60, s-maxage=60
- Uniform Interface:
- - Resources are identified by URIs, and each resource has a unique identifier. e.g. https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy points to unique album. 
- - Representation of a resource gives enough information to modify or delete it. e.g. 
```
GET https://jsonplaceholder.typicode.com/posts/1
Response: {
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere...",
  "body": "quia et suscipit..."
}
PUT https://jsonplaceholder.typicode.com/posts/1
{
  "userId": 1,
  "id": 1,
  "title": "Updated title",
  "body": "Updated body"
}
```
- - Each message includes enough information to describe how to process it.
- - Responses should include links to related resources and possible actions. e.g.
```
GET https://api.github.com/repos/octocat/Hello-World
Response:
{
  "name": "Hello-World",
  "issues_url": "https://api.github.com/repos/octocat/Hello-World/issues{/number}",
}
```
- Layered System: The architecture can be composed of hierarchical layers, with each layer only knowing about the layer it's communicating with.

### Question 2
- Coinbase API: Follows API design best practices, except sometimes miss for links to related resources in response. 
- arXiv: Violates API design best practices, e.g.
```
http://export.arxiv.org/api/query?search_query=all:electron&start=0&max_results=10
```
which is query based instead of resource based, better design would be
```
http://export.arxiv.org/api/papers?category=physics&limit=10&offset=0
```
- Metropolitan Museum of Art API: Follows API design best practices
- Reddit API: Generally follows API design best practices, except issues like inconsistent URL patterns, e.g. /r/subreddit/action and /api/action, and action-based API e.g. /api/vote, which should be resource-based like /posts/{id}/votes. 
- World Bank: Nested resource, e.g. 
```
GET /v2/sources/57/country/ALB/series/SP.POP.TOTL/time/all/version/199704/data
```
should be 
```
GET /v2/data?source=57&country=ALB&series=SP.POP.TOTL
```
### Question 3
```
curl -X GET "https://api.coinbase.com/v2/currencies"
```
```
GET /v2/currencies HTTP/1.1 
Host: api.coinbase.com     # server domain name
User-Agent: curl/7.68.0    # client software
Accept: */*                # content types the client can process
```
```
HTTP/1.1 200 OK            
Content-Type: application/json; charset=utf-8
                           # response format and character encoding
Content-Length: 12543      # size of response body
Date: Wed, 27 Jun 2025 15:30:00 GMT
                           # response generation time
Server: nginx/1.18.0       # web server software
Cache-Control: public, max-age=3600
                           # caching instructions
ETag: "a1b2c3d4e5f6"       # resource version uid
X-RateLimit-Limit: 10000  
X-RateLimit-Remaining: 9995
X-RateLimit-Reset: 1719504000
                           # API rate limiting status
Access-Control-Allow-Origin: *
                           # CORS header
Strict-Transport-Security: max-age=31536000; includeSubDomains         
                           # Forces HTTPS
```
demo_1.jpg



```
curl -X GET "http://export.arxiv.org/api/query?search_query=cat:cs.AI&start=0&max_results=10"
```
```
GET /api/query?search_query=cat:cs.AI&start=0&max_results=10 HTTP/1.1
Host: export.arxiv.org
User-Agent: curl/7.68.0
Accept: */*
```
```
HTTP/1.1 200 OK
Content-Type: application/atom+xml; charset=utf-8
Content-Length: 15670
Date: Wed, 27 Jun 2025 15:30:00 GMT
Server: Apache/2.4.41
Last-Modified: Wed, 27 Jun 2025 15:25:00 GMT
                          # data update time
Expires: Wed, 27 Jun 2025 16:30:00 GMT
                          # response expiration time
Cache-Control: max-age=3600
Connection: keep-alive    # keep TCP connection alive
```
demo_2.jpg

```
curl -X GET "https://collectionapi.metmuseum.org/public/collection/v1/objects/45434"
```

```
GET /public/collection/v1/objects/45434 HTTP/1.1
Host: collectionapi.metmuseum.org
User-Agent: curl/7.68.0
Accept: */*
```
```
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 2847
Date: Wed, 27 Jun 2025 15:30:00 GMT
Server: cloudflare
Cache-Control: public, max-age=86400
ETag: "artwork-45434-v1"
X-RateLimit-Limit: 80
X-RateLimit-Window: 1
Access-Control-Allow-Origin: *
Access-Control-Allow-Methods: GET, OPTIONS
Access-Control-Allow-Headers: Content-Type
CF-Cache-Status: HIT     # Cloudflare cache hit
CF-Ray: 7d4c2a1b9c8e7f6g-LAX
                         # Cloudflare request tracking ID
```
demo_3.jpg
```
curl -X GET "https://www.reddit.com/r/technology/new.json"
```
```
GET /r/technology/new.json HTTP/1.1
Host: www.reddit.com
User-Agent: curl/7.68.0
Accept: */*
```
```
HTTP/1.1 200 OK
Content-Type: application/json; charset=UTF-8
Content-Length: 156789
Date: Wed, 27 Jun 2025 15:30:00 GMT
Server: snooserv
Cache-Control: max-age=600
Set-Cookie: session_tracker=abcd1234; Domain=.reddit.com; Path=/                         # sets session cookie
X-Moose: majestic              # custom header
X-Served-By: cache-reddit-01   # cache server
X-UA-Compatible: IE=edge       # Internet Explorer compatibility
Access-Control-Allow-Origin: *
Vary: Accept-Encoding          # response varies based on Accept-Encoding header
```
demo_4.jpg
```
curl -X GET "https://api.worldbank.org/v2/incomeLevel?format=json"
```
```
GET /v2/incomeLevel?format=json HTTP/1.1
Host: api.worldbank.org
User-Agent: curl/7.68.0
Accept: */*
```
```
HTTP/1.1 200 OK
Content-Type: application/json; charset=utf-8
Content-Length: 891
Date: Wed, 27 Jun 2025 15:30:00 GMT
Server: Microsoft-IIS/10.0
Cache-Control: no-cache, must-revalidate
Pragma: no-cache               # legacy cache control
Expires: -1                    # prevent caching 
X-Powered-By: ASP.NET          # framework
Access-Control-Allow-Origin: *
Access-Control-Allow-Headers: origin, x-requested-with, accept, content-type
Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS
                               # comprehensive CORS headers for browser access
```
demo_5.jpg

## Question 1
API (Application Programming Interface): set of protocols that allows software applications to communicate with each other.
Enables systems to work together without needing to understand each other's internal workings.

## Question 2
Developer API: 
- for developers
- response often processed as raw data

Application API:
- for end users
- response often visualized

## Question 3
REST APIs:
- follows REST principles
- stateless
- uses HTTP methods

GraphQL:
- query language that lets clients request specific data
- single endpoint

WebSocket:
- real-time, bidirectional communication
- live data

gRPC:
- call server methods as local method
- bidirectional streaming
- binary serialization format

## Question 4
Path variables:
- part of URL
- identify specific resources
- required

Request parameters
- after '?'
- for data filtering/sorting, etc
- optional

## Question 5
Endpoint: web address of the resource
HTTP Methods: actions to perform on the resource
Headers: meta data. Data format, Authorization, Caching, etc.
Request Body: data (client -> server), sometimes include Authentication
Request Body: data (client <- server), requested resource/confirmation of action, include status code
Parameters, as explained in Question 4

## Question 6
cURL: command-line tool for transferring data to/from servers
Why use postman: 
- graphical
- easy duplication and modification of requests
- automated testing
- easy to draft header

## Question 7
2xx Success:
- 200 OK, successful, data returned 
- 204 No Content, request successful, no data to return

3xx Redirection:
- 301 Moved Permanently, resource permanently moved to new URL

4xx Client Error:
- 400 Bad Request, invalid request syntax or parameters
- 401 Unauthorized, authentication required or failed
- 403 Forbidden, valid credentials but insufficient permissions
- 404 Not Found, resource doesn't exist

5xx Server Error:
- 500 Internal Server Error, generic
- 501 Not Implemented, doesn't support the requested functionality
- 502 Bad Gateway, invalid response from upstream server

## Question 8

GET - Retrieve Data:
- Success: 200 (OK), 304 (Not Modified)
- Error: 404 (Not Found), 403 (Forbidden)


POST - Create New Resource:
- Success: 201 (Created), 200 (OK), 202 (Accepted)
- Error: 400 (Bad Request), 409 (Conflict)


PUT - Update/Replace Resource:
- Success: 201 (Created), 200 (OK), 202 (Accepted)
- Error: 400 (Bad Request), 404 (Not Found)


DELETE - Remove Resource:
- Success: 200 (OK), 204 (No Content), 202 (Accepted)
- Error: 404 (Not Found), 403 (Forbidden)

## Question 9 
Reliability: no risk of losing session data. 
Simplicity: no need to manage client sessions, synchronize state across multiple servers
Caching: doesn't depend on server-side state

## Question 10
Best practices:
- Use HTTP caching
- Pagination
- Optimize payload size
- Rate limiting

## Question 11
XSS: Attacker injects malicious scripts into web pages that execute in other users' browsers
- Validate all user input
- Use Content Security Policy headers

CSRF: Malicious site sends requests to legitimate site using user's existing session
- Require re-authentication for sensitive actions
- Implement SameSite cookie attributes
- Use CSRF tokens in forms