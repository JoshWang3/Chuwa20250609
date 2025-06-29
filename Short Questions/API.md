1. https://api.github.com/users/octocat
Resource-based URI (/users/{username}), proper GET method
Correct status codes (200, 404, etc.)
Supports pagination with since and Link headers
Conclusion: good design

Curl command: 
curl -X GET \
-H "Accept: application/vnd.github+json" \
https://api.github.com/users/octocat

Request header
Accept	application/vnd.github+json	Tells GitHub that the client expects a JSON response in GitHub’s custom media type.
User-Agent	PostmanRuntime/7.x.x	Required by GitHub for all API requests; identifies the client making the request.
Host	api.github.com	Specifies the domain to which the request is being sent.
Connection	keep-alive	Requests that the server keeps the connection open for further requests (performance).
Accept-Encoding	gzip, deflate, br	Informs the server that the client supports compressed responses (reduces payload size).

Response header
Server	GitHub.com	Identifies the server software handling the request.
Date	Tue, 27 Jun 2025 10:00:00 GMT	Timestamp when the response was generated.
Content-Type	application/json; charset=utf-8	Specifies the media type and encoding of the response body.
Content-Length	1234	The size (in bytes) of the response payload.
X-RateLimit-Limit	60	Total number of API requests allowed per hour for unauthenticated users.
X-RateLimit-Remaining	59	Number of requests remaining in the current rate limit window.
X-RateLimit-Reset	1724674800	UNIX timestamp when the current rate limit resets.
ETag	"abc123etagvalue"	Identifier for caching; client can send it back to avoid re-downloading unchanged data.
Cache-Control	public, max-age=60, s-maxage=60	Specifies how the response can be cached by browsers and proxies.
Vary	Accept, Authorization	Indicates response may change based on Accept or Authorization headers.
X-GitHub-Media-Type	github.v3	Indicates the version of the GitHub API used.


2. http://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY
curl -X GET \
"http://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY"

Request header
Accept: application/json → Expect JSON response
appid=<API_KEY> as query param for authentication

Response header
Content-Type: application/json; charset=utf-8
Cache-Control: public, max-age=10 → Indicates freshness and caching rules
Date: timestamp

Resource-based; uses GET, clear parameters
However,
Auth via query param isn’t ideal—better via Authorization header
Caching window (10 mins) is short; recommending standardized caching headers instead of custom logic

3. https://restcountries.com/v3.1/name/canada
curl -X GET \
   -H "Accept: application/json" \
   https://restcountries.com/v3.1/name/canada

Request Headers:
Accept: application/json

Response Headers:
Content-Type: application/json; charset=utf-8
Access-Control-Allow-Origin: * → Enables CORS
Date, Server: metadata

Design Evaluation & Improvements:
-Clean RESTful URI (/v3.1/name/{country}), proper plural resource names
-Good filtering capability (name, fullText, fields)
-Supports pagination for large lists via selective fields
Improvement: Could offer Cache-Control for better client-side caching.

4. https://api.spacexdata.com/v3/launches?limit=1&offset=5

curl -X GET \
-H "Accept: application/json" \
https://api.spacexdata.com/v3/launches?limit=1&offset=5

Request Headers:
Accept: application/json

Response Headers:
Content-Type: application/json
Date, ETag: useful for client cache validation

Design:
Lacks API versioning in path (uses v3 subdomain, but /v4/ path would be more explicit)

5. https://jsonplaceholder.typicode.com/posts/1/comments
curl -X GET \
   -H "Accept: application/json" \
   https://jsonplaceholder.typicode.com/posts/1/comments

Request Headers:
Accept: application/json

Response Headers:
Content-Type: application/json; charset=utf-8
X-Powered-By: Express
Date, Connection: server/runtime info
Excellent RESTful path structure with plural resources and nesting (posts/1/comments)


✅ Supports all HTTP methods (GET, POST, etc.)
✅ Enables filtering via query (comments?postId=1)
Improvement: Artificial error simulation could enhance testing (e.g., non-200 status codes for invalid payloads).