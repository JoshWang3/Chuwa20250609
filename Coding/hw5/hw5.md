# Stream API Questions

## Why is Java Stream API required?

Before Java 8, processing collections was tedious with lots of loops and conditional statements. Stream API makes code cleaner and more readable.

**Traditional vs Stream approach:**
```java
// Old way: find high salary employees
List<String> result = new ArrayList<>();
for (Employee emp : employees) {
    if (emp.getSalary() > 100000) {
        result.add(emp.getName());
    }
}

// Stream way: much cleaner
List<String> result = employees.stream()
    .filter(emp -> emp.getSalary() > 100000)
    .map(Employee::getName)
    .collect(Collectors.toList());
```

## How does Stream API help with data processing?

1. **Chaining operations** - You can connect multiple operations together, making logic clear
2. **Parallel processing** - Just add `.parallelStream()` for multi-threading performance boost  
3. **Lazy evaluation** - Only executes when you need the result, saves memory
4. **Built-in methods** - Provides common operations like groupingBy, joining without writing them yourself

**Main benefits:**
- Code is shorter and clearer
- Easy parallelization  
- Functional programming style
- Performance optimizations (JVM internal optimizations)

Overall, Stream API makes Java data processing more modern, comfortable to write, and better performing. 