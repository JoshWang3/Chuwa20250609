<p> 1. Explain the concept of API (Application Programming Interface), why do we need APIs.</p>
<p> An API is a set of rules and protocols that allows different software applications to communicate with each other. 
It defines how requests and responses should be structured and what operations are available. </p>
<p> APIs allow the frontend and backend of an application to evolve independently; APIs enable reusability. 
Once an API is built, multiple clients can consume it; APIs allow systems to integrate; With APIs, 
organizations can scale services, expose only needed functionality, and keep sensitive logic hidden behind well-defined 
interfaces. </p>

<p> 2. Compare developer API vs application API (normal APIs)</p>
Developer API specifically designed to be used by software developers when building new applications or 
integrating with existing ones. Application API enables communication and interaction between different software 
applications, systems, or components. 

<p> 3. Name some different types of APIs</p>
<p> Public APIs: Accessible to anyone, typically for broad use and integration. </p> 
<p> Partner APIs: Available to specific, authorized external partners for collaboration. </p>
<p> Internal APIs: Used within an organization for internal communication and integration of systems. </p>
<p> Composite APIs: Combine multiple APIs to provide a unified and more complex functionality. </p>

<p> 4. Compare path variables vs request parameters in REST API.</p>
Path variables are part of the URL path. They typically represent specific resources, like passing a specific id value. 
Request parameters are part of the query string after the ? in a URL. They are used to filter, sort, or control a response.

<p> 5. Explain the different components that make up a RESTful API and what does each part do?</p>
<p> Http methods: Perform actions on resources (Get, Put, Patch, Post, Delete) </p>
<p> URL (Path variables + Request parameter) </p>
<p> Http Headers: Provide metadata for the request, such as content type, authorization, and caching info.</p>
<p> Request Body: for actions (Put, Patch, Post) Json body needs to be passed. </p>
<p> Response Body: data returned back from the server. </p>
<p> Response status code: indicates the result of the request.</p>
<p> Response headers </p>

<p> 6. Explain what cURL is and why we use API testing tools like Postman instead of testing APIs directly with
   cURL.</p>
cURL (short for Client URL) is a command-line tool used to send HTTP requests to a server. 
It supports multiple protocols like HTTP, HTTPS, FTP, etc. Postman provides a graphical UI. It's easier to use for testing
API. 

<p> 7. List common HTTP status codes and their meanings.</p>
<p> 1xx: informational responses </p>
<p> 2xx: success status </p>
<p> 3xx: redirects </p>
<p> 4xx: client side errors </p>
<p> 5xx: server side errors </p>

<p> 8. List HTTP methods and their meanings, and their expected HTTP status codes.</p>
<p> Get: retrieve data. status code: 200(OK); 404(Not found, if ID not found or invalid) </p>
<p> Post: create data. status code: 404(Not found); 409(if resource already existed) </p>
<p> Put: full resource update, if some fields are missing, those fields will be updated with value null. status code: 200(OK); 204(No Content); 404(Not found, if ID not found or invalid) </p>
<p> Patch: partial update/modify, all other fields remain unchanged if those fields are not sent. status code: 200(OK); 204(No Content); 404(Not found, if ID not found or invalid) </p>
<p> Delete: delete data. status code: 200(OK); 404(Not found, if ID not found) </p>

<p> 9. Explain why REST API is stateless.</p>
Statelessness means that servers do not save client data between requests. Client requests to the server are similar 
to URLs you type in your browser to visit a website. The response from the server is plain data, without the 
typical graphical rendering of a web page.

<p> 10. Discuss best practices for REST API design, from performance perspective.</p>
<p> Use JSON as the format for sending or receiving data </p>
<p> Use nouns in endpoints </p>
<p> Implement Pagination for Large Datasets </p>
<p> Use correct status codes </p>
<p> Use versioning (/v1/) to prevent breaking changes from impacting existing clients </p>

<p> 11. Explain the concept of XSS (Cross-Site Scripting) and CSRF (Cross Site Request Forgery) and how to
    avoid them.</p>
<p> XSS is a type of security vulnerability that allows attackers to inject malicious scripts into web pages viewed by other users. 
Escape or encode user input before rendering it in the UI. Use security libraries or built-in frameworks that auto-escape HTML </p>
CSRF tricks a logged-in user’s browser into sending unauthorized requests to a web app on their behalf.
Use CSRF tokens: Generate a unique token for each session/request and verify it on the server. 
Require re-authentication for critical actions

<p> API Practices:</p>
<p> Use Postman or other API testing tools to:</p>
<p> 1. Find at least 5 different public APIs (e.g., GitHub APIs, Google Cloud APIs, GeoInfo APIs, Weather APIs)
   and use them to explain what defines a REST API. These APIs can use any HTTP methods and may also
   include non-REST APIs (e.g., GraphQL). Some public APIs may require API keys (user registration
   required); </p>
<p> a. https://dog.ceo/api/breeds/list/all </p>

<p> 2. Justify whether these APIs follow API design best practices, and provide your better design for them.</p>
For API a, it fetches all data. The best practice is passing the page number and page size in the url. 
https://dog.ceo/api/breeds?pageSize=25&pageNumber=1

3. List the above APIs in form of cURL commands, and attach Postman screenshots in your markdown
   submission.

4. List the request headers and response headers of the APIs mentioned above, and explain what each
   key-value pair in the headers section does.
