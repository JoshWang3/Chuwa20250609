package BlogPosts;

import java.util.*;
import java.util.stream.*;

public class UniqueTagBlogPosts {
    public static void main(String[] args) {

        List<Blog> blogs = Arrays.asList(
                new Blog("SpongeBob’s Secret Krabby Patty Recipe",
                        Arrays.asList("spongebob", "recipe", "burger", "krabbypatty", "food")
                ),
                new Blog( "Who Earns the Most? A Deep Dive into Bikini Bottom Employee Salaries",
                        Arrays.asList("spongebob", "salary", "bikinibottom", "jobs", "funfacts")
                ),
                new Blog( "How to Be a Model Employee: Lessons from SpongeBob",
                        Arrays.asList("spongebob", "career", "employee", "workethic", "motivation")
                ),
                new Blog(
                        "Inside Bikini Bottom: Where Does Everyone Live?",
                        Arrays.asList("spongebob", "bikinibottom", "homes", "realestate", "community")
                )
        );

        List<String> uniqueTags = blogs.stream()
                .flatMap(blog -> blog.getTags().stream()) // flatten all tag lists into one stream of tags
                .distinct() // remove duplicates
                .sorted() // sorted tags
                .collect(Collectors.toList());

        System.out.println(uniqueTags);
    }
}

// output: [bikinibottom, burger, career, community, employee, food, funfacts, homes, jobs, krabbypatty, motivation, realestate, recipe, salary, spongebob, workethic]