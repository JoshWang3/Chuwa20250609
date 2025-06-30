# Ryan Ma HW5 Short questions and Screenshots


### 1. Solve following questions using Stream API, you should write complete code context rather than a single Stream API statement (please also write necessary POJO classes or helper code)
- All codes from 1.1 to 1.5 are in Coding/HW/HW5
###   1.1 Find top 3 longest strings that start with a vowel
###   1.2 Return names of departments where average employee salary > 100,000
###   1.3 Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
###   1.4 Return top 5 words by frequency from a paragraph
###   1.5 Group products by category and sort each group by price descending

### 2. Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags, and product-category POJOs to demo.
- In Coding/HW/HW5

### 3. Explain why Java Stream API is required, how does it help on data processing?
- Why Java Stream API is required?
Before Streams, Data processing used verbose loops, conditionals, temporary variables. Code became hard to read, error-prone, and harder to parallelize.
Java 8 introduced functional programming concepts. Then Stream API leverages these to process data functionally.
- How does it help on data processing?

  | Benefits                | Explanation                            |
  |-------------------------|----------------------------------------|
  | **Declarative**         | Express *what* to do, not *how*        |
  | **Chainable pipelines** | Build clean processing steps           |
  | **Parallelizable**      | Easy multi-core data processing        |
  | **Lazy evaluation**     | Performance optimization               |
  | **Rich API**            | Filtering, mapping, reducing, grouping |

