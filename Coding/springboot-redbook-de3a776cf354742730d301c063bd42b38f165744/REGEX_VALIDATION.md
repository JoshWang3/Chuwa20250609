# Regular Expression Validation Patterns

## Post Entity Validation

### 1. Post Title Validation
**Pattern:** `^[a-zA-Z0-9\\s\\-_.,!?'\"()]{3,100}$`

**Description:** 
- **Length:** 3-100 characters
- **Allowed characters:** Letters (a-z, A-Z), numbers (0-9), spaces, hyphens, underscores, periods, commas, exclamation marks, question marks, single quotes, double quotes, parentheses
- **Use case:** Blog post titles that are descriptive but not too long

**Examples:**
- ✅ Valid: "My First Blog Post!"
- ✅ Valid: "How to Learn Java - Part 1"
- ✅ Valid: "Top 10 Tips (2024)"
- ❌ Invalid: "Hi" (too short)
- ❌ Invalid: "My Post @#$%" (invalid special characters)

### 2. Post Description Validation
**Pattern:** `^[a-zA-Z0-9\\s\\-_.,!?'\"()\\n\\r]{10,500}$`

**Description:**
- **Length:** 10-500 characters
- **Allowed characters:** Same as title + newlines and carriage returns
- **Use case:** Short summary of the blog post

**Examples:**
- ✅ Valid: "This post discusses the fundamentals of Spring Boot development."
- ✅ Valid: "Learn how to create REST APIs with proper validation and error handling."
- ❌ Invalid: "Too short" (less than 10 characters)
- ❌ Invalid: Contains special symbols like @ # $ % & *

### 3. Post Content Validation
**Pattern:** `^[a-zA-Z0-9\\s\\-_.,!?'\"()\\n\\r@#$%&*+=/<>{}\\[\\]]{20,5000}$`

**Description:**
- **Length:** 20-5000 characters
- **Allowed characters:** All basic text characters including more symbols for formatting
- **Use case:** Full blog post content with rich text support

**Examples:**
- ✅ Valid: "This is a comprehensive guide to Spring Boot development. It covers topics like @RestController, @Service, and more..."
- ✅ Valid: "Code example: public class MyClass { // implementation }"
- ❌ Invalid: "Short content" (less than 20 characters)

## Comment Entity Validation

### 1. Comment Name Validation
**Pattern:** `^[a-zA-Z\\s'-]{2,50}$`

**Description:**
- **Length:** 2-50 characters
- **Allowed characters:** Letters, spaces, hyphens, apostrophes only
- **Use case:** User names for comments (real names, not usernames)

**Examples:**
- ✅ Valid: "John Doe"
- ✅ Valid: "Mary-Jane Smith"
- ✅ Valid: "O'Connor"
- ❌ Invalid: "J" (too short)
- ❌ Invalid: "User123" (numbers not allowed)

### 2. Comment Email Validation
**Pattern:** `^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$`

**Description:**
- **Format:** Standard email format with @ symbol
- **Parts:** username@domain.extension
- **Use case:** User email addresses for comment notifications

**Examples:**
- ✅ Valid: "john.doe@example.com"
- ✅ Valid: "user+tag@domain.org"
- ✅ Valid: "test.email@sub.domain.com"
- ❌ Invalid: "notanemail"
- ❌ Invalid: "user@domain" (missing extension)

### 3. Comment Body Validation
**Pattern:** `^[a-zA-Z0-9\\s\\-_.,!?'\"()\\n\\r]{5,1000}$`

**Description:**
- **Length:** 5-1000 characters
- **Allowed characters:** Basic text characters for comments
- **Use case:** Comment content that's informative but not too long

**Examples:**
- ✅ Valid: "Great article! Thanks for sharing."
- ✅ Valid: "I disagree with point #2. Here's why..."
- ❌ Invalid: "Good" (too short)
- ❌ Invalid: Contains special characters like @ # $ %

## Implementation Details

### Service Layer Validation
The validation is implemented in the service layer using the `matches()` method:

```java
private void validatePostTitle(String title) {
    if (title == null || !title.matches("^[a-zA-Z0-9\\s\\-_.,!?'\"()]{3,100}$")) {
        throw new ValidationException("Title must be 3-100 characters, alphanumeric with basic punctuation");
    }
}
```

### Entity Level Validation
The validation is also defined at the entity level using `@Pattern` annotations:

```java
@Pattern(regexp = "^[a-zA-Z0-9\\s\\-_.,!?'\"()]{3,100}$", 
         message = "Title must be 3-100 characters, alphanumeric with basic punctuation")
private String title;
```

### Error Handling
When validation fails, the application throws a `ValidationException` with a descriptive message, which is caught by the `GlobalExceptionHandler` and returns a 400 Bad Request response.

## Testing the Patterns

You can test these patterns at [regex101.com](https://regex101.com/) by:
1. Copying the pattern
2. Setting the flavor to "Java"
3. Testing with various input strings

## Security Considerations

These patterns help prevent:
- **XSS attacks** by restricting dangerous characters
- **SQL injection** by limiting input format
- **Buffer overflow** by enforcing length limits
- **Data corruption** by ensuring valid formats 