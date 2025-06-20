### 3. Explain why Java Stream API is required, how does it help on data processing?

The Java Stream API provides several key advantages for data processing:
- Functional Programming Style: 
  - Streams enable declarative programming where you describe what you want rather than how to do it. This makes code more readable and expressive.
- Method Chaining: 
  - Operations can be chained together fluently, creating pipelines that are easy to read and understand. For example: list.stream().filter().map().collect().
- Lazy Evaluation: 
  - Intermediate operations are lazy - they're not executed until a terminal operation is called. This improves performance by avoiding unnecessary processing.
- Parallel Processing: 
  - Converting to parallel streams with .parallelStream() automatically distributes work across multiple threads, leveraging multi-core processors without complex threading code.
- Built-in Operations: 
  - Rich set of operations like filter, map, reduce, collect, groupBy handle common data processing patterns without writing loops.
- Immutability: 
  - Stream operations don't modify the original data source, reducing side effects and making code more predictable.
- Memory Efficiency: 
  - Streams process elements on-demand rather than creating intermediate collections, reducing memory overhead for large datasets.