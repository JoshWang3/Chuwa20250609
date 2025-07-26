### Short Questions

## Question1

Solve following questions using Stream API, you should write complete code context rather than a
   single Stream API statement (please also write necessary POJO classes or helper code):
1. Find top 3 longest strings that start with a vowel
2. Return names of departments where average employee salary > 100,000
3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
4. Return top 5 words by frequency from a paragraph
5. Group products by category and sort each group by price descending

Answer1:
ALL codes above are in Projects folder Question1_x.java

## Question2

Write code snippet to explain how Optional helps prevent null pointer exception, you may use blog-tags,
   and product-category POJOs to demo.

Answer2:
the code is in Question2.java

for value in stream, we can use filter to avoid null :

`.filter(ob -> ob != null)`

for functions like `blog.getTags()` and `category.getCategory()`,
we need use `Optional.ofNullable` to wrap it:

```
Optional.ofNullable(blog.getTags())
                                .orElse(Collections.emptyList())
                                

Optional.ofNullable(category.getCategory())
                                        .orElse("UnknownType"),
```


## Question3
Explain why Java Stream API is required,how does it help on data processing?

Concise, pipeline processing, lazy evaluation(improve performance), Parallel streams

