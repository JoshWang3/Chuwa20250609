# hw5 submission

## Q1: Solve following questions using Stream API

### Answer:
Code in :

/Coding/hw5

## Q2: Write code snippet to explain how Optional helps prevent null pointer exception

### Answer:
Code in :

/Coding/hw5/Q1UniqueTags.java

Optional provides a safer alternative to traditional null checks. It allows chaining, avoids clutter, and makes your code more expressive and null-safe.

In demo code, what it prevent:

Avoids NPE: blog.getTags() might be null, but Optional.ofNullable(...).orElse(Collections.emptyList()) ensures a safe fallback.

## Q3: Explain why Java Stream API is required, how does it help on data processing?

### Answer:
Why Java Stream API is Required:

1. Imperative code is verbose and error-prone

Before Java 8, most data processing was done using loops like for or while. These required:

Manual iteration

Conditional logic

Mutability (e.g., temporary variables, mutable lists)

Extra storage of data


2. Streams enable declarative programming

The Stream API allows you to write declarative, functional-style code:

What to do, not how to do it

Focuses on describing the result, not the mechanics of iteration

How Java Stream API Helps in Data Processing

1. Chainable operations (pipelines)

Stream API allows a chain of operations like filter → map → sort → collect, enabling complex data transformations in a single, readable pipeline.

2. Supports both sequential and parallel processing

Just change .stream() to .parallelStream() to automatically enable multi-threaded data processing — without writing thread code.

3. Better abstraction for aggregation

Streams provide powerful tools for grouping, summarizing, and reducing data.

4. Lazy evaluation and short-circuiting

Streams are lazy — intermediate operations are not executed until a terminal operation is invoked (collect(), forEach(), count(), etc.).

This allows optimizations like:

Short-circuiting (limit(), anyMatch())

Skipping unnecessary computation
