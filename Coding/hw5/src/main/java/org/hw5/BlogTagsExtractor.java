package org.hw5;

import java.util.*;
import java.util.stream.Collectors;

//1.3. Get a sorted list of all unique tags from a list of blog posts (Blog to Tags is 1-to-many relationship)
public class BlogTagsExtractor {

    // Blog post class representing the entity
    static class BlogPost {
        private String title;
        private String content;
        private List<String> tags;

        public BlogPost(String title, String content, List<String> tags) {
            this.title = title;
            this.content = content;
            this.tags = tags != null ? tags : new ArrayList<>();
        }

        //  getters and setters
        public List<String> getTags() {
            return tags;
        }

        public String getTitle() { return title; }
        public String getContent() { return content; }
    }

    /**
     * Extract sorted unique tags from a list of blog posts using Stream API
     *
     * @param blogPosts List of blog posts
     * @return Sorted list of unique tags
     */
    public static List<String> getSortedUniqueTags(List<BlogPost> blogPosts) {
        return blogPosts.stream()
                .filter(Objects::nonNull)                    // Filter out null blog posts
                .flatMap(post -> post.getTags().stream())    // Flatten all tags into single stream
                .filter(Objects::nonNull)                    // Filter out null tags
                .filter(tag -> !tag.trim().isEmpty())       // Filter out empty/whitespace tags
                .map(String::trim)                           // Trim whitespace from tags
                .map(String::toLowerCase)                    // Normalize case (optional)
                .distinct()                                  // Remove duplicates
                .sorted()                                    // Sort alphabetically
                .collect(Collectors.toList());               // Collect to list
    }



    /**
     * Implementation using TreeSet for automatic sorting and uniqueness
     */
    public static Set<String> getSortedUniqueTagsAsSet(List<BlogPost> blogPosts) {
        return blogPosts.stream()
                .filter(Objects::nonNull)
                .flatMap(post -> post.getTags().stream())
                .filter(Objects::nonNull)
                .filter(tag -> !tag.trim().isEmpty())
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    // Demo usage
    public static void main(String[] args) {
        // Sample data
        List<BlogPost> blogPosts = Arrays.asList(
                new BlogPost("Java Basics", "Content about Java",
                        Arrays.asList("java", "programming", "basics", "OOP")),
                new BlogPost("Spring Framework", "Content about Spring",
                        Arrays.asList("java", "spring", "framework", "web")),
                new BlogPost("Database Design", "Content about databases",
                        Arrays.asList("database", "sql", "design", "programming")),
                new BlogPost("React Tutorial", "Content about React",
                        Arrays.asList("javascript", "react", "frontend", "web")),
                new BlogPost("Empty Tags Post", "Post with empty tags",
                        Arrays.asList("", "  ", null, "valid-tag"))
        );

        // Test the algorithm
        System.out.println("Sorted Unique Tags:");
        List<String> uniqueTags = getSortedUniqueTags(blogPosts);
        uniqueTags.forEach(System.out::println);

        System.out.println("\nTotal unique tags: " + uniqueTags.size());


        // Test TreeSet version
        System.out.println("\nTreeSet Version:");
        getSortedUniqueTagsAsSet(blogPosts).forEach(System.out::println);
    }
}
