1. An API (which is abbreviation for Application Programming Interface) is a set of rules, protocols, and tools that allow different software applications to communicate with each other. It defines how requests and responses should be formatted so systems can exchange data and functionality in a secure way.

3. Application APIs are APIs that enable communication between software components—used internally or externally.
   Examples: RESTful API between frontend and backend; Android API for mobile development.

Developer APIs are Public-facing APIs designed for third-party developers to build apps, tools, or integrations.
Examples: Twitter API for posting tweets; Google Maps API for embedding maps.

3. GraphQL APIs， SOAP APIs， REST APIs， Open APIs

4. Request parameter goes after ? in the URL, purpose is to Filter, sort, or modify the request, path variable is part of the url path and Identify a specific resource

5. Endpoint-Locates the resource
   HTTP Method-Specifies the action
   Headers-Passes metadata
   Request Body-Sends data to server
   Query Params-Filters/modifies request
   Response Body-Returns data to client
   Status Codes-Communicates result status

6. cURL (Client URL) is a command-line tool used to send HTTP requests to a server.
   It supports multiple protocols (HTTP, HTTPS, FTP, etc.) and is commonly used to test and interact with APIs.
cURL is powerful and scriptable, great for quick or automated command-line tasks.
API testing tools like Postman is better for manual testing, organizing API calls, and debugging because of its visual interface

7. 200	OK – Request succeeded
   400	Bad Request – Malformed syntax or invalid request
   401	Unauthorized – Missing or invalid authentication
   403	Forbidden – Authentication valid, but access denied
   404	Not Found – Resource not found on the server
   405	Method Not Allowed – HTTP method not supported for this endpoint

8. 
1) GET
Expected Status Codes:
200 OK – Success, resource returned
304 Not Modified – Cached version still valid
404 Not Found – Resource doesn’t exist

2)POST
Expected Status Codes:
201 Created – Resource successfully created
400 Bad Request – Invalid input data
409 Conflict – Duplicate resource

3) PUT
Expected Status Codes:
200 OK – Resource updated
204 No Content – Update successful, no body returned
400 Bad Request – Malformed request
404 Not Found – Resource doesn’t exist

4) PATCH
Expected Status Codes:
200 OK – Resource partially updated
204 No Content – Update successful, no body returned
400 Bad Request – Invalid patch data
404 Not Found – Resource not found

5) DELETE
Expected Status Codes:
200 OK – Resource deleted, may return data
204 No Content – Successfully deleted, no response body
404 Not Found – Resource doesn’t exist

9. A REST API is stateless because each request from the client to the server must contain all the information needed to understand and process the request—independent of any previous requests.

10. 1) 
1) Use Proper HTTP Methods
Stick to REST conventions: GET (read), POST (create), PUT (update), DELETE (remove).
Avoid using POST for all actions—it breaks caching and tooling optimizations.

2) Enable Caching
Use headers like Cache-Control, ETag, and Last-Modified to allow client-side and intermediary caching.
Especially important for GET requests with static or infrequently changing data.

3) Support Pagination, Filtering, and Sorting
Prevent large payloads by allowing pagination: ?page=1&limit=50
Add filters and sorting: ?status=active&sort=price_desc
Reduces server load and improves response time.

11. XSS occurs when untrusted user input is included in a webpage without proper validation or escaping, allowing attackers to inject malicious scripts into the browser of another user.
We can avoid it by using Use Content Security Policy (CSP) and avoiding inline JavaScript

CSRF tricks a logged-in user’s browser into sending unauthorized actions (like changing a password or transferring funds) to a trusted site, using that user’s credentials.
We can avoid it by using SameSite cookies and Require re-authentication for critical changes