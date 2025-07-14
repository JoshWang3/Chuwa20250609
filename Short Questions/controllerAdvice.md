@ControllerAdvice:
1. Global Exception Handling
Capture and handle exceptions thrown from any controller.

2. Global Data Binding
Customize how request data is bound to Java objects.

3. Global Model Attributes
Add attributes to all @ModelAttribute-annotated model maps.

Alternatives:
1. HandlerExceptionResolver Interface
2. ResponseEntityExceptionHandler class
3. Aspect-Oriented Programming (AOP)

Differences between regular and customized exception:
1. The purpose and structure of the exception
2. How it's handled in @ControllerAdvice
3. The resulting HTTP response and error message shown to the client