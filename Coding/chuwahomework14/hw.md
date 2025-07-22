### 1. compare all types of test
    ✅ 1. Unit Testing
    What: Test individual methods or classes in isolation (no dependencies like network or database).
    
    Example in Java:
    
    java
    Copy
    Edit
    public class Calculator {
    public int add(int a, int b) {
    return a + b;
    }
    }
    JUnit Test:
    
    java
    Copy
    Edit
    @Test
    public void testAdd() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(2, 3));
    }
    🔁 Comparison: Fastest and cheapest. Doesn’t test integration with other systems.
    
    ✅ 2. Functional Testing
    What: Tests a specific feature or function of the app (like login, fetch store list).
    
    Java/Android Example:
    Test if StoreFeed displays the list correctly.
    
    java
    Copy
    Edit
    @Test
    public void testStoreFeedDisplay() {
    onView(withId(R.id.stores_view))
    .check(matches(isDisplayed()));
    }
    🔁 Comparison: Broader than unit testing, focuses on feature behavior.
    
    ✅ 3. Integration Testing
    What: Tests how different modules work together (e.g., ViewModel + Repository + API).
    
    Example:
    
    java
    Copy
    Edit
    @Test
    public void testViewModelToApiIntegration() {
    StoreViewModel vm = new StoreViewModel(new FakeRepository());
    List<Store> stores = vm.getStores(); // Connects ViewModel to Repository
    assertFalse(stores.isEmpty());
    }
    🔁 Comparison: Includes external classes or services. Slower than unit tests.
    
    ✅ 4. Regression Testing
    What: Ensures that new changes don’t break existing features.
    
    Example:
    
    java
    Copy
    Edit
    @Test
    public void testLoginStillWorksAfterRefactor() {
    boolean result = loginService.login("user", "pass");
    assertTrue(result);
    }
    🔁 Comparison: Not a unique type of test, but rather a purpose for rerunning existing tests.
    
    ✅ 5. Smoke Testing
    What: Basic set of tests to ensure app builds and launches properly (sanity check).
    
    Example:
    
    java
    Copy
    Edit
    @Test
    public void appLaunchesSuccessfully() {
    ActivityScenario.launch(MainActivity.class)
    .onActivity(activity -> assertNotNull(activity));
    }
    🔁 Comparison: Shallow and broad. Done before deeper testing. Like "Does the app start?"
    
    ✅ 6. Performance Testing
    What: Measures how fast or resource-efficient your app is.
    
    Example (JUnit + System.nanoTime):
    
    java
    Copy
    Edit
    @Test
    public void testApiCallPerformance() {
    long start = System.nanoTime();
    storeService.fetchStores();
    long duration = System.nanoTime() - start;
    assertTrue(duration < 1000000000); // < 1 second
    }
    🔁 Comparison: Non-functional, often automated separately. Requires benchmarking tools.
    
    ✅ 7. Stress Testing
    What: Checks app behavior under extreme conditions (e.g., many users, low memory).
    
    Example:
    
    java
    Copy
    Edit
    @Test
    public void testMultipleLoginAttempts() {
    for (int i = 0; i < 1000; i++) {
    loginService.login("user", "wrongpass");
    }
    assertTrue(loginService.lockedOut());
    }
    🔁 Comparison: Focuses on robustness and breaking points. Often simulates "worst case".
    
    ✅ 8. A/B Testing
    What: Compare two versions (A and B) to see which performs better.
    
    Example:
    
    java
    Copy
    Edit
    String buttonText = FeatureToggle.isNewVersion() ? "Buy Now" : "Shop";
    assertEquals("Buy Now", buttonText); // For group B
    🔁 Comparison: Not about correctness but about user behavior and conversion.
    
    ✅ 9. End-to-End (E2E) Testing
    What: Tests the full user journey across the system (UI to DB and back).
    
    Example with Espresso:
    
    java
    Copy
    Edit
    @Test
    public void testCompleteStoreBrowsingFlow() {
    onView(withId(R.id.stores_view))
    .perform(click()); // Opens details
    onView(withId(R.id.store_description))
    .check(matches(isDisplayed()));
    }
    🔁 Comparison: Slowest and most comprehensive. Tests real-life usage scenarios.

    Type	Scope	Speed	Example Layer	Purpose
    Unit Testing	Single method/class	🔥 Fast	Utils, ViewModel	Validate internal logic
    Functional	Feature level	⚡ Fast	Fragment, Activity	Ensure features work
    Integration	Connected modules	🐢 Medium	ViewModel + Repository	Check module interaction
    Regression	All past tests	⚡ Varies	All	Prevent reintroduced bugs
    Smoke	High-level check	⚡ Fast	Launch screen	Sanity test
    Performance	Timing/resource	🐢 Medium	API calls, loops	Ensure performance limits
    Stress	Extreme conditions	🐢 Slow	Network, login	System under load
    A/B Testing	Version comparison	🐢 Varies	UI text, layout	User behavior insights
    End-to-End	Full stack	🐌 Slow	UI → API → DB	Simulate real user journey


### 2 environment types 

    1. Development: An environment used by developers to build, test, and run ongoing features and APIs. It is not stable and is typically not exposed to external clients.

    2. QA (Quality Assurance): An environment where QA engineers test new features for functionality, stability, and bugs before they are released further down the pipeline. It is more stable than development but still internal-facing.

    3. Pre-prod/Staging: An environment that closely replicates production. It is generally stable and used by client-side teams or other dependent systems to validate integrations, features, or data flows before production deployment.

    4. Production: The final live environment accessible to all users. It must be highly stable, secure, and robust. Any modifications occur through a controlled and complete deployment pipeline.


### 3 CommentServiceImplTest

### jacoco won't compile 

![Screenshot 2025-07-21 at 8.12.25 PM.png](Screenshot%202025-07-21%20at%208.12.25%E2%80%AFPM.png)
    