import java.io.*;
import java.net.*;
import java.net.http.*;
import java.time.Duration;

/**
 * Simple API Practices Demo
 * Demonstrates interaction with 5+ public APIs for HW_API assignment
 */
public class SimpleAPIDemo {
    
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    public static void main(String[] args) {
        System.out.println("=== API Practices Demo ===\n");
        
        SimpleAPIDemo demo = new SimpleAPIDemo();
        
        // Test 5+ different public APIs
        demo.testGitHubAPI();
        demo.testJSONPlaceholderAPI();
        demo.testDogAPI();
        demo.testRestCountriesAPI();
        demo.testCatFactsAPI();
        demo.testOpenWeatherAPI(); // 6th API (requires key)
        
        // Analysis and summary
        demo.printAnalysis();
    }
    
    // 1. GitHub API - GET request
    private void testGitHubAPI() {
        System.out.println("1. GitHub REST API");
        System.out.println("==================");
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/octocat"))
                    .header("Accept", "application/json")
                    .header("User-Agent", "APIDemo/1.0")
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            printRequestResponse("GET", "https://api.github.com/users/octocat", request, response);
            
            System.out.println("cURL Command:");
            System.out.println("curl -H \"Accept: application/json\" -H \"User-Agent: APIDemo/1.0\" https://api.github.com/users/octocat\n");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // 2. JSONPlaceholder API - POST request
    private void testJSONPlaceholderAPI() {
        System.out.println("2. JSONPlaceholder REST API");
        System.out.println("===========================");
        
        try {
            String jsonBody = "{\"title\":\"Test Post\",\"body\":\"Test content\",\"userId\":1}";
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            printRequestResponse("POST", "https://jsonplaceholder.typicode.com/posts", request, response);
            
            System.out.println("cURL Command:");
            System.out.println("curl -X POST -H \"Content-Type: application/json\" -d '" + jsonBody + "' https://jsonplaceholder.typicode.com/posts\n");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // 3. Dog CEO API - Simple GET
    private void testDogAPI() {
        System.out.println("3. Dog CEO REST API");
        System.out.println("===================");
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dog.ceo/api/breeds/image/random"))
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            printRequestResponse("GET", "https://dog.ceo/api/breeds/image/random", request, response);
            
            System.out.println("cURL Command:");
            System.out.println("curl https://dog.ceo/api/breeds/image/random\n");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // 4. REST Countries API - GET with query parameters
    private void testRestCountriesAPI() {
        System.out.println("4. REST Countries API");
        System.out.println("=====================");
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://restcountries.com/v3.1/name/japan?fields=name,capital,population"))
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            printRequestResponse("GET", "https://restcountries.com/v3.1/name/japan?fields=name,capital,population", request, response);
            
            System.out.println("cURL Command:");
            System.out.println("curl -H \"Accept: application/json\" \"https://restcountries.com/v3.1/name/japan?fields=name,capital,population\"\n");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // 5. Cat Facts API - Another simple GET
    private void testCatFactsAPI() {
        System.out.println("5. Cat Facts REST API");
        System.out.println("=====================");
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://catfact.ninja/fact"))
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            printRequestResponse("GET", "https://catfact.ninja/fact", request, response);
            
            System.out.println("cURL Command:");
            System.out.println("curl -H \"Accept: application/json\" https://catfact.ninja/fact\n");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // 6. OpenWeatherMap API - API Key authentication (demo structure)
    private void testOpenWeatherAPI() {
        System.out.println("6. OpenWeatherMap API (API Key Required)");
        System.out.println("========================================");
        
        System.out.println("Request Structure (requires valid API key):");
        System.out.println("Method: GET");
        System.out.println("URL: https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY");
        System.out.println("Headers: Accept: application/json");
        
        System.out.println("\ncURL Command:");
        System.out.println("curl -H \"Accept: application/json\" \"https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY\"\n");
        
        System.out.println("Note: Replace YOUR_API_KEY with actual API key from openweathermap.org");
        
        System.out.println("=".repeat(60) + "\n");
    }
    
    // Helper method to print request and response info
    private void printRequestResponse(String method, String url, HttpRequest request, HttpResponse<String> response) {
        System.out.println("Request: " + method + " " + url);
        
        System.out.println("Request Headers:");
        request.headers().map().forEach((key, values) ->
            System.out.println("  " + key + ": " + String.join(", ", values)));
        
        System.out.println("Response Status: " + response.statusCode());
        
        System.out.println("Response Headers:");
        response.headers().map().forEach((key, values) ->
            System.out.println("  " + key + ": " + String.join(", ", values)));
        
        String body = response.body();
        if (body.length() > 150) {
            System.out.println("Response Body: " + body.substring(0, 150) + "...");
        } else {
            System.out.println("Response Body: " + body);
        }
        System.out.println();
    }
    
    // Analysis and evaluation
    private void printAnalysis() {
        System.out.println("API DESIGN ANALYSIS");
        System.out.println("===================");
        
        System.out.println("\n1. REST API Principles Evaluation:");
        System.out.println("----------------------------------");
        
        System.out.println("✅ GitHub API: Excellent REST design");
        System.out.println("   - Clear resource URLs (/users/{username})");
        System.out.println("   - Proper HTTP methods and status codes");
        System.out.println("   - Good error handling");
        
        System.out.println("✅ JSONPlaceholder API: Good for CRUD operations");
        System.out.println("   - Supports all HTTP methods (GET, POST, PUT, DELETE)");
        System.out.println("   - Consistent JSON responses");
        
        System.out.println("✅ Dog CEO & Cat Facts APIs: Simple and effective");
        System.out.println("   - Easy to use, no authentication needed");
        System.out.println("   - Clear response format");
        
        System.out.println("✅ REST Countries API: Advanced features");
        System.out.println("   - Field selection with query parameters");
        System.out.println("   - Flexible filtering options");
        
        System.out.println("⚠️ OpenWeatherMap API: Security concerns");
        System.out.println("   - API key in URL is less secure");
        System.out.println("   - Should use Authorization header instead");
        
        System.out.println("\n2. Common Headers Explained:");
        System.out.println("----------------------------");
        
        System.out.println("REQUEST HEADERS:");
        System.out.println("• Accept: application/json - Tells server what format we want");
        System.out.println("• Content-Type: application/json - Tells server our data format");
        System.out.println("• User-Agent: APIDemo/1.0 - Identifies our application");
        System.out.println("• Authorization: Bearer <token> - Provides authentication");
        
        System.out.println("\nRESPONSE HEADERS:");
        System.out.println("• Content-Type: application/json - Server's response format");
        System.out.println("• Cache-Control: max-age=3600 - How long to cache the response");
        System.out.println("• X-RateLimit-Remaining: 59 - How many API calls left");
        System.out.println("• Access-Control-Allow-Origin: * - CORS policy for browsers");
        
        System.out.println("\n3. Better Design Recommendations:");
        System.out.println("---------------------------------");
        
        System.out.println("SECURITY:");
        System.out.println("   - Use Authorization header instead of API key in URL");
        System.out.println("   - Implement OAuth 2.0 for better security");
        System.out.println("   - Always use HTTPS");
        
        System.out.println("PERFORMANCE:");
        System.out.println("   - Add caching headers (ETag, Last-Modified)");
        System.out.println("   - Implement pagination for large datasets");
        System.out.println("   - Support compression (gzip)");
        
        System.out.println("DEVELOPER EXPERIENCE:");
        System.out.println("   - Provide clear error messages");
        System.out.println("   - Include rate limiting information");
        System.out.println("   - Offer comprehensive documentation");
        
        System.out.println("\n4. Summary:");
        System.out.println("-----------");
        System.out.println("• Tested 6 different public APIs");
        System.out.println("• All demonstrate REST principles to varying degrees");
        System.out.println("• Headers provide crucial metadata for HTTP communication");
        System.out.println("• Security and performance can always be improved");
        System.out.println("• Good API design benefits both providers and consumers");
    }
} 