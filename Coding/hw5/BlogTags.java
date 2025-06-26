package hw5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlogTags {
    public static void main(String[] args) {
        List<Blog> blogList = Arrays.asList(
                new Blog("10 Quick Pasta Recipes for Busy Nights", Arrays.asList("Pasta", "Quick Meals", "Italian")),
                new Blog("The Art of Baking Perfect Sourdough", Arrays.asList("Baking", "Breakfast", "Bread")),
                new Blog("Mastering the Perfect Steak at Home", Arrays.asList("Grilling", "Dinner", "Steak")),
                new Blog("Healthy Smoothie Bowls to Start Your Day", Arrays.asList("Healthy", "Breakfast", "Smoothie")),
                new Blog("Secrets to Fluffy Pancakes", Arrays.asList("Breakfast", "Baking", "Desserts")),
                new Blog("Spicy Thai Curry in Under 30 Minutes", Arrays.asList("Thai", "Dinner", "Quick Meals"))
        );

        List<String> uniqueTags = blogList.stream()
                // one-to-many relationship:  each blog has a list of tags
                // flatMap() allows to convert a Stream<Blog> into a Stream<String>
                // First, it maps each element to a stream,
                // then it flattens all of them into one single stream.
                .flatMap(blog -> blog.getTags().stream())
                .distinct()  // get the unique element
                .sorted()
                .collect(Collectors.toList());

        uniqueTags.forEach(System.out::println);
    }
}


