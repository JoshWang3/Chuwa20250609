# 6.18 HW5 - Java8

1. Solve following questions using Stream API, you should write complete code context rather than a single Stream API statement (please also write necessary POJO classes or helper code):
   1. Find top 3 longest strings that start with a vowel
      
      [LongestString.java](../../Coding/hw5/LongestString.java)
   2. Return names of departments where average employee salary > 100,000
      
      [EmployeeSalary.java](../../Coding/hw5/EmployeeSalary.java)
   3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
      
      [BlogTags.java](../../Coding/hw5/BlogTags.java)
   4. Return top 5 words by frequency from a paragraph
      
      [WordFrequency.java](../../Coding/hw5/WordFrequency.java)
   5. Group products by category and sort each group by price descending
      
      [ProductsCategory.java](../../Coding/hw5/ProductsCategory.java)


2. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.

   Optional helps prevent `NullPointerException` by wrapping potentially null values inside **a safe container**, which clearly indicates whether a value is present or absent. With using Optional, we can avoid nested null checks, improve code readability, and reduce risk of NullPointerException.

   [OptionalDemoBlog.java](../../Coding/hw5/OptionalDemoBlog.java)

   [OptionalDemoProduct.java](../../Coding/hw5/OptionalDemoProduct.java)

   

3. Explain why Java Stream API is required, how does it help on data processing?

   Stream API is required because it provides a clean and powerful way to process collections of data in an efficient way.

   It is **memory-efficient**, which means intermediate operations (like `filter()` or `map()`) don’t create new data structures in memory. The processing happens only when a terminal operation (like `collect()` or `forEach()`) is called. This improves performance and avoids unnecessary memory usage — especially with large datasets.

   It supports **chain-style data operations**, which makes the code easy to read and maintain, without writing multiple loops or temporary variables.