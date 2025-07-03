Wrapper data type classes ar object-oriented wrappers around the primitive data types (e.g. int, double, char, etc.), which provides a way to handle primitive data types as objects.

The reason why we need wrapper classes is that 
1. Java collection classes (List, Set, Map, etc.) can only work with reference types instead of primitive data. (e.g. List<Integer>)
2. Wrapper classes can be null whereas primitive data always have a value. (0 for int)
3. Wrapper classes provide static method (Integer.parseInt(), Integer.toString()) and constants (Integer.MAX_VALUE) that is useful for data processing.