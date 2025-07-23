Testing:

1. Unit Testing
   1. Definition: Tests individual units or components (functions, methods, or classes) in isolation. 
   2. Goal: Verify that each small part of the code behaves correctly. 
   3. Example: Testing a calculateTotalPrice() function in an e-commerce app that calculates cart totals. 
   4. Comparison: More granular than all other types. Unlike integration or functional testing, unit tests don’t involve other parts of the system (e.g., database or UI).
2. Functional Testing 
   1. Definition: Tests specific functions of a system against defined requirements, often via the UI or API. 
   2. Goal: Ensure the system performs its intended functions. 
   3. Example: Verifying that clicking “Submit” on a login form authenticates a user correctly. 
   4. Comparison: Broader than unit testing; may involve multiple components but focuses strictly on functional correctness (not performance or UX).
3. Integration Testing
   1. Definition: Tests how multiple components work together. 
   2. Goal: Ensure modules or services interact correctly. 
   3. Example: Testing how the payment gateway interacts with the order system after a purchase. 
   4. Comparison: Fills the gap between unit and end-to-end testing. Unlike unit tests, it involves multiple real modules or external systems.
4. Regression Testing
   1. Definition: Tests existing functionality after code changes to ensure nothing is broken. 
   2. Goal: Prevent new bugs from being introduced in previously working features. 
   3. Example: After updating the shipping module, retesting the checkout process to ensure it's still functional. 
   4. Comparison: It’s not a testing level (like unit or integration), but a strategy applied across test types.
5. Smoke Testing
   1. Definition: A quick check to see if the basic and critical functions of a build work. 
   2. Goal: Validate whether a build is stable enough for deeper testing. 
   3. Example: Checking if the app launches, login works, and main dashboard loads after deployment. 
   4. Comparison: Shallow and broad, unlike exhaustive functional or regression testing also known as build verification testing.
6. Performance Testing
   1. Definition: Measures responsiveness, speed, scalability, and stability of the system under expected load. 
   2. Goal: Ensure acceptable user experience under normal conditions. 
   3. Example: Measuring how fast the website loads with 500 concurrent users. 
   4. Comparison: Different from functional testing—focuses on how well the system works rather than what it does.
7. Stress Testing
   1. Definition: Pushes the system beyond its limits to test its breaking point. 
   2. Goal: Observe how the system fails and how it recovers. 
   3. Example: Sending 10,000 requests per second to an API until it crashes. 
   4. Comparison: A subtype of performance testing but focuses on failure behavior instead of normal operation.
8. A/B Testing
   1. Definition: Compares two versions of a system with real users to see which performs better. 
   2. Goal: Make data-driven design or feature decisions. 
   3. Example: Showing version A with a red “Buy Now” button to 50% of users and version B with a blue button to the other 50%, then comparing conversion rates. 
   4. Comparison: A form of live experiment. Unlike unit or regression tests, it involves real user feedback and is not about correctness but effectiveness.
9. End-to-End (E2E) Testing
   1. Definition: Tests complete workflows from the user's perspective, across all components. 
   2. Goal: Ensure the entire system works together as expected. 
   3. Example: Simulating a user placing an order from login to checkout to confirmation email. 
   4. Comparison: Most comprehensive. Builds on top of unit/integration/functional tests and mimics real-world use.
10. User Acceptance Testing (UAT)
    1. Definition: Final testing by actual users or stakeholders before production release. 
    2. Goal: Validate the system meets business requirements. 
    3. Example: A sales team testing a new CRM feature to verify it fits their workflow. 
    4. Comparison: Business-facing and usually conducted after internal testing passes. It's the final gate before production.

Environment:

1. Development
   1. Definition: Environment where developers write and test code. 
   2. Usage: Frequent builds, untested features, developer tools. 
   3. Example: A local server running on a developer’s machine. 
   4. Comparison: Least stable, not suitable for testing real-world scenarios.
2. QA (Quality Assurance)
   1. Definition: Environment for dedicated testing (manual and automated). 
   2. Usage: Integration, regression, and functional tests run here. 
   3. Example: QA team testing new builds for bugs after development finishes. 
   4. Comparison: More stable than dev, but may use mock data or partial features.
3. Pre-prod/Staging
   1. Definition: Mirrors production as closely as possible. 
   2. Usage: Final tests, smoke tests, UAT, and performance tests happen here. 
   3. Example: Load testing a full site with real infrastructure before going live. 
   4. Comparison: Last step before production. Should be nearly identical to prod, often using sanitized or production-like data.
4. Production
   1. Definition: Live environment where real users interact with the system. 
   2. Usage: Live traffic, real data, real impact. 
   3. Example: Amazon.com or Google Docs used by actual customers. 
   4. Comparison: Most stable, heavily monitored, and highest risk for bugs. No testing should occur here except A/B tests or canary releases.
