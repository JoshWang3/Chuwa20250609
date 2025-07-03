2. How Optional Prevents Null Pointer Exceptions

Optional is a container object which may or may not contain a non-null value.
With Optional, the method signature itself signals that the value might be absent. 
This forces the caller to "unwrap" the Optional and consciously decide what to do if the value is not there.

```java
import java.util.Optional;

public Optional<Product> findProductByName(String name, List<Product> products) {
return products.stream()
.filter(p -> p.getName().equalsIgnoreCase(name))
.findFirst();
}

Product product = findProductByName("Tablet", products)
.orElse(new Product("Not Found", "N/A", 0.0));
System.out.println(product.getCategory()); 

findProductByName("Laptop", products)
.ifPresent(p -> System.out.println("Found a laptop! Price: " + p.getPrice()));


String category = findProductByName("Jeans", products) 
.map(Product::getCategory)        
.orElse("Category not found");    
System.out.println(category); 
```
By using Optional, you make your API clearer and prevent entire classes of NullPointerException errors at compile time rather than discovering them at runtime. 

3. Why the Java Stream API is Required

Stream pipelines are often shorter and more closely resemble the business logic they represent, making the code easier to understand and maintain.

Stream operations are designed to be stateless and produce new collections rather than modifying existing ones. This functional approach reduces bugs caused by unexpected state changes.

Streams can be processed in parallel to leverage multi-core processors, often with a single method call change (.stream() to .parallelStream()). Achieving this with manual thread management is significantly more complex and error-prone.

Operations on a stream are only performed when a terminal operation is invoked. This allows the stream pipeline to perform optimizations, such as combining multiple operations into a single pass over the data.