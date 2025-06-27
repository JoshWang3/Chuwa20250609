# API Practices Analysis

## Overview

I tested 6 different public APIs to understand REST design patterns, analyze HTTP headers, and evaluate API best practices. Here's what I found.

## APIs Tested

### 1. GitHub API
**What it does**: Get user information  
**URL**: `https://api.github.com/users/octocat`  
**Authentication**: Optional (rate limits apply without token)

```bash
curl -H "Accept: application/json" -H "User-Agent: APIDemo/1.0" https://api.github.com/users/octocat
```

**Response**: Returns user profile data in JSON format. Status 200 with comprehensive user info.

### 2. JSONPlaceholder API  
**What it does**: Mock REST API for testing CRUD operations  
**URL**: `https://jsonplaceholder.typicode.com/posts`  
**Authentication**: None needed

```bash
curl -X POST -H "Content-Type: application/json" -d '{"title":"Test Post","body":"Test content","userId":1}' https://jsonplaceholder.typicode.com/posts
```

**Response**: Creates a new post and returns it with status 201. Perfect for learning REST patterns.

### 3. Dog CEO API
**What it does**: Random dog images and breed information  
**URL**: `https://dog.ceo/api/breeds/image/random`  
**Authentication**: None

```bash
curl https://dog.ceo/api/breeds/image/random
```

**Response**: Simple JSON with random dog image URL. Fast and reliable.

### 4. REST Countries API
**What it does**: Country information with flexible filtering  
**URL**: `https://restcountries.com/v3.1/name/japan?fields=name,capital,population`  
**Authentication**: None

```bash
curl -H "Accept: application/json" "https://restcountries.com/v3.1/name/japan?fields=name,capital,population"
```

**Response**: Filtered country data. Demonstrates good query parameter usage.

### 5. Cat Facts API
**What it does**: Random cat facts  
**URL**: `https://catfact.ninja/fact`  
**Authentication**: None

```bash
curl -H "Accept: application/json" https://catfact.ninja/fact
```

**Response**: JSON with a random cat fact. Simple but well-structured.

### 6. OpenWeatherMap API
**What it does**: Weather data  
**URL**: `https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY`  
**Authentication**: API Key required

```bash
curl -H "Accept: application/json" "https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY"
```

**Note**: Requires registration for API key.

## Headers Analysis

### Request Headers I Used

- **Accept**: `application/json` - Tells the server I want JSON responses
- **Content-Type**: `application/json` - For POST requests, indicates I'm sending JSON data  
- **User-Agent**: `APIDemo/1.0` - GitHub requires this to identify the client
- **Authorization**: `Bearer <token>` - Would be used for authenticated APIs

### Response Headers I Got

- **Content-Type**: `application/json; charset=utf-8` - Server confirms it's sending JSON
- **Cache-Control**: `max-age=3600` - Tells clients how long to cache the response
- **X-RateLimit-Remaining**: `59` - Shows how many API calls I have left
- **Access-Control-Allow-Origin**: `*` - CORS header allowing browser requests
- **ETag**: `"abc123..."` - Used for caching and conditional requests

## What Makes a Good REST API

### Excellent Examples

**GitHub API** does everything right:
- Clear resource URLs like `/users/{username}` 
- Proper HTTP status codes (200, 404, 201)
- Good error messages with helpful details
- Rate limiting info in headers
- Supports different response formats

**REST Countries API** has great features:
- Field selection to minimize response size
- Multiple search options (by name, code, region)
- Good caching headers

### Good but Simple

**Dog CEO and Cat Facts APIs** keep it simple:
- No unnecessary authentication barriers
- Fast responses
- Clear JSON structure
- Easy to understand and use

### Areas for Improvement

**JSONPlaceholder API** is great for learning but missing:
- Real authentication mechanisms
- Proper validation error responses  
- Pagination for large datasets

**OpenWeatherMap API** has security issues:
- API key in URL parameters instead of headers
- Could use OAuth instead of simple API keys

## Better Design Recommendations

### Security
Instead of putting API keys in URLs (like OpenWeatherMap does), better APIs should:
- Use Authorization headers: `Authorization: Bearer <token>`
- Implement OAuth 2.0 for user authentication
- Always use HTTPS

### Performance  
Good APIs should include:
- Caching headers like ETag and Last-Modified
- Pagination for large datasets (`?page=1&limit=50`)
- Data compression support
- Field selection options (like REST Countries does)

### Developer Experience
The best APIs provide:
- Clear, consistent error messages
- Rate limiting information in headers
- Good documentation with examples
- Sandbox environments for testing

## Key Takeaways

After testing these APIs, here's what I learned:

1. **Consistency matters** - APIs should behave predictably across all endpoints
2. **Headers are important** - They carry crucial metadata about caching, rate limits, and content types
3. **Security should be built-in** - Don't put secrets in URLs
4. **Simple can be better** - Sometimes a straightforward API (like Dog CEO) is more useful than a complex one
5. **Good error handling helps everyone** - Clear error messages save developers time

The GitHub and REST Countries APIs represent the gold standard of REST design, while the simpler APIs show that you don't always need complexity to be useful. The main thing is to be consistent and think about the developer experience.

## Running the Code

To test these APIs yourself:

```bash
javac SimpleAPIDemo.java
java SimpleAPIDemo
```

The program will call all these APIs and show you the actual request/response headers in action. 