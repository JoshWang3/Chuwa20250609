import java.util.*;
class Blog{
    private List<String> tags;
    public Blog(List<String> tags){
        this.tags = tags;
    }
    public List<String> getTags(){
        return tags;
    }
}
public class TagsSearch {
    static class Solution{
        public List<String> getUniqueTags(List<Blog> blogs){
            return blogs.stream()
                    .flatMap(b->b.getTags().stream())
                    .distinct()
                    .sorted()
                    .toList();
        }
    }
    public static void main(String[] args) {
        List<Blog> blogs = List.of(
                new Blog(List.of("java", "spring", "cloud")),
                new Blog(List.of("python", "cloud", "data")),
                new Blog(List.of("java", "microservices", "kotlin")),
                new Blog(List.of("go", "cloud", "kubernetes", "go")),
                new Blog(Collections.emptyList()),
                new Blog(List.of("data", "ai", "java"))
        );
        Solution solution = new Solution();
        List<String> uniqueTags = solution.getUniqueTags(blogs);
        System.out.println(uniqueTags);
    }
}
