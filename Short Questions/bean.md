@Component:
1. Used on classes
2. Automatically detects and registers beans
3. Required @ComponentScan
4. Limited to class-level
5. Automatically detected during component scan

Use cases:
1. Let Spring automatically discover and manage your beans. 
2. The class is self-contained and doesn’t require complex instantiation logic.

@Bean:
1. Used on methods inside @Configuration classes
2. Explicitly defines and configures beans
3. Manual register for beans
4. Full control over bean instantiation and configuration
5. Manually called by Spring when initializing the context

Use cases:
1. Manual control over the bean instantiation process. 
2. The object is from a third-party library and you can’t annotate the class. 
3. Need to configure constructor arguments, factory methods, or complex setup.


Bean Scopes:
1. Singleton (default):
   1. Only one instance of the bean is created for the entire Spring container.
   2. Used on stateless services or shared resources.
2. Prototype:
   1. A new instance is created every time the bean is requested.
   2. Used on stateful beans or when bean has mutable state.
3. Request (Web app only):
   1. A new bean instance is created per HTTP request.
   2. Web applications that require request-specific data (e.g., user input).
4. Session (Web app only):
   1. A bean is created for each HTTP session.
   2. User-specific data across multiple requests (e.g., login info).
5. Application (Web app only):
   1. One bean per ServletContext (singleton for web apps).
   2. Shared application-wide beans (e.g., caching).
6. Websocket:
   1. A new bean per WebSocket session.
   2. WebSocket messaging apps needing session-bound state.


Bean id:
1. A unique name or identifier for the bean
2. Used to reference the bean in code or config
3. Must be unique within the Spring container

Bean class:
1. The full class name of the bean
2. Specifies the type of object Spring should create
3. Can have multiple beans of the same class

When a Spring bean has multiple implementations, Spring needs a way to decide which one to inject when autowiring. By default, Spring will throw an error.

Solution:
1. Use @Primary
2. Use @Qualifier
3. Use @Resource
4. Inject as a List or Map when having multiple implementation needs to be injected
